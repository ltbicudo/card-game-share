package br.com.cardgameshare.dto;

public class CartaColecaoDTO extends DTO {

    private Long idCarta;
    private Long idColecao;
    private String gathererURLImage;

    public Long getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(Long idCarta) {
        this.idCarta = idCarta;
    }

    public Long getIdColecao() {
        return idColecao;
    }

    public void setIdColecao(Long idColecao) {
        this.idColecao = idColecao;
    }

    public String getGathererURLImage() {
        return gathererURLImage;
    }

    public void setGathererURLImage(String gathererURLImage) {
        this.gathererURLImage = gathererURLImage;
    }
}
