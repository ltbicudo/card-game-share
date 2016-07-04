package br.com.cardgameshare.entity;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by Flavia on 7/3/2016.
 */
@javax.persistence.Entity
@Table(name = "artista")
public class Artista extends Entity {

    @Column(name = "nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
