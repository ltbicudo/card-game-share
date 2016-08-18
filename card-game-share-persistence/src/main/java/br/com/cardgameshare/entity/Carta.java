package br.com.cardgameshare.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@javax.persistence.Entity
@Table(name = "carta")
public class Carta extends Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 22)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "custo_mana_convertido", precision = 22)
    private Long custoManaConvertido;

    @Column(name = "custo_mana", length = 100)
    private String custoMana;

    @Column(name = "numero", length = 100, nullable = false)
    private String numero;

    @Column(name = "texto", length = 4000)
    private String texto;

    @Column(name = "texto_original", length = 4000)
    private String textoOriginal;

    @ManyToOne
    @JoinColumn(name = "id_raridade")
    private Raridade raridade;

    @Column(name = "tipo", length = 100, nullable = false)
    private String tipo;

    @Column(name = "poder", length = 100)
    private String poder;

    @Column(name = "resistencia", length = 100)
    private String resistencia;

    @Column(name = "lealdade", precision = 22)
    private Long lealdade;

    @Column(name = "multiverse_id", precision = 22)
    private Long multiverseId;

    @ManyToMany
    @JoinTable(name = "tipos_cartas", joinColumns = {
            @JoinColumn(name = "id_carta", nullable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "id_tipo_carta", nullable = false) })
    private List<TipoCarta> tipos;

    @ManyToMany
    @JoinTable(name = "cores_cartas", joinColumns = {
            @JoinColumn(name = "id_carta", nullable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "id_cor", nullable = false) })
    private List<Cor> cores;

    @ManyToMany
    @JoinTable(name = "identificadores_cores_cartas", joinColumns = {
            @JoinColumn(name = "id_carta", nullable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "id_cor", nullable = false) })
    private List<Cor> identificadoresCores;

    @ManyToOne
    @JoinColumn(name = "id_layout")
    private Layout layout;

    @Column(name = "citacao", length = 4000)
    private String citacao;

    @Column(name = "json_id", length = 100)
    private String jsonId;

    @ManyToOne
    @JoinColumn(name = "id_artista")
    private Artista artista;

//    private String nomeImagem;
//    private List<FormatoLegalidade> formatosLegalidades;
//    private String jsonMciNumber;
//    private Long jsonMultiVerseId;
//    private List<String> names;
//    private String tipoOriginal;

//    private List<Colecao> colecoesPresentes;
//    private List<SubTipo> subTipos;

//    private SuperTipo superTipo;

//    private String marcaDAgua;
//    private Borda borda;
//    private Boolean datada;
//    private Integer vanguardaMao;
//    private Integer vanguardaVida;
//    private Date dataLancamento;
//    private String regras;
//    private String nomesEstrangeiros;
//    private String origemPromo;
//    private Colecao colecao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGathererURLImage() {
        if (this.multiverseId != null) {
            return "http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=" + this.multiverseId + "&type=card";
        }
        return "/resources/images/card-back.jpg";
    }

//    public Artista getArtista() {
//        return artista;
//    }
//
//    public void setArtista(Artista artista) {
//        this.artista = artista;
//    }

    public Long getCustoManaConvertido() {
        return custoManaConvertido;
    }

    public void setCustoManaConvertido(Long custoManaConvertido) {
        this.custoManaConvertido = custoManaConvertido;
    }

//    public List<Cor> getCores() {
//        return cores;
//    }
//
//    public void setCores(List<Cor> cores) {
//        this.cores = cores;
//    }
//
//    public String getCitacao() {
//        return citacao;
//    }
//
//    public void setCitacao(String citacao) {
//        this.citacao = citacao;
//    }

    public String getJsonId() {
        return jsonId;
    }

    public void setJsonId(String jsonId) {
        this.jsonId = jsonId;
    }

//    public String getNomeImagem() {
//        return nomeImagem;
//    }
//
//    public void setNomeImagem(String nomeImagem) {
//        this.nomeImagem = nomeImagem;
//    }
//
//    public Layout getLayout() {
//        return layout;
//    }
//
//    public void setLayout(Layout layout) {
//        this.layout = layout;
//    }
//
//    public List<FormatoLegalidade> getFormatosLegalidades() {
//        return formatosLegalidades;
//    }
//
//    public void setFormatosLegalidades(List<FormatoLegalidade> formatosLegalidades) {
//        this.formatosLegalidades = formatosLegalidades;
//    }
//
//    public String getCustoMana() {
//        return custoMana;
//    }
//
//    public void setCustoMana(String custoMana) {
//        this.custoMana = custoMana;
//    }
//
//    public String getJsonMciNumber() {
//        return jsonMciNumber;
//    }
//
//    public void setJsonMciNumber(String jsonMciNumber) {
//        this.jsonMciNumber = jsonMciNumber;
//    }
//
//    public Long getJsonMultiVerseId() {
//        return jsonMultiVerseId;
//    }
//
//    public void setJsonMultiVerseId(Long jsonMultiVerseId) {
//        this.jsonMultiVerseId = jsonMultiVerseId;
//    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public List<String> getNames() {
//        return names;
//    }
//
//    public void setNames(List<String> names) {
//        this.names = names;
//    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

//    public String getTextoOriginal() {
//        return textoOriginal;
//    }
//
//    public void setTextoOriginal(String textoOriginal) {
//        this.textoOriginal = textoOriginal;
//    }
//
//    public String getTipoOriginal() {
//        return tipoOriginal;
//    }
//
//    public void setTipoOriginal(String tipoOriginal) {
//        this.tipoOriginal = tipoOriginal;
//    }
//
//    public String getPoder() {
//        return poder;
//    }
//
//    public void setPoder(String poder) {
//        this.poder = poder;
//    }
//
//    public List<Colecao> getColecoesPresentes() {
//        return colecoesPresentes;
//    }
//
//    public void setColecoesPresentes(List<Colecao> colecoesPresentes) {
//        this.colecoesPresentes = colecoesPresentes;
//    }
//
//    public Raridade getRaridade() {
//        return raridade;
//    }
//
//    public void setRaridade(Raridade raridade) {
//        this.raridade = raridade;
//    }
//
//    public List<SubTipo> getSubTipos() {
//        return subTipos;
//    }
//
//    public void setSubTipos(List<SubTipo> subTipos) {
//        this.subTipos = subTipos;
//    }
//
//    public String getTexto() {
//        return texto;
//    }
//
//    public void setTexto(String texto) {
//        this.texto = texto;
//    }
//
//    public String getResistencia() {
//        return resistencia;
//    }
//
//    public void setResistencia(String resistencia) {
//        this.resistencia = resistencia;
//    }
//
//    public List<TipoCarta> getTipos() {
//        return tipos;
//    }
//
//    public void setTipos(List<TipoCarta> tipos) {
//        this.tipos = tipos;
//    }
//
//    public SuperTipo getSuperTipo() {
//        return superTipo;
//    }
//
//    public void setSuperTipo(SuperTipo superTipo) {
//        this.superTipo = superTipo;
//    }
//
//    public Integer getLealdade() {
//        return lealdade;
//    }
//
//    public void setLealdade(Integer lealdade) {
//        this.lealdade = lealdade;
//    }
//
//    public String getMarcaDAgua() {
//        return marcaDAgua;
//    }
//
//    public void setMarcaDAgua(String marcaDAgua) {
//        this.marcaDAgua = marcaDAgua;
//    }
//
//    public Borda getBorda() {
//        return borda;
//    }
//
//    public void setBorda(Borda borda) {
//        this.borda = borda;
//    }
//
//    public Boolean getDatada() {
//        return datada;
//    }
//
//    public void setDatada(Boolean datada) {
//        this.datada = datada;
//    }
//
//    public Integer getVanguardaMao() {
//        return vanguardaMao;
//    }
//
//    public void setVanguardaMao(Integer vanguardaMao) {
//        this.vanguardaMao = vanguardaMao;
//    }
//
//    public Integer getVanguardaVida() {
//        return vanguardaVida;
//    }
//
//    public void setVanguardaVida(Integer vanguardaVida) {
//        this.vanguardaVida = vanguardaVida;
//    }
//
//    public Date getDataLancamento() {
//        return dataLancamento;
//    }
//
//    public void setDataLancamento(Date dataLancamento) {
//        this.dataLancamento = dataLancamento;
//    }
//
//    public String getRegras() {
//        return regras;
//    }
//
//    public void setRegras(String regras) {
//        this.regras = regras;
//    }
//
//    public String getNomesEstrangeiros() {
//        return nomesEstrangeiros;
//    }
//
//    public void setNomesEstrangeiros(String nomesEstrangeiros) {
//        this.nomesEstrangeiros = nomesEstrangeiros;
//    }
//
//    public String getOrigemPromo() {
//        return origemPromo;
//    }
//
//    public void setOrigemPromo(String origemPromo) {
//        this.origemPromo = origemPromo;
//    }
//
//    public Colecao getColecao() {
//        return colecao;
//    }
//
//    public void setColecao(Colecao colecao) {
//        this.colecao = colecao;
//    }

    public Raridade getRaridade() {
        return raridade;
    }

    public void setRaridade(Raridade raridade) {
        this.raridade = raridade;
    }

    public String getCustoMana() {
        return custoMana;
    }

    public void setCustoMana(String custoMana) {
        this.custoMana = custoMana;
    }

    public String getCitacao() {
        return citacao;
    }

    public void setCitacao(String citacao) {
        this.citacao = citacao;
    }

    public List<TipoCarta> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoCarta> tipos) {
        this.tipos = tipos;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTextoOriginal() {
        return textoOriginal;
    }

    public void setTextoOriginal(String textoOriginal) {
        this.textoOriginal = textoOriginal;
    }

    public List<Cor> getCores() {
        return cores;
    }

    public void setCores(List<Cor> cores) {
        this.cores = cores;
    }

    public List<Cor> getIdentificadoresCores() {
        return identificadoresCores;
    }

    public void setIdentificadoresCores(List<Cor> identificadoresCores) {
        this.identificadoresCores = identificadoresCores;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPoder() {
        return poder;
    }

    public void setPoder(String poder) {
        this.poder = poder;
    }

    public String getResistencia() {
        return resistencia;
    }

    public void setResistencia(String resistencia) {
        this.resistencia = resistencia;
    }

    public Long getLealdade() {
        return lealdade;
    }

    public void setLealdade(Long lealdade) {
        this.lealdade = lealdade;
    }

    public Long getMultiverseId() {
        return multiverseId;
    }

    public void setMultiverseId(Long multiverseId) {
        this.multiverseId = multiverseId;
    }
}
