package br.com.cardgameshare.managedbean.havelist;

import br.com.cardgameshare.dto.ContatoDTO;
import br.com.cardgameshare.entity.TipoContato;
import br.com.cardgameshare.exception.ExcecaoNegocial;
import br.com.cardgameshare.managedbean.login.LoginManager;
import br.com.cardgameshare.service.ContatoService;
import com.ocpsoft.pretty.faces.annotation.URLAction;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * ManagedBean para controle da tela de Have List
 */
@ManagedBean
@RequestScoped
public class HaveListManager {

    @PostConstruct
    private void init() {
    }

    @URLAction(mappingId = "have-list")
    public String load() {
        if (!LoginManager.isUsuarioLogado()) {
            return "pretty:inicio";
        }
        return null;
    }

}
