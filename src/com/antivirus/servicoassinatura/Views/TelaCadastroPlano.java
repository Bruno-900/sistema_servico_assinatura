package com.antivirus.servicoassinatura.Views;

import com.antivirus.servicoassinatura.dao.PlanoDAO;
import com.antivirus.servicoassinatura.model.Plano;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class TelaCadastroPlano extends javax.swing.JFrame {

    private PlanoDAO planoDAO = new PlanoDAO();
    private TelaListaPlano telaLista;
    private int idEdicao = -1;

    public TelaCadastroPlano(TelaListaPlano telaLista) {
        this.telaLista = telaLista;
        initComponents();
        setLocationRelativeTo(null);
    }

    public TelaCadastroPlano(TelaListaPlano telaLista, int id) {
        this(telaLista);
        this.idEdicao = id;
        carregarPlano();
    }

    private void carregarPlano() {
        Plano p = planoDAO.buscarPorId(idEdicao); // você vai criar esse método em 2 segundos
        if (p != null) {
            txtNome.setText(p.getNome());
            txtDescricao.setText(p.getDescricao());
            txtPreco.setText(p.getPreco().toString());
            setTitle("Editar Plano ID: " + idEdicao);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // layout igual ao anterior, só muda os campos
        // ... (código completo abaixo)
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtPreco = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Plano");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setText("CADASTRO DE PLANO");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jLabel2.setText("Nome do Plano:");

        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 14));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jLabel3.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jLabel4.setText("Preço (R$):");

        txtPreco.setFont(new java.awt.Font("Segoe UI", 0, 14));

        btnSalvar.setBackground(new java.awt.Color(0, 153, 51));
        btnSalvar.setFont(new java.awt.Font("Segoe UI", 1, 16));
        btnSalvar.setForeground(java.awt.Color.WHITE);
        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(evt -> salvar());

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(evt -> this.dispose());

        // layout aqui (bem simples, copia e cola)

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(txtNome, 400, 400, 400)
                                        .addComponent(jLabel3)
                                        .addComponent(jScrollPane1, 400, 400, 400)
                                        .addComponent(jLabel4)
                                        .addComponent(txtPreco, 200, 200, 200)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnCancelar, 120, 120, 120)
                                                .addGap(160, 160, 160)
                                                .addComponent(btnSalvar, 120, 120, 120)))
                                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addComponent(txtNome, 40, 40, 40)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addComponent(jScrollPane1, 100, 100, 100)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addComponent(txtPreco, 40, 40, 40)
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                        .addComponent(btnSalvar, 50, 50, 50)
                                        .addComponent(btnCancelar, 50, 50, 50))
                                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(jPanel1));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(jPanel1));

        pack();
    }

    private void salvar() {
        if (txtNome.getText().trim().isEmpty() || txtPreco.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        Plano p = new Plano();
        p.setNome(txtNome.getText());
        p.setDescricao(txtDescricao.getText());
        try {
            p.setPreco(new BigDecimal(txtPreco.getText().replace(",", ".")));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Preço inválido!");
            return;
        }

        if (idEdicao == -1) {
            planoDAO.cadastrarPlano(p);
            JOptionPane.showMessageDialog(this, "Plano cadastrado com sucesso!");
        } else {
            p.setIdPlano(idEdicao);
            planoDAO.atualizarPlano(p);  // corrigir o nome do método depois
            JOptionPane.showMessageDialog(this, "Plano atualizado com sucesso!");
        }

        telaLista.carregarTabelaNovamente();
        this.dispose();
    }

    // Variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPreco;
    // End

    public static void main(String[] args) {
        new TelaCadastroPlano(null).setVisible(true);
    }
}