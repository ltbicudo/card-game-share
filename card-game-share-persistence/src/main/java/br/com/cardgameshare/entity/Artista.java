package br.com.cardgameshare.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Flavia on 7/3/2016.
 */
@javax.persistence.Entity
@Table(name = "artista")
public class Artista extends Entity {

    @Id
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;

    @Column(name = "nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
