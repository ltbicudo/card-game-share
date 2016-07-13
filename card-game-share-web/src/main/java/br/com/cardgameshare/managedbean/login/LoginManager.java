package br.com.cardgameshare.managedbean.login;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.dto.LoginDTO;
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
public class LoginManager {

    @EJB
    private UsuarioService usuarioService;

    private LoginDTO loginDTO;

    @PostConstruct
    private void init() {
        this.loginDTO = new LoginDTO();
    }

    /**
     * Realiza o login do usuário na aplicação.
     *
     * @return
     */
    public String entrar() {

        try {

            // Validação do usuário
            this.usuarioService.validarUsuarioParaEntrar(this.loginDTO);

            // Login com usuário
            this.usuarioService.entrar(this.loginDTO);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login realizado com sucesso", "")); // Possivelmente remover
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

            return "pretty:inicio";

        } catch (ExcecaoNegocial e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
            return null;
        }
    }

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }
}
