package com.antivirus.servicoassinatura.Views;

import com.antivirus.servicoassinatura.dao.AssinanteDAO;
import com.antivirus.servicoassinatura.dao.PlanoDAO;
import com.antivirus.servicoassinatura.model.Assinante;
import com.antivirus.servicoassinatura.model.Plano;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class TelaCadastroAssinante extends javax.swing.JFrame {

    private AssinanteDAO dao = new AssinanteDAO();
    private PlanoDAO planoDAO = new PlanoDAO();
    private TelaListaAssinante telaLista;
    private int idEdicao = -1;

    public TelaCadastroAssinante(TelaListaAssinante telaLista) {
        this.telaLista = telaLista;
        initComponents();
        setLocationRelativeTo(null);
        carregarPlanos();
    }

    public TelaCadastroAssinante(TelaListaAssinante telaLista, int id) {
        this(telaLista);
        this.idEdicao = id;
        carregarDados();
    }

    private void carregarPlanos() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Sem plano");
        try {
            List<Plano> planos = planoDAO.listarPlano();
            for (Plano p : planos) {
                model.addElement(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar planos");
        }
        comboPlano.setModel(model);
    }

    private void carregarDados() {
        Assinante a = dao.buscarPorId(idEdicao); // você vai criar esse método em 10s
        if (a != null) {
            txtNome.setText(a.getNome());
            txtCpf.setText(a.getCpf());
            txtEmail.setText(a.getEmail());
            txtSenha.setText(a.getSenha());
            if (a.getIdPlano() > 0) {
                for (int i = 0; i < comboPlano.getItemCount(); i++) {
                    Plano p = (Plano) comboPlano.getItemAt(i);
                    if (p != null && p.getIdPlano() == a.getIdPlano()) {
                        comboPlano.setSelectedIndex(i);
                        break;
                    }
                }
            }
            setTitle("Editar Assinante ID: " + idEdicao);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Layout completo (campos + combo box + botões)
        // ... (código abaixo)

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        comboPlano = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Assinante");

        jPanel1.setBackground(java.awt.Color.WHITE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setText("CADASTRO DE ASSINANTE");

        jLabel2.setText("Nome:");
        jLabel3.setText("CPF:");
        jLabel4.setText("Email:");
        jLabel5.setText("Senha:");
        jLabel6.setText("Plano:");

        comboPlano.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Plano) {
                    setText(((Plano) value).getNome());
                } else {
                    setText(value == null ? "" : value.toString());
                }
                return this;
            }
        });

        btnSalvar.setBackground(new java.awt.Color(0, 153, 51));
        btnSalvar.setForeground(java.awt.Color.WHITE);
        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(evt -> salvar());

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(evt -> this.dispose());

        // Layout (organizado)
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup()
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2).addComponent(txtNome, 400, 400, 400)
                                        .addComponent(jLabel3).addComponent(txtCpf, 200, 200, 200)
                                        .addComponent(jLabel4).addComponent(txtEmail, 400, 400, 400)
                                        .addComponent(jLabel5).addComponent(txtSenha, 200, 200, 200)
                                        .addComponent(jLabel6).addComponent(comboPlano, 300, 300, 300)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnCancelar, 120, 120, 120)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnSalvar, 120, 120, 120)))
                                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup()
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30).addComponent(jLabel1)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2).addComponent(txtNome, 40, 40, 40)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3).addComponent(txtCpf, 40, 40, 40)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4).addComponent(txtEmail, 40, 40, 40)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5).addComponent(txtSenha, 40, 40, 40)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6).addComponent(comboPlano, 40, 40, 40)
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
        Assinante a = new Assinante();
        a.setNome(txtNome.getText());
        a.setCpf(txtCpf.getText());
        a.setEmail(txtEmail.getText());
        a.setSenha(new String(txtSenha.getPassword()));

        Object selecionado = comboPlano.getSelectedItem();
        if (selecionado instanceof Plano) {
            a.setIdPlano(((Plano) selecionado).getIdPlano());
        } else {
            a.setIdPlano(0);
        }

        if (idEdicao == -1) {
            dao.cadastrarAssinante(a);
            JOptionPane.showMessageDialog(this, "Assinante cadastrado!");
        } else {
            a.setIdAssinante(idEdicao);
            dao.atualizarAssinante(a);
            JOptionPane.showMessageDialog(this, "Assinante atualizado!");
        }

        telaLista.recarregarTabela();
        this.dispose();
    }

    // Variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Object> comboPlano;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCpf, txtEmail, txtNome;
    private javax.swing.JPasswordField txtSenha;
    // End

    public static void main(String[] args) {
        new TelaCadastroAssinante(null).setVisible(true);
    }
}