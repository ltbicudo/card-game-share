package br.com.cardgameshare.dto;

public class CartaColecaoDTO extends DTO {

    private Long idCarta;
    private Long idColecao;
    private String nomeColecao;
    private String gathererURLImage;
    private String urlLogo;

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

    public String getUrlLogo() {
        if (urlLogo != null) {
            return urlLogo;
        }
        return "";
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getNomeColecao() {
        return nomeColecao;
    }

    public void setNomeColecao(String nomeColecao) {
        this.nomeColecao = nomeColecao;
    }
}
