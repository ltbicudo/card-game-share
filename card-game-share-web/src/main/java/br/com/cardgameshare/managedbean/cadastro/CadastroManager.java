package br.com.cardgameshare.managedbean.cadastro;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * ManagedBean para controle da tela de Cdastro
 */
@ManagedBean
@RequestScoped
public class CadastroManager {

    public String cadastrar() {
        return "pretty:inicio";
    }

}
