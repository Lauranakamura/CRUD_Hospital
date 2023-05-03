import javax.swing.*;

import API.Logradouro;
import API.ClienteWS;

import bd.core.MeuResultSet;
import bd.daos.Hospitais;
import bd.dbos.Hospital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class Janela extends JFrame {
    // enumeração de modos do programa
    private enum Modo {
        NAVEGANDO, INCLUINDO, LISTANDO, ALTERANDO, EXCLUINDO
    }
    private Modo modo;

    // instancia todos os text fields da janela
    private JTextField txtIdRgCpf = new JTextField();
    private JTextField txtNome = new JTextField();
    private JTextField txtTelefone = new JTextField();
    private JTextField txtNascimento = new JTextField();
    private JTextField txtNaturalidade = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtCep = new JTextField();
    private JTextField txtComplemento = new JTextField();
    private JTextField txtLogradouro = new JTextField();
    private JTextField txtCidade = new JTextField();
    private JTextField txtEstado = new JTextField();

    // instancia todos as labels da janela
    private JLabel lbIdRgCpf = new JLabel("ID (RG/CPF):");
    private JLabel lbNome = new JLabel("Nome:");
    private JLabel lbTelefone = new JLabel("Telefone:");
    private JLabel lbNascimento = new JLabel("Nascimento:");
    private JLabel lbNaturalidade = new JLabel("Naturalidade:");
    private JLabel lbEmail = new JLabel("Email:");
    private JLabel lbCep = new JLabel ("CEP:");
    private JLabel lbNumero = new JLabel ("Número:");
    private JLabel lbComplemento = new JLabel ("Complemento:");
    private JLabel lbLogradouro = new JLabel ("Logradouro:");
    private JLabel lbCidade = new JLabel ("Cidade:");
    private JLabel lbEstado = new JLabel ("Estado:");

    // instancia todos os buttons da janela
    private JButton btnIncluir = new JButton("Incluir");
    private JButton btnListar = new JButton("Listar");
    private JButton btnAlterar = new JButton("Alterar");
    private JButton btnExcluir = new JButton("Excluir");

    private JButton btnProximo = new JButton("Próximo");
    private JButton btnExecutar = new JButton("Executar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JButton btnAnterior = new JButton("Anterior");


    // instancia o spinner para o número do endereço
    private JSpinner spNumero = new JSpinner(new SpinnerNumberModel(1, 1, 99999, 1));

    // variáveis para listagem dos dados
    private ArrayList<Hospital> pacientes = new ArrayList<>();
    private int indicePaciente = 0;

    public Janela() {
        super("CRUD Hospitais - Pacientes");

        // define as coordenadas e dimensões de cada componente
        this.lbIdRgCpf.setBounds(30, 40, 250, 25);
        this.txtIdRgCpf.setBounds(30, 75, 250, 25);
        this.lbNome.setBounds(30, 110, 250, 25);
        this.txtNome.setBounds(30, 145, 250, 25);
        this.lbTelefone.setBounds(30, 180, 250, 25);
        this.txtTelefone.setBounds(30, 215, 250, 25);
        this.lbNascimento.setBounds(30, 250, 250, 25);
        this.txtNascimento.setBounds(30, 285, 250, 25);
        this.lbNaturalidade.setBounds(30, 320, 250, 25);
        this.txtNaturalidade.setBounds(30, 355, 250, 25);
        this.lbEmail.setBounds(30, 390, 250, 25);
        this.txtEmail.setBounds(30, 425, 250, 25);
        this.lbCep.setBounds(30, 460, 250, 25);
        this.txtCep.setBounds(30, 495, 250, 25);
        this.lbNumero.setBounds(30, 530, 250, 25);
        this.spNumero.setBounds(30, 565, 250, 25);
        this.lbComplemento.setBounds(30, 600, 250, 25);
        this.txtComplemento.setBounds(30, 635, 250, 25);
        this.lbLogradouro.setBounds(310, 460, 250, 25);
        this.txtLogradouro.setBounds(310, 495, 250, 25);
        this.lbCidade.setBounds(310, 530, 250, 25);
        this.txtCidade.setBounds(310, 565, 250, 25);
        this.lbEstado.setBounds(310, 600, 250, 25);
        this.txtEstado.setBounds(310, 635, 250, 25);
        this.btnIncluir.setBounds(310, 75, 100, 25);
        this.btnListar.setBounds(310, 145, 100, 25);
        this.btnAlterar.setBounds(310, 215, 100, 25);
        this.btnExcluir.setBounds(310, 285, 100, 25);
        this.btnAnterior.setBounds(450, 75, 100, 25);
        this.btnExecutar.setBounds(450, 145, 100, 25);
        this.btnCancelar.setBounds(450, 215, 100, 25);
        this.btnProximo.setBounds(450, 285, 100, 25);

        // anexa todos os componentes à janela
        this.add(this.lbIdRgCpf);
        this.add(this.txtIdRgCpf);
        this.add(this.lbNome);
        this.add(this.txtNome);
        this.add(this.lbTelefone);
        this.add(this.txtTelefone);
        this.add(this.lbNascimento);
        this.add(this.txtNascimento);
        this.add(this.lbNaturalidade);
        this.add(this.txtNaturalidade);
        this.add(this.lbEmail);
        this.add(this.txtEmail);
        this.add(this.lbCep);
        this.add(this.txtCep);
        this.add(this.lbNumero);
        this.add(this.spNumero);
        this.add(this.lbComplemento);
        this.add(this.txtComplemento);
        this.add(this.lbLogradouro);
        this.add(this.txtLogradouro);
        this.add(this.lbCidade);
        this.add(this.txtCidade);
        this.add(this.lbEstado);
        this.add(this.txtEstado);
        this.add(this.btnIncluir);
        this.add(this.btnListar);
        this.add(this.btnAlterar);
        this.add(this.btnExcluir);
        this.add(this.btnAnterior);
        this.add(this.btnExecutar);
        this.add(this.btnCancelar);
        this.add(this.btnProximo);
        this.add(new JLabel(""));

        // define as configurações da janela
        this.setSize(700,710);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // desativa todos os campos por padrão, deixando apenas as operações padrão
        this.txtLogradouro.setEnabled(false);
        this.txtCidade.setEnabled(false);
        this.txtEstado.setEnabled(false);
        setBotoesAuxiliares(false);
        setCampos(false);

        // adiciona os listeners do eventos aos botões
        this.btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluir();
            }
        });

        this.btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listar();
            }
        });

        this.btnAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior();
            }
        });

        this.btnExecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executar();
            }
        });

        this.btnProximo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proximo();
            }
        });

        this.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });

        this.btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });

        this.btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterar();
            }
        });

        // adiciona o event listener relativo à saída do text field CEP
        this.txtCep.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                try {
                    exibirLogradouro(txtCep.getText());
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    txtCep.setText("");
                    txtCep.grabFocus();

                    txtLogradouro.setText("");
                    txtCidade.setText("");
                    txtEstado.setText("");
                }
            }
        });

        // define o modo como navegando
        modo = Modo.NAVEGANDO;
    }

    // define todos os campos de acordo com um dado estado
    private void setCampos(boolean estado)
    {
        this.txtIdRgCpf.setEnabled(estado);
        this.txtNome.setEnabled(estado);
        this.txtTelefone.setEnabled(estado);
        this.txtNascimento.setEnabled(estado);
        this.txtNaturalidade.setEnabled(estado);
        this.txtEmail.setEnabled(estado);
        this.txtCep.setEnabled(estado);
        this.spNumero.setEnabled(estado);
        this.txtComplemento.setEnabled(estado);
    }

    private void setBotoesPrincipais(boolean estado)
    {
        this.btnIncluir.setEnabled(estado);
        this.btnListar.setEnabled(estado);
        this.btnAlterar.setEnabled(estado);
        this.btnExcluir.setEnabled(estado);
    }

    private void setBotoesAuxiliares(boolean estado)
    {
        this.btnAnterior.setEnabled(estado);
        this.btnExecutar.setEnabled(estado);
        this.btnCancelar.setEnabled(estado);
        this.btnProximo.setEnabled(estado);
    }

    // limpa todos os campos
    private void limparCampos() {
        this.txtIdRgCpf.setText("");
        this.txtNome.setText("");
        this.txtTelefone.setText("");
        this.txtNascimento.setText("");
        this.txtNaturalidade.setText("");
        this.txtEmail.setText("");
        this.txtCep.setText("");
        this.spNumero.setValue(1);
        this.txtComplemento.setText("");
        this.txtLogradouro.setText("");
        this.txtCidade.setText("");
        this.txtEstado.setText("");
    }

    // obtém o logradouro de um dado cep
    private Logradouro getLogradouro(String cep) throws Exception {
        try {
            return (Logradouro) ClienteWS.getObjeto(Logradouro.class, "https://api.postmon.com.br/v1/cep/", cep);
        } catch (Exception ex) {
            throw new Exception("Erro ao consultar CEP");
        }
    }

    private void exibirPaciente(Hospital h) throws Exception
    {
        if (h == null)
            throw new Exception("Erro: Paciente inválido!");

        this.txtIdRgCpf.setText(h.getIdRgCpf());
        this.txtNome.setText(h.getNome());
        this.txtTelefone.setText(h.getTelefone());
        this.txtNascimento.setText(h.getNascimento());
        this.txtNaturalidade.setText(h.getNaturalidade());
        this.txtEmail.setText(h.getEmail());
        this.txtCep.setText(h.getCep());
        this.spNumero.setValue(h.getNumero());
        this.txtComplemento.setText(h.getComplemento());

        exibirLogradouro(h.getCep());
    }

    private void exibirLogradouro(String cep) throws Exception {
        try {
            Logradouro l = this.getLogradouro(cep);

            if (l == null)
                throw new Exception("Erro: Logradouro inválido!");

            this.txtLogradouro.setText(l.getLogradouro());
            this.txtCidade.setText(l.getCidade());
            this.txtEstado.setText(l.getEstado());
        } catch (Exception erro) {
            throw erro;
        }
    }

    // realiza a ação respectiva ao modo atual
    private void executar()
    {
        // se o modo é o listar
        if (modo == Modo.LISTANDO)
        {
            try {
                String id = txtIdRgCpf.getText();
                // verifica se vai listar um único paciente
                if (!id.equals(""))
                {
                    Hospital h = Hospitais.getPaciente(Integer.parseInt(id));
                    exibirPaciente(h);
                }

                // lista todos os pacientes
                else
                {
                    MeuResultSet resultado = Hospitais.getPacientes();

                    pacientes.clear();

                    while (resultado.next()) {
                        Hospital paciente =
                                new Hospital(resultado.getString("IDRGCPF"),
                                        resultado.getString("NOME"),
                                        resultado.getString("TELEFONE"),
                                        resultado.getString("NASCIMENTO"),
                                        resultado.getString("NATURALIDADE"),
                                        resultado.getString("EMAIL"),
                                        resultado.getString("CEP"),
                                        resultado.getInt("NUMERO"),
                                        resultado.getString("COMPLEMENTO"));

                        pacientes.add(paciente);
                    }

                    if (pacientes.size() == 0)
                        JOptionPane.showMessageDialog(null, "Sem pacientes cadastrados! Por favor, inserir.", "Listar", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception erro) {
                System.err.println(erro.getMessage());
            }
        }

        else if (modo == Modo.INCLUINDO)
        {
            try {
                try {
                    String id = txtIdRgCpf.getText();

                    Hospital h = new Hospital(
                            id,
                            this.txtNome.getText(),
                            this.txtTelefone.getText(),
                            this.txtNascimento.getText(),
                            this.txtNaturalidade.getText(),
                            this.txtEmail.getText(),
                            this.txtCep.getText(),
                            (int)this.spNumero.getValue(),
                            this.txtComplemento.getText()
                    );

                    Hospitais.incluir(h);
                    exibirPaciente(h);
                    JOptionPane.showMessageDialog(null, "O paciente foi incluído com sucesso!", "Incluir", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    limparCampos();
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (modo == Modo.EXCLUINDO)
        {
            try {
                try {
                    String id = txtIdRgCpf.getText();

                    if (id.equals("") || id.length() < 9)
                        JOptionPane.showMessageDialog(null, "O ID deve ser um RG ou CPF válido!", "Erro", JOptionPane.INFORMATION_MESSAGE);

                    else
                    {
                        Hospitais.excluir(id);

                        JOptionPane.showMessageDialog(null, "O paciente foi excluído com sucesso!", "Excluir", JOptionPane.INFORMATION_MESSAGE);

                        pacientes.clear();

                        limparCampos();
                    }
                  } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    limparCampos();
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        else if (modo == Modo.ALTERANDO)
        {
            try {
                try {
                    Hospital h = new Hospital(
                            this.txtIdRgCpf.getText(),
                            this.txtNome.getText(),
                            this.txtTelefone.getText(),
                            this.txtNascimento.getText(),
                            this.txtNaturalidade.getText(),
                            this.txtEmail.getText(),
                            this.txtCep.getText(),
                            (int)this.spNumero.getValue(),
                            this.txtComplemento.getText()
                    );

                    Hospitais.alterar(h);
                    exibirPaciente(h);
                    JOptionPane.showMessageDialog(null, "O paciente foi alterado com sucesso!", "Alterar", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    limparCampos();
                }
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        setCampos(false);
        setBotoesPrincipais(true);
        this.btnExecutar.setEnabled(false);
        this.btnCancelar.setEnabled(false);

        listarPacientes();

        modo = Modo.NAVEGANDO;
    }

    private void listarPacientes()
    {
        if (pacientes.size() > 0)
        {
            indicePaciente = 0;
            try {
                exibirPaciente(pacientes.get(0));
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

            if (pacientes.size() > 1)
            {
                btnAnterior.setEnabled(true);
                btnProximo.setEnabled(true);
            }
        }
    }

    // retroce para o paciente anterior
    private void anterior() {
        if (indicePaciente == 0)
            indicePaciente = pacientes.size() - 1;

        else
            indicePaciente--;

        try {
            exibirPaciente(pacientes.get(indicePaciente));
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // avança para o próximo paciente
    private void proximo() {
        if (indicePaciente + 1 == pacientes.size())
            indicePaciente = 0;

        else
            indicePaciente++;

        try {
            exibirPaciente(pacientes.get(indicePaciente));
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // prepara o programa para listar
    private void listar() {
        JOptionPane.showMessageDialog(null, "Digite um RG ou CPF para buscar um paciente ou execute para buscar todos!", "Listar", JOptionPane.INFORMATION_MESSAGE);

        limparCampos();

        setBotoesPrincipais(false);

        txtIdRgCpf.setEnabled(true);

        btnExecutar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(false);

        modo = Modo.LISTANDO;
    }

    // prepara o programa para incluir
    private void incluir() {
        JOptionPane.showMessageDialog(null, "Digite os dados do paciente e clique em executar!", "Incluir", JOptionPane.INFORMATION_MESSAGE);

        setBotoesPrincipais(false);

        limparCampos();
        setCampos(true);

        btnAnterior.setEnabled(false);
        btnExecutar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnProximo.setEnabled(false);

        modo = Modo.INCLUINDO;
    }

    // cancela a ação sendo executada
    private void cancelar()
    {
        if (JOptionPane.showConfirmDialog(null, "Deseja cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION) == 0)
        {
            limparCampos();

            setCampos(false);
            setBotoesPrincipais(true);
            setBotoesAuxiliares(false);

            listarPacientes();

            modo = Modo.NAVEGANDO;
        }
    }

    // prepara o programa para excluir
    private void excluir()
    {
        JOptionPane.showMessageDialog(null, "Digite o ID do paciente e clique em executar!", "Excluir", JOptionPane.INFORMATION_MESSAGE);

        setBotoesPrincipais(false);

        setCampos(false);
        txtIdRgCpf.setEnabled(true);

        btnExecutar.setEnabled(true);
        btnCancelar.setEnabled(true);

        modo = Modo.EXCLUINDO;
    }

    // prepara o programa para alterar
    private void alterar() {
        JOptionPane.showMessageDialog(null, "Altere os dados do paciente e clique em executar!", "Alterar", JOptionPane.INFORMATION_MESSAGE);

        setBotoesPrincipais(false);

        setCampos(true);

        btnExecutar.setEnabled(true);
        btnCancelar.setEnabled(true);

        modo = Modo.ALTERANDO;
    }
}