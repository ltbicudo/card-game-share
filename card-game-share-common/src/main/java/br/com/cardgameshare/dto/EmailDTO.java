package br.com.cardgameshare.dto;

import java.util.ArrayList;
import java.util.List;

public class EmailDTO extends DTO {

    private List<String> destinatarios;
    private String assunto;
    private String corpo;

    public List<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public void adicionarDestinatario(String destinatario) {
        if (this.destinatarios == null) {
            this.destinatarios = new ArrayList<String>();
        }
        this.destinatarios.add(destinatario);
    }

    public String getDestinatariosFormatadoEnvio() {
        if (this.destinatarios != null && !this.destinatarios.isEmpty()) {
            String token = "";
            StringBuilder bufferDestinatarios = new StringBuilder("");
            for (String destinatarioAtual : this.destinatarios) {
                bufferDestinatarios.append(token);
                bufferDestinatarios.append(destinatarioAtual);
                token = ", ";
            }
            return bufferDestinatarios.toString();
        }
        return "";
    }

}
