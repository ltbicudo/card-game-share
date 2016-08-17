package br.com.cardgameshare.managedbean.mycards;

import br.com.cardgameshare.entity.Carta;
import br.com.cardgameshare.service.CartaService;
import com.ocpsoft.pretty.faces.annotation.URLAction;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * ManagedBean para controle da tela de Have List
 */
@ManagedBean
@RequestScoped
public class MyCardsManager {

    @EJB
    private CartaService cartaService;

    private Long cartaSelecionada;

    @PostConstruct
    private void init() {
    }

    @URLAction(mappingId = "my-cards")
    public String load() {
        // TODO descomentar código abaixo
//        if (!LoginManager.isUsuarioLogado()) {
//            return "pretty:inicio";
//        }
        return null;
    }

    public List<Carta> buscarCartas(String query) {
        List<Carta> resultadoConsulta = this.cartaService.listarPorNome(query);
        return resultadoConsulta;
    }

    public Long getCartaSelecionada() {
        return cartaSelecionada;
    }

    public void setCartaSelecionada(Long cartaSelecionada) {
        this.cartaSelecionada = cartaSelecionada;
    }
}
