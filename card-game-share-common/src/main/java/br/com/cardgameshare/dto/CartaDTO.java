package br.com.cardgameshare.dto;

import java.util.List;

public class CartaDTO extends DTO {

    private String nome;
    private List<CartaColecaoDTO> listaCartaColecao;
    private int indiceColecaoAtual;

    public CartaColecaoDTO cartaColecaoAtual() {
        if (this.listaCartaColecao != null && !this.listaCartaColecao.isEmpty()) {
            return this.listaCartaColecao.get(indiceColecaoAtual);
        }
        return null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<CartaColecaoDTO> getListaCartaColecao() {
        return listaCartaColecao;
    }

    public void setListaCartaColecao(List<CartaColecaoDTO> listaCartaColecao) {
        this.listaCartaColecao = listaCartaColecao;
    }

    public int getIndiceColecaoAtual() {
        return indiceColecaoAtual;
    }

    public void setIndiceColecaoAtual(int indiceColecaoAtual) {
        this.indiceColecaoAtual = indiceColecaoAtual;
    }
}
