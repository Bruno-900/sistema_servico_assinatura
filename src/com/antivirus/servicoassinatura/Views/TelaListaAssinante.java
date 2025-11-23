package com.antivirus.servicoassinatura.Views;

import com.antivirus.servicoassinatura.dao.AssinanteDAO;
import com.antivirus.servicoassinatura.dao.PlanoDAO;
import com.antivirus.servicoassinatura.model.Assinante;
import com.antivirus.servicoassinatura.model.Plano;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaListaAssinante extends javax.swing.JFrame {

    private final AssinanteDAO assinanteDAO = new AssinanteDAO();
    private final PlanoDAO planoDAO = new PlanoDAO();

    public TelaListaAssinante() {
        initComponents();
        configurarJanela();
        configurarTabela();
        carregarTabela();
    }

    private void configurarJanela() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);     // Abre maximizado
        setResizable(false);                         // Não deixa redimensionar
        setTitle("Sistema de Assinaturas - Gerenciar Assinantes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ícone da janela (se tiver um escudo.png na pasta resources/icones)
        try {
            setIconImage(new ImageIcon(getClass().getResource("/icones/escudo.png")).getImage());
        } catch (Exception ignored) {}

        // Rodapé com seu nome (fica lindo!)
        JLabel rodape = new JLabel("Desenvolvido por SEU NOME - 2025 ©", JLabel.CENTER);
        rodape.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        rodape.setForeground(Color.GRAY);
        getContentPane().add(rodape, BorderLayout.SOUTH);
    }

    private void configurarTabela() {
        tabelaAssinantes.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tabelaAssinantes.setRowHeight(35);
        tabelaAssinantes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        tabelaAssinantes.getTableHeader().setBackground(new Color(0, 102, 204));
        tabelaAssinantes.getTableHeader().setForeground(Color.WHITE);

        // Centralizar todas as colunas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabelaAssinantes.getColumnCount(); i++) {
            tabelaAssinantes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaAssinantes.getModel();
        modelo.setNumRows(0);

        try {
            List<Assinante> lista = assinanteDAO.listarAssinante();
            for (Assinante a : lista) {
                String nomePlano = "Sem plano";
                if (a.getIdPlano() > 0) {
                    Plano p = new PlanoDAO().buscarPorId(a.getIdPlano());
                    if (p != null) nomePlano = p.getNome();
                }

                modelo.addRow(new Object[]{
                        a.getIdAssinante(),
                        a.getNome(),
                        a.getCpf(),
                        a.getEmail(),
                        nomePlano
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar assinantes!", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAssinantes = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel1.setForeground(java.awt.Color.WHITE);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GERENCIAR ASSINANTES");
        jLabel1.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        jPanel1.add(jLabel1, BorderLayout.NORTH);

        tabelaAssinantes.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {"ID", "Nome", "CPF", "Email", "Plano"}
        ) { public boolean isCellEditable(int row, int column) { return false; }});
        jScrollPane1.setViewportView(tabelaAssinantes);

        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBackground(Color.WHITE);
        painelTabela.add(jScrollPane1, BorderLayout.CENTER);
        painelTabela.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        jPanel1.add(painelTabela, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new java.awt.Color(0, 102, 204));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));
        painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));

        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVoltar.setText("← VOLTAR");
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(evt -> this.dispose());

        btnNovo.setBackground(new java.awt.Color(0, 153, 51));
        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 18));
        btnNovo.setForeground(java.awt.Color.WHITE);
        btnNovo.setText("NOVO ASSINANTE");
        btnNovo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNovo.setPreferredSize(new Dimension(220, 55));
        btnNovo.addActionListener(evt -> new TelaCadastroAssinante(this).setVisible(true));

        btnEditar.setBackground(new java.awt.Color(0, 102, 204));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 18));
        btnEditar.setForeground(java.awt.Color.WHITE);
        btnEditar.setText("EDITAR");
        btnEditar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditar.setPreferredSize(new Dimension(140, 55));
        btnEditar.addActionListener(evt -> editar());

        btnExcluir.setBackground(new java.awt.Color(204, 0, 0));
        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 18));
        btnExcluir.setForeground(java.awt.Color.WHITE);
        btnExcluir.setText("EXCLUIR");
        btnExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExcluir.setPreferredSize(new Dimension(140, 55));
        btnExcluir.addActionListener(evt -> excluir());

        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnNovo);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);

        jPanel1.add(painelBotoes, BorderLayout.SOUTH);
        getContentPane().add(jPanel1, BorderLayout.CENTER);

        pack();
    }

    private void editar() {
        int linha = tabelaAssinantes.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um assinante para editar!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int id = (int) tabelaAssinantes.getValueAt(linha, 0);
        new TelaCadastroAssinante(this, id).setVisible(true);
    }

    private void excluir() {
        int linha = tabelaAssinantes.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um assinante para excluir!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir este assinante permanentemente?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

            int id = (int) tabelaAssinantes.getValueAt(linha, 0);
            assinanteDAO.excluirAssinante(id);
            carregarTabela();
            JOptionPane.showMessageDialog(this, "Assinante excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void recarregarTabela() {
        carregarTabela();
    }

    // Variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaAssinantes;
    // End

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaListaAssinante().setVisible(true));
    }
}