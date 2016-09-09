package br.com.cardgameshare.dto;

import java.util.List;

public class CartasUsuariosDTO extends DTO {

    private Long idUsuario;
    private Long idCarta;
    private String nomeCarta;
    private Long idColecao;
    private String nomeColecao;
    private String codigoColecao;
    private Long quantidade;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(Long idCarta) {
        this.idCarta = idCarta;
    }

    public Long getIdColecao() {
        return idColecao;
    }

    public void setIdColecao(Long idColecao) {
        this.idColecao = idColecao;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeCarta() {
        return nomeCarta;
    }

    public void setNomeCarta(String nomeCarta) {
        this.nomeCarta = nomeCarta;
    }

    public String getNomeColecao() {
        return nomeColecao;
    }

    public void setNomeColecao(String nomeColecao) {
        this.nomeColecao = nomeColecao;
    }

    public String getCodigoColecao() {
        return codigoColecao;
    }

    public void setCodigoColecao(String codigoColecao) {
        this.codigoColecao = codigoColecao;
    }
}
