package br.com.cardgameshare.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "contato")
public class Contato extends Entity {

    @Id
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;

    @Column(name = "mensagem")
    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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
