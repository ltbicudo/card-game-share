package br.com.cardgameshare.managedbean.contato;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.dto.ContatoDTO;
import br.com.cardgameshare.entity.TipoContato;
import br.com.cardgameshare.exception.ExcecaoNegocial;
import br.com.cardgameshare.service.ContatoService;
import br.com.cardgameshare.service.UsuarioService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * ManagedBean para controle da tela de Contato
 */
@ManagedBean
@RequestScoped
public class ContatoManager {

    @EJB
    private ContatoService contatoService;

    private ContatoDTO contatoDTO;
    private List<TipoContato> listaTipoContato;

    @PostConstruct
    private void init() {
        this.contatoDTO = new ContatoDTO();
        listaTipoContato = this.contatoService.listarTiposContato();
    }

    /**
     * Realiza o envio da mensagem de contato.
     *
     * @return
     */
    public String enviar() {

        try {
            this.contatoService.enviarMensagem(this.contatoDTO);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensagem enviada", ""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "pretty:inicio";
        } catch (ExcecaoNegocial e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
            return null;
        }
    }

    public ContatoDTO getContatoDTO() {
        return contatoDTO;
    }

    public void setContatoDTO(ContatoDTO contatoDTO) {
        this.contatoDTO = contatoDTO;
    }

    public List<TipoContato> getListaTipoContato() {
        return listaTipoContato;
    }

    public void setListaTipoContato(List<TipoContato> listaTipoContato) {
        this.listaTipoContato = listaTipoContato;
    }
}
