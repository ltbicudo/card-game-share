package br.com.cardgameshare.entity;

import javax.persistence.*;
import java.util.Date;

@javax.persistence.Entity
@Table(name = "colecao")
public class Colecao extends Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "codigo", length = 3, nullable = false)
    private String codigo;

    @Column(name = "data_lancamento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLancamento;

    @Column(name = "url_logo", length = 100)
    private String urlLogo;

    @ManyToOne
    @JoinColumn(name = "id_borda", nullable = false)
    private Borda borda;

    @ManyToOne
    @JoinColumn(name = "id_tipo_colecao", nullable = false)
    private TipoColecao tipoColecao;

    @ManyToOne
    @JoinColumn(name = "id_bloco")
    private Bloco bloco;

    //private String jsonGetererCode;
    //private String jsonMagicCardsInfoCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Borda getBorda() {
        return borda;
    }

    public void setBorda(Borda borda) {
        this.borda = borda;
    }

    public TipoColecao getTipoColecao() {
        return tipoColecao;
    }

    public void setTipoColecao(TipoColecao tipoColecao) {
        this.tipoColecao = tipoColecao;
    }

//    public String getJsonGetererCode() {
//        return jsonGetererCode;
//    }

//    public void setJsonGetererCode(String jsonGetererCode) {
//        this.jsonGetererCode = jsonGetererCode;
//    }
//
//    public String getJsonMagicCardsInfoCode() {
//        return jsonMagicCardsInfoCode;
//    }
//
//    public void setJsonMagicCardsInfoCode(String jsonMagicCardsInfoCode) {
//        this.jsonMagicCardsInfoCode = jsonMagicCardsInfoCode;
//    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
}
