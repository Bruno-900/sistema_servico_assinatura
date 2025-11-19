package com.antivirus.servicoassinatura.Views;

import com.antivirus.servicoassinatura.dao.AssinanteDAO;
import com.antivirus.servicoassinatura.dao.PlanoDAO;
import com.antivirus.servicoassinatura.model.Assinante;
import com.antivirus.servicoassinatura.model.Plano;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TelaListaAssinante extends javax.swing.JFrame {

    private AssinanteDAO assinanteDAO = new AssinanteDAO();
    private PlanoDAO planoDAO = new PlanoDAO();

    public TelaListaAssinante() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Gerenciar Assinantes");
        carregarTabela();
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tabelaAssinantes.getModel();
        modelo.setNumRows(0);
        try {
            List<Assinante> lista = assinanteDAO.listarAssinante();
            for (Assinante a : lista) {
                String nomePlano = "Sem plano";
                if (a.getIdPlano() > 0) {
                    Plano p = planoDAO.buscarPorId(a.getIdPlano());
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
            JOptionPane.showMessageDialog(this, "Erro ao carregar assinantes!");
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

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28));
        jLabel1.setForeground(java.awt.Color.WHITE);
        jLabel1.setText("GERENCIAR ASSINANTES");

        tabelaAssinantes.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {"ID", "Nome", "CPF", "Email", "Plano"}
        ) { public boolean isCellEditable(int row, int column) { return false; }});
        tabelaAssinantes.setRowHeight(30);
        jScrollPane1.setViewportView(tabelaAssinantes);

        btnNovo.setBackground(new java.awt.Color(0, 153, 51));
        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 16));
        btnNovo.setForeground(java.awt.Color.WHITE);
        btnNovo.setText("NOVO ASSINANTE");
        btnNovo.addActionListener(evt -> new TelaCadastroAssinante(this).setVisible(true));

        btnEditar.setBackground(new java.awt.Color(0, 102, 204));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 16));
        btnEditar.setForeground(java.awt.Color.WHITE);
        btnEditar.setText("EDITAR");
        btnEditar.addActionListener(evt -> editar());

        btnExcluir.setBackground(new java.awt.Color(204, 0, 0));
        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 16));
        btnExcluir.setForeground(java.awt.Color.WHITE);
        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(evt -> excluir());

        btnVoltar.setText("← VOLTAR");
        btnVoltar.addActionListener(evt -> this.dispose());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                        .addComponent(jLabel1)
                                        .addComponent(jScrollPane1, 840, 840, 840)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnVoltar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnNovo, 180, 180, 180)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnEditar, 120, 120, 120)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnExcluir, 120, 120, 120)))
                                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1)
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPane1, 400, 400, 400)
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                        .addComponent(btnVoltar, 50, 50, 50)
                                        .addComponent(btnNovo, 50, 50, 50)
                                        .addComponent(btnEditar, 50, 50, 50)
                                        .addComponent(btnExcluir, 50, 50, 50))
                                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(jPanel1));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(jPanel1));

        pack();
    }

    private void editar() {
        int linha = tabelaAssinantes.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um assinante!");
            return;
        }
        int id = (int) tabelaAssinantes.getValueAt(linha, 0);
        new TelaCadastroAssinante(this, id).setVisible(true);
    }

    private void excluir() {
        int linha = tabelaAssinantes.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um assinante!");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Excluir permanentemente?", "Excluir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int id = (int) tabelaAssinantes.getValueAt(linha, 0);
            assinanteDAO.excluirAssinante(id);
            carregarTabela();
            JOptionPane.showMessageDialog(this, "Assinante excluído!");
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