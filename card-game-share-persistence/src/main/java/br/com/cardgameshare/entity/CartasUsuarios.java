package br.com.cardgameshare.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Table(name = "cartas_usuarios")
public class CartasUsuarios extends Entity {

    @EmbeddedId
    private CartasUsuariosPK pk;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "id_carta", referencedColumnName = "id", insertable = false, updatable = false)
    private Carta carta;

    @Column(unique = true, nullable = false, precision = 22)
    private Long quantidade;

    public CartasUsuariosPK getPk() {
        return pk;
    }

    public void setPk(CartasUsuariosPK pk) {
        this.pk = pk;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
