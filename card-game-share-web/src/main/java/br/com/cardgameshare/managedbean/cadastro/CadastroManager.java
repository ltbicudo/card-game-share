package br.com.cardgameshare.managedbean.cadastro;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.exception.ExcecaoNegocial;
import br.com.cardgameshare.service.UsuarioService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * ManagedBean para controle da tela de Cadastro
 */
@ManagedBean
@RequestScoped
public class CadastroManager {

    @EJB
    private UsuarioService usuarioService;

    private CadastroDTO cadastroDTO;

    @PostConstruct
    private void init() {
        this.cadastroDTO = new CadastroDTO();
    }

    /**
     * Realiza o cadastro de um novo usuário.
     *
     * @return
     */
    public String cadastrar() {

        try {

            // Validação do usuário
            this.usuarioService.validarUsuarioParaCriacao(this.cadastroDTO);

            // Salvar o novo usuário
            this.usuarioService.salvar(this.cadastroDTO);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso", ""));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

            return "pretty:inicio";

        } catch (ExcecaoNegocial e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
            return null;
        }
    }

    public CadastroDTO getCadastroDTO() {
        return cadastroDTO;
    }

    public void setCadastroDTO(CadastroDTO cadastroDTO) {
        this.cadastroDTO = cadastroDTO;
    }
}
