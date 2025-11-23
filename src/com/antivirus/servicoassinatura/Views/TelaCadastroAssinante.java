package com.antivirus.servicoassinatura.Views;

import com.antivirus.servicoassinatura.dao.AssinanteDAO;
import com.antivirus.servicoassinatura.dao.PlanoDAO;
import com.antivirus.servicoassinatura.model.Assinante;
import com.antivirus.servicoassinatura.model.Plano;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class TelaCadastroAssinante extends javax.swing.JFrame {

    private final AssinanteDAO dao = new AssinanteDAO();
    private final PlanoDAO planoDAO = new PlanoDAO();
    private final TelaListaAssinante telaLista;
    private int idEdicao = -1;

    public TelaCadastroAssinante(TelaListaAssinante telaLista) {
        this.telaLista = telaLista;
        initComponents();
        configurarJanela();
        carregarPlanos();
    }

    public TelaCadastroAssinante(TelaListaAssinante telaLista, int id) {
        this(telaLista);
        this.idEdicao = id;
        carregarDados();
        setTitle("Editar Assinante ID: " + id);
    }

    private void configurarJanela() {
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void carregarPlanos() {
        DefaultComboBoxModel<Plano> model = new DefaultComboBoxModel<>();

        // "Sem plano" com ID = 0
        model.addElement(new Plano(0, "Sem plano", "", BigDecimal.ZERO));

        try {
            List<Plano> planos = planoDAO.listarPlano();
            for (Plano p : planos) {
                model.addElement(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar planos: " + e.getMessage());
        }

        comboPlano.setModel(model);

        // Exibe apenas o nome no ComboBox
        comboPlano.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Plano) {
                    setText(((Plano) value).getNome());
                }
                return this;
            }
        });
    }

    private void carregarDados() {
        Assinante a = dao.buscarPorId(idEdicao);
        if (a != null) {
            txtNome.setText(a.getNome());
            txtCpf.setText(a.getCpf());
            txtEmail.setText(a.getEmail());
            txtSenha.setText(a.getSenha());

            // Seleciona o plano correto
            for (int i = 0; i < comboPlano.getItemCount(); i++) {
                Plano p = comboPlano.getItemAt(i);
                if (p != null && p.getIdPlano() == a.getIdPlano()) {
                    comboPlano.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void salvar() {
        try {
            Assinante a = new Assinante();
            a.setNome(txtNome.getText().trim());
            a.setCpf(txtCpf.getText().trim());
            a.setEmail(txtEmail.getText().trim());
            a.setSenha(new String(txtSenha.getPassword()));

            // SALVA O ID DO PLANO CORRETAMENTE
            Plano planoSelecionado = (Plano) comboPlano.getSelectedItem();
            int idPlano = (planoSelecionado != null && planoSelecionado.getIdPlano() > 0)
                    ? planoSelecionado.getIdPlano() : 0;
            a.setIdPlano(idPlano);

            if (idEdicao == -1) {
                dao.cadastrarAssinante(a);
                JOptionPane.showMessageDialog(this, "Assinante cadastrado com sucesso!");
            } else {
                a.setIdAssinante(idEdicao);
                dao.atualizarAssinante(a);
                JOptionPane.showMessageDialog(this, "Assinante atualizado com sucesso!");
            }

            if (telaLista != null) {
                telaLista.recarregarTabela();
            }
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new JPanel();
        jLabel1 = new JLabel("CADASTRO DE ASSINANTE");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel2 = new JLabel("Nome:");
        txtNome = new JTextField();
        jLabel3 = new JLabel("CPF:");
        txtCpf = new JTextField();
        jLabel4 = new JLabel("Email:");
        txtEmail = new JTextField();
        jLabel5 = new JLabel("Senha:");
        txtSenha = new JPasswordField();
        jLabel6 = new JLabel("Plano:");
        comboPlano = new JComboBox<>();
        btnSalvar = new JButton("SALVAR");
        btnCancelar = new JButton("CANCELAR");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Assinante");

        jPanel1.setBackground(Color.WHITE);

        btnSalvar.setBackground(new Color(0, 153, 51));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.addActionListener(e -> salvar());

        btnCancelar.addActionListener(e -> this.dispose());

        GroupLayout layoutPanel = new GroupLayout(jPanel1);
        jPanel1.setLayout(layoutPanel);
        layoutPanel.setHorizontalGroup(layoutPanel.createParallelGroup()
                .addGroup(layoutPanel.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layoutPanel.createParallelGroup()
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(txtNome, 400, 400, 400)
                                .addComponent(jLabel3)
                                .addComponent(txtCpf, 200, 200, 200)
                                .addComponent(jLabel4)
                                .addComponent(txtEmail, 400, 400, 400)
                                .addComponent(jLabel5)
                                .addComponent(txtSenha, 200, 200, 200)
                                .addComponent(jLabel6)
                                .addComponent(comboPlano, 300, 300, 300)
                                .addGroup(layoutPanel.createSequentialGroup()
                                        .addComponent(btnCancelar, 120, 120, 120)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSalvar, 120, 120, 120)))
                        .addGap(40, 40, 40))
        );
        layoutPanel.setVerticalGroup(layoutPanel.createParallelGroup()
                .addGroup(layoutPanel.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addComponent(txtNome, 40, 40, 40)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addComponent(txtCpf, 40, 40, 40)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addComponent(txtEmail, 40, 40, 40)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addComponent(txtSenha, 40, 40, 40)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addComponent(comboPlano, 40, 40, 40)
                        .addGap(40, 40, 40)
                        .addGroup(layoutPanel.createParallelGroup()
                                .addComponent(btnSalvar, 50, 50, 50)
                                .addComponent(btnCancelar, 50, 50, 50))
                        .addGap(30, 30, 30))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(jPanel1));
        layout.setVerticalGroup(layout.createParallelGroup().addComponent(jPanel1));

        pack();
    }

    // Variáveis de componentes
    private JButton btnCancelar;
    private JButton btnSalvar;
    private JComboBox<Plano> comboPlano;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    private JPanel jPanel1;
    private JTextField txtCpf, txtEmail, txtNome;
    private JPasswordField txtSenha;
}