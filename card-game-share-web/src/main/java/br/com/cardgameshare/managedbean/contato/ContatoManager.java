package br.com.cardgameshare.managedbean.contato;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.dto.ContatoDTO;
import br.com.cardgameshare.exception.ExcecaoNegocial;
import br.com.cardgameshare.service.UsuarioService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * ManagedBean para controle da tela de Contato
 */
@ManagedBean
@RequestScoped
public class ContatoManager {

    private ContatoDTO contatoDTO;

    @PostConstruct
    private void init() {
        this.contatoDTO = new ContatoDTO();
    }

    /**
     * Realiza o envio da mensagem de contato.
     *
     * @return
     */
    public String enviar() {

        /**
        try {
            this.usuarioService.validarUsuarioParaCriacao(this.cadastroDTO);
            return "pretty:inicio";
        } catch (ExcecaoNegocial e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
            return null;
        }
         */
        return null;
    }

    public ContatoDTO getContatoDTO() {
        return contatoDTO;
    }

    public void setContatoDTO(ContatoDTO contatoDTO) {
        this.contatoDTO = contatoDTO;
    }
}
