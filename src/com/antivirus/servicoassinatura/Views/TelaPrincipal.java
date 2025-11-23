package com.antivirus.servicoassinatura.Views;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
        configurarJanela();
    }

    private void configurarJanela() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setTitle("Sistema de Assinaturas - Painel Administrativo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ícone da janela
        try {
            setIconImage(new ImageIcon(getClass().getResource("/icones/escudo.png")).getImage());
        } catch (Exception ignored) {}

        // Rodapé com seu nome (igual às outras telas)
        JLabel rodape = new JLabel("Projeto PSSI - 2025 ©", JLabel.CENTER);
        rodape.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        rodape.setForeground(Color.GRAY);
        rodape.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        getContentPane().add(rodape, BorderLayout.SOUTH);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        btnPlanos = new JButton();
        btnAssinantes = new JButton();
        btnSair = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        jPanel1.setBackground(new Color(0, 102, 204));

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("PAINEL DO ADMINISTRADOR");
        jLabel1.setBorder(BorderFactory.createEmptyBorder(50, 0, 40, 0));

        // Painel com os dois botões grandes
        JPanel painelCentro = new JPanel();
        painelCentro.setOpaque(false);
        painelCentro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 80, 30, 80);

        btnPlanos.setBackground(new Color(0, 153, 204));
        btnPlanos.setFont(new Font("Segoe UI", Font.BOLD, 26));
        btnPlanos.setForeground(Color.WHITE);
        btnPlanos.setText("GERENCIAR PLANOS");
        btnPlanos.setPreferredSize(new Dimension(420, 130));
        btnPlanos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPlanos.setFocusPainted(false);
        btnPlanos.addActionListener(evt -> new TelaListaPlano().setVisible(true));
        // NÃO TEM NENHUM dispose() ou setVisible(false) → a tela principal FICA ABERTA!

        btnAssinantes.setBackground(new Color(0, 153, 51));
        btnAssinantes.setFont(new Font("Segoe UI", Font.BOLD, 26));
        btnAssinantes.setForeground(Color.WHITE);
        btnAssinantes.setText("GERENCIAR ASSINANTES");
        btnAssinantes.setPreferredSize(new Dimension(420, 130));
        btnAssinantes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAssinantes.setFocusPainted(false);
        btnAssinantes.addActionListener(evt -> new TelaListaAssinante().setVisible(true));
        // NENHUMA LINHA FECHA OU ESCONDE A TELA PRINCIPAL!

        gbc.gridx = 0; gbc.gridy = 0;
        painelCentro.add(btnPlanos, gbc);
        gbc.gridx = 1;
        painelCentro.add(btnAssinantes, gbc);

        // Botão Sair (embaixo à direita)
        JPanel painelSul = new JPanel(new FlowLayout(FlowLayout.RIGHT, 60, 30));
        painelSul.setOpaque(false);

        btnSair.setBackground(new Color(204, 0, 0));
        btnSair.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnSair.setForeground(Color.WHITE);
        btnSair.setText("SAIR DO SISTEMA");
        btnSair.setPreferredSize(new Dimension(280, 65));
        btnSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSair.setFocusPainted(false);
        btnSair.addActionListener(evt -> {
            if (JOptionPane.showConfirmDialog(this,
                    "Deseja realmente sair do sistema?", "Sair",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        painelSul.add(btnSair);

        // Montando tudo
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(jLabel1, BorderLayout.NORTH);
        jPanel1.add(painelCentro, BorderLayout.CENTER);
        jPanel1.add(painelSul, BorderLayout.SOUTH);

        getContentPane().add(jPanel1, BorderLayout.CENTER);
        pack();
    }

    // Variables
    private JButton btnAssinantes;
    private JButton btnPlanos;
    private JButton btnSair;
    private JLabel jLabel1;
    private JPanel jPanel1;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}