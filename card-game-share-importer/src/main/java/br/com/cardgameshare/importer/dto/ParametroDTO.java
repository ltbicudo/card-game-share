package br.com.cardgameshare.importer.dto;

import java.sql.Types;

public class ParametroDTO {

    private String coluna;
    private Object valor;
    private int tipoColuna;

    public ParametroDTO(String coluna, Object valor, int tipoColuna) {
        this.coluna = coluna;
        this.valor = valor;
        this.tipoColuna = tipoColuna;
    }

    public String getColuna() {
        return coluna;
    }

    public void setColuna(String coluna) {
        this.coluna = coluna;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public int getTipoColuna() {
        return tipoColuna;
    }

    public void setTipoColuna(int tipoColuna) {
        this.tipoColuna = tipoColuna;
    }
}
