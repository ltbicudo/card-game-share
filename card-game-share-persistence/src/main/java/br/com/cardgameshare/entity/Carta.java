package br.com.cardgameshare.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Flavia on 7/3/2016.
 */
public class Carta {

    private Artista artista;
    private Integer custoManaConvertido;
    private List<Cor> cores;
    private String citacao;
    private String jsonId;
    private String nomeImagem;
    private Layout layout;
    private List<FormatoLegalidade> formatosLegalidades;
    private String custoMana;
    private String jsonMciNumber;
    private Long jsonMultiVerseId;
    private String nome;
    private List<String> names;
    private String numero;
    private String textoOriginal;
    private String tipoOriginal;
    private String poder;
    private List<Colecao> colecoesPresentes;
    private Raridade raridade;
    private List<SubTipo> subTipos;
    private String texto;
    private String resistencia;
    private List<Tipo> tipos;
    private SuperTipo superTipo;
    private Integer lealdade;
    private String marcaDAgua;
    private Borda borda;
    private Boolean datada;
    private Integer vanguardaMao;
    private Integer vanguardaVida;
    private Date dataLancamento;
    private String regras;
    private String nomesEstrangeiros;
    private String origemPromo;
    private Colecao colecao;

}
