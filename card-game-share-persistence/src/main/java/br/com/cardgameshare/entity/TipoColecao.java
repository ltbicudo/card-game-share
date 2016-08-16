package br.com.cardgameshare.entity;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "tipo_colecao")
public class TipoColecao extends Entity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;

    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @Column(name = "codigo", length = 100, nullable = false)
    private String codigo;

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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
