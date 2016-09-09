package br.com.cardgameshare.dto;

import java.util.List;

public class CartasUsuariosDTO extends DTO {

    private Long idUsuario;
    private CartaDTO carta;
    private Long quantidade;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public CartaDTO getCarta() {
        return carta;
    }

    public void setCarta(CartaDTO carta) {
        this.carta = carta;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
