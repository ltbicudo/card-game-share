package br.com.cardgameshare.dto;

import java.util.Date;
import java.util.List;

public class CadastroDTO extends DTO {

    private String nome;
    private String email;
    private String confirmacaoEmail;
    private String senha;
    private String confirmacaoSenha;
    private Boolean bloqueado;
    private Date dataUltimoLogin;
    private List<CartasUsuariosDTO> cartas;

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmacaoEmail() {
        return confirmacaoEmail;
    }

    public void setConfirmacaoEmail(String confirmacaoEmail) {
        this.confirmacaoEmail = confirmacaoEmail;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public Date getDataUltimoLogin() {
        return dataUltimoLogin;
    }

    public void setDataUltimoLogin(Date dataUltimoLogin) {
        this.dataUltimoLogin = dataUltimoLogin;
    }

    public List<CartasUsuariosDTO> getCartas() {
        return cartas;
    }

    public void setCartas(List<CartasUsuariosDTO> cartas) {
        this.cartas = cartas;
    }
}
