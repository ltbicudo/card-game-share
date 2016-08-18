package br.com.cardgameshare.managedbean.selllist;

import com.ocpsoft.pretty.faces.annotation.URLAction;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * ManagedBean para controle da tela de Have List
 */
@ManagedBean
@RequestScoped
public class SellListManager {

    @PostConstruct
    private void init() {
    }

    @URLAction(mappingId = "sell-list")
    public String load() {
        // TODO descomentar código abaixo
//        if (!LoginManager.isUsuarioLogado()) {
//            return "pretty:inicio";
//        }
        return null;
    }

}
