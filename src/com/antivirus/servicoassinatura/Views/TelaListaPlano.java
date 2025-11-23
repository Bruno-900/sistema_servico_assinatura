package com.antivirus.servicoassinatura.Views;

import com.antivirus.servicoassinatura.dao.PlanoDAO;
import com.antivirus.servicoassinatura.model.Plano;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaListaPlano extends javax.swing.JFrame {

    private final PlanoDAO planoDAO = new PlanoDAO();

    public TelaListaPlano() {
        initComponents();
        configurarJanela();
        configurarTabela();
        carregarTabela();
    }

    // --- MESMO PADRÃO DA TELA DE ASSINANTES ---
    private void configurarJanela() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);   // abre maximizado
        setResizable(false);                       // não redimensiona
        setTitle("Gerenciar Planos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            setIconImage(new ImageIcon(getClass().getResource("/icones/escudo.png")).getImage());
        } catch (Exception ignored) {}

        JLabel rodape = new JLabel("Desenvolvido por SEU NOME - 2025 ©", JLabel.CENTER);
        rodape.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        rodape.setForeground(Color.GRAY);
        getContentPane().add(rodape, BorderLayout.SOUTH);
    }

    private void configurarTabela() {

        tabelaPlanos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tabelaPlanos.setRowHeight(35);

        tabelaPlanos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tabelaPlanos.getTableHeader().setBackground(new Color(0, 102, 204));
        tabelaPlanos.getTableHeader().setForeground(Color.WHITE);

        // Centralizar colunas
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tabelaPlanos.getColumnCount(); i++) {
            tabelaPlanos.getColumnModel().getColumn(i).setCellRenderer(center);
        }
    }

    private void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaPlanos.getModel();
        modelo.setNumRows(0);

        try {
            List<Plano> lista = planoDAO.listarPlano();
            for (Plano p : lista) {
                modelo.addRow(new Object[]{
                        p.getIdPlano(),
                        p.getNome(),
                        p.getDescricao(),
                        "R$ " + p.getPreco()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar planos!");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new JPanel(new BorderLayout());
        jLabel1 = new JLabel("GERENCIAR PLANOS", SwingConstants.CENTER);
        jScrollPane1 = new JScrollPane();
        tabelaPlanos = new JTable();
        JPanel painelBotoes = new JPanel();
        btnNovo = new JButton();
        btnEditar = new JButton();
        btnExcluir = new JButton();
        btnVoltar = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Cabeçalho
        jPanel1.setBackground(new Color(0, 102, 204));
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        jPanel1.add(jLabel1, BorderLayout.NORTH);

        // Tabela
        tabelaPlanos.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Nome", "Descrição", "Preço"}
        ) {
            public boolean isCellEditable(int row, int column) { return false; }
        });

        jScrollPane1.setViewportView(tabelaPlanos);

        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBackground(Color.WHITE);
        painelTabela.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        painelTabela.add(jScrollPane1, BorderLayout.CENTER);
        jPanel1.add(painelTabela, BorderLayout.CENTER);

        // Painel de botões (igual ao da outra tela)
        painelBotoes.setBackground(new Color(0, 102, 204));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));
        painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));

        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVoltar.setText("← VOLTAR");
        btnVoltar.addActionListener(evt -> this.dispose());

        btnNovo.setBackground(new Color(0, 153, 51));
        btnNovo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnNovo.setForeground(Color.WHITE);
        btnNovo.setText("NOVO PLANO");
        btnNovo.setPreferredSize(new Dimension(220, 55));
        btnNovo.addActionListener(evt -> new TelaCadastroPlano(this).setVisible(true));

        btnEditar.setBackground(new Color(0, 102, 204));
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setText("EDITAR");
        btnEditar.setPreferredSize(new Dimension(140, 55));
        btnEditar.addActionListener(evt -> editarPlano());

        btnExcluir.setBackground(new Color(204, 0, 0));
        btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setText("EXCLUIR");
        btnExcluir.setPreferredSize(new Dimension(140, 55));
        btnExcluir.addActionListener(evt -> excluirPlano());

        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnNovo);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);

        jPanel1.add(painelBotoes, BorderLayout.SOUTH);

        getContentPane().add(jPanel1, BorderLayout.CENTER);
        pack();
    }

    private void editarPlano() {
        int linha = tabelaPlanos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um plano!");
            return;
        }

        int id = (int) tabelaPlanos.getValueAt(linha, 0);
        new TelaCadastroPlano(this, id).setVisible(true);
    }

    private void excluirPlano() {
        int linha = tabelaPlanos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um plano!");
            return;
        }

        int id = (int) tabelaPlanos.getValueAt(linha, 0);
        if (JOptionPane.showConfirmDialog(this,
                "Deseja excluir este plano?",
                "Excluir Plano",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            planoDAO.excluirPlano(id);
            carregarTabela();
        }
    }

    public void carregarTabelaNovamente() { carregarTabela(); }

    // Variables declaration
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaPlanos;
    // End of variables declaration
}
