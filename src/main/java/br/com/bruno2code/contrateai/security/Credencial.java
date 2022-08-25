package br.com.bruno2code.contrateai.security;

public class Credencial {

    private String login;
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Credencial(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }
}
