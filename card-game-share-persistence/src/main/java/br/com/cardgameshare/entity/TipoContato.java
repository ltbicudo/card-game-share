package br.com.cardgameshare.entity;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "tipo_contato")
public class TipoContato extends Entity {

    @Id
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;

    @Column(name = "descricao", length = 256, nullable = false)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
