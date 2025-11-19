package com.antivirus.servicoassinatura.Views;

import com.antivirus.servicoassinatura.dao.AdministradorDAO;
import com.antivirus.servicoassinatura.model.Administrador;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {

    public TelaLogin() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Login - Sistema de Assinatura Antivirus");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 28));
        jLabel1.setForeground(java.awt.Color.WHITE);
        jLabel1.setText("LOGIN ADMINISTRADOR");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel2.setForeground(java.awt.Color.WHITE);
        jLabel2.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 16));
        txtEmail.setText("admin@admin.com");   // pra facilitar o teste

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18));
        jLabel3.setForeground(java.awt.Color.WHITE);
        jLabel3.setText("Senha:");

        txtSenha.setFont(new java.awt.Font("Segoe UI", 0, 16));
        txtSenha.setText("123");                // pra facilitar o teste

        btnEntrar.setBackground(new java.awt.Color(0, 153, 51));
        btnEntrar.setFont(new java.awt.Font("Segoe UI", 1, 18));
        btnEntrar.setForeground(java.awt.Color.WHITE);
        btnEntrar.setText("ENTRAR");
        btnEntrar.addActionListener(evt -> fazerLogin());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup().addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup()
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup()
                                                .addComponent(jLabel2).addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup()
                                                .addComponent(txtEmail, 250, 250, 250)
                                                .addComponent(txtSenha, 250, 250, 250)
                                                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup().addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60).addComponent(jLabel1)
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup().addComponent(jLabel2).addComponent(txtEmail, 40, 40, 40))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup().addComponent(jLabel3).addComponent(txtSenha, 40, 40, 40))
                        .addGap(30, 30, 30).addComponent(btnEntrar, 50, 50, 50)
                        .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));

        pack();
    }

    private void fazerLogin() {
        String email = txtEmail.getText().trim();
        String senha = new String(txtSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        AdministradorDAO dao = new AdministradorDAO();
        try {
            for (Administrador a : dao.listarAdministrador()) {
                if (a.getEmail().equals(email) && a.getSenha().equals(senha)) {
                    JOptionPane.showMessageDialog(this, "Bem-vindo, " + a.getNome() + "!");
                    new TelaPrincipal().setVisible(true);
                    this.dispose();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Email ou senha incorretos!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro de conexão com o banco");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaLogin().setVisible(true));
    }

    // Variaveis
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSenha;
}