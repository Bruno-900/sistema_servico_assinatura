package com.antivirus.servicoassinatura.Views;

import com.antivirus.servicoassinatura.dao.PlanoDAO;
import com.antivirus.servicoassinatura.model.Plano;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaListaPlano extends javax.swing.JFrame {

    private PlanoDAO planoDAO = new PlanoDAO();

    public TelaListaPlano() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Gerenciar Planos");
        carregarTabela();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPlanos = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28));
        jLabel1.setForeground(java.awt.Color.WHITE);
        jLabel1.setText("GERENCIAR PLANOS");

        tabelaPlanos.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {"ID", "Nome", "Descrição", "Preço"}
        ) { public boolean isCellEditable(int row, int column) { return false; }});
        tabelaPlanos.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tabelaPlanos.setRowHeight(30);
        jScrollPane1.setViewportView(tabelaPlanos);

        btnNovo.setBackground(new java.awt.Color(0, 153, 51));
        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 16));
        btnNovo.setForeground(java.awt.Color.WHITE);
        btnNovo.setText("NOVO PLANO");
        btnNovo.addActionListener(evt -> new TelaCadastroPlano(this).setVisible(true));

        btnEditar.setBackground(new java.awt.Color(0, 102, 204));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 16));
        btnEditar.setForeground(java.awt.Color.WHITE);
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(evt -> editarPlano());

        btnExcluir.setBackground(new java.awt.Color(204, 0, 0));
        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 16));
        btnExcluir.setForeground(java.awt.Color.WHITE);
        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(evt -> excluirPlano());

        btnVoltar.setText("← VOLTAR");
        btnVoltar.addActionListener(evt -> this.dispose());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                        .addComponent(jLabel1)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnVoltar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1)
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));

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
        if (JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir?", "Excluir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int id = (int) tabelaPlanos.getValueAt(linha, 0);
            planoDAO.excluirPlano(id);
            carregarTabela();
            JOptionPane.showMessageDialog(this, "Plano excluído com sucesso!");
        }
    }

    public void carregarTabelaNovamente() {
        carregarTabela();
    }

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

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaListaPlano().setVisible(true));
    }
}