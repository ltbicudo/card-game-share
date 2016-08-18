package br.com.cardgameshare.managedbean.mycards;

import br.com.cardgameshare.entity.Carta;
import br.com.cardgameshare.service.CartaService;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * ManagedBean para controle da tela de Have List
 */
@ManagedBean
@RequestScoped
public class MyCardsManager {

    @EJB
    private CartaService cartaService;

    private Carta cartaSelecionada;
    private DashboardModel dashboardModel;

    @PostConstruct
    private void init() {
    }

    @URLAction(mappingId = "my-cards")
    public String load() {
        // TODO descomentar código abaixo
//        if (!LoginManager.isUsuarioLogado()) {
//            return "pretty:inicio";
//        }

        this.prepararDashBoardCartas();

        return null;
    }

    private void prepararDashBoardCartas() {
        this.dashboardModel = new DefaultDashboardModel();
        DashboardColumn coluna1 = new DefaultDashboardColumn();
        coluna1.addWidget("colecao01");

        this.dashboardModel.addColumn(coluna1);
    }

    public List<Carta> buscarCartas(String query) {
        List<Carta> resultadoConsulta = this.cartaService.listarPorNome(query);
        return resultadoConsulta;
    }

    public Carta getCartaSelecionada() {
        return cartaSelecionada;
    }

    public void setCartaSelecionada(Carta cartaSelecionada) {
        this.cartaSelecionada = cartaSelecionada;
    }

    public DashboardModel getDashboardModel() {
        return dashboardModel;
    }

    public void setDashboardModel(DashboardModel dashboardModel) {
        this.dashboardModel = dashboardModel;
    }

}
