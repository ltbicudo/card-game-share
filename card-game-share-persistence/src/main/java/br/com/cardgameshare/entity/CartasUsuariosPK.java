package br.com.cardgameshare.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartasUsuariosPK extends Entity {

    @Column(name = "id_usuario", unique = true, nullable = false, precision = 22)
    private Long idUsuario;

    @Column(name = "id_carta",unique = true, nullable = false, precision = 22)
    private Long idCarta;

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
}
