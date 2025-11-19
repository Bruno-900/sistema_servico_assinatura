package com.antivirus.servicoassinatura.Views;

import  com.antivirus.servicoassinatura.Views.TelaLogin;
import com.antivirus.servicoassinatura.Views.TelaListaAssinante;
import com.antivirus.servicoassinatura.Views.TelaListaPlano;

import javax.swing.JOptionPane;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); // abre maximizado (fica bonito)
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnPlanos = new javax.swing.JButton();
        btnAssinantes = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Painel Administrativo - Sistema Antivirus");

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36));
        jLabel1.setForeground(java.awt.Color.WHITE);
        jLabel1.setText("PAINEL DO ADMINISTRADOR");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel2.setForeground(java.awt.Color.WHITE);
        jLabel2.setText("Escolha uma opção abaixo:");

        btnPlanos.setBackground(new java.awt.Color(0, 153, 204));
        btnPlanos.setFont(new java.awt.Font("Segoe UI", 1, 24));
        btnPlanos.setForeground(java.awt.Color.WHITE);
        //btnPlanos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/plano.png"))); // opcional
        btnPlanos.setText("GERENCIAR PLANOS");
        btnPlanos.setPreferredSize(new java.awt.Dimension(400, 120));
        btnPlanos.addActionListener(evt -> new TelaListaPlano().setVisible(true));

        btnAssinantes.setBackground(new java.awt.Color(0, 153, 51));
        btnAssinantes.setFont(new java.awt.Font("Segoe UI", 1, 24));
        btnAssinantes.setForeground(java.awt.Color.WHITE);
        btnAssinantes.setText("GERENCIAR ASSINANTES");
        btnAssinantes.setPreferredSize(new java.awt.Dimension(400, 120));
        btnAssinantes.addActionListener(evt -> new TelaListaAssinante().setVisible(true));

        btnSair.setBackground(new java.awt.Color(204, 0, 0));
        btnSair.setFont(new java.awt.Font("Segoe UI", 1, 18));
        btnSair.setForeground(java.awt.Color.WHITE);
        btnSair.setText("SAIR DO SISTEMA");
        btnSair.addActionListener(evt -> {
            if (JOptionPane.showConfirmDialog(this, "Deseja realmente sair?", "Sair",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnPlanos, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(btnAssinantes, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnSair, javax.swing.GroupLayout.Alignment.TRAILING, 200, 200, 200))
                                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel1)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                        .addComponent(btnPlanos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAssinantes, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80)
                                .addComponent(btnSair, 60, 60, 60)
                                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup().addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup().addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    // Variables
    private javax.swing.JButton btnAssinantes;
    private javax.swing.JButton btnPlanos;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }

}
