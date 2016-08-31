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

    public static boolean isUsuarioLogado() {
        return "S".equals(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CGS_INDICADOR_USUARIO_LOGADO"));
    }

    public static CadastroDTO obterUsuarioLogado() {
        return (CadastroDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CGS_USUARIO_LOGADO");
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
            CadastroDTO usuarioLogin = this.usuarioService.entrar(this.loginDTO);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CGS_INDICADOR_USUARIO_LOGADO", "S");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CGS_USUARIO_LOGADO", usuarioLogin);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login realizado com sucesso", "")); // Possivelmente remover
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

            return "pretty:my-cards";

        } catch (ExcecaoNegocial e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
            return null;
        }
    }

    /**
     * Realiza o logout do usuário na aplicação.
     *
     * @return
     */
    public String sair() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("CGS_INDICADOR_USUARIO_LOGADO");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("CGS_USUARIO_LOGADO");
        return "pretty:inicio";
    }

    /**
     * Realiza o envio de uma nova senha para o email de cadastro.
     *
     * @return
     */
    public String enviarSenhaNova() {

        try {
            // Validação do usuário
            this.usuarioService.enviarSenhaNova(this.loginDTO);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Uma senha nova foi enviada para o email de cadastro", ""));

            return null;

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
