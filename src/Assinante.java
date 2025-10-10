public class Assinante {
    private int idAssinante;
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    /* Metodo contrutor */

    public Assinante(int idAssinante, String nome, String cpf, String email, String senha){
        this.idAssinante = idAssinante;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    // Getters (para obter os valores)

    public int getIdAssinante() {
        return idAssinante;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    // Setters (para modificar os valores)

    public void setIdAssinante(int idAssinante) {
        this.idAssinante = idAssinante;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}

