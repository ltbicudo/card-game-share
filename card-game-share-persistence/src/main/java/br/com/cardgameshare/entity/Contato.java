package br.com.cardgameshare.entity;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "contato")
public class Contato extends Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_contato", nullable = false)
    private TipoContato tipoContato;

    @Column(name = "mensagem", length = 4000, nullable = false)
    private String mensagem;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "email", length = 100)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        // FIXME consertar quando o orika corrigir o converter
        if ("(null)".equals(nome)) {
            this.nome = null;
        } else {
            this.nome = nome;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // FIXME consertar quando o orika corrigir o converter
        if ("(null)".equals(email)) {
            this.email = null;
        } else {
            this.email = email;
        }
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }
}
