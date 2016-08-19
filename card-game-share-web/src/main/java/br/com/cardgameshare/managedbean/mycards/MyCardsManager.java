package br.com.cardgameshare.managedbean.mycards;

import br.com.cardgameshare.entity.Carta;
import br.com.cardgameshare.service.CartaService;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
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
    private Dashboard dashboardCartas;

    @PostConstruct
    private void init() {
    }

    @URLAction(mappingId = "my-cards")
    public String load() {
        // TODO descomentar código abaixo
//        if (!LoginManager.isUsuarioLogado()) {
//            return "pretty:inicio";
//        }

        this.prepararDashboardCartas();

        return null;
    }

    private void prepararDashboardCartas() {

        FacesContext fc = FacesContext.getCurrentInstance();
        Application application = fc.getApplication();

        this.dashboardCartas = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
        this.dashboardCartas.setId("dashboardCartas");

        DashboardModel model = new DefaultDashboardModel();
        DashboardColumn coluna01 = new DefaultDashboardColumn();
        model.addColumn(coluna01);
        DashboardColumn coluna02 = new DefaultDashboardColumn();
        model.addColumn(coluna02);
        this.dashboardCartas.setModel(model);

        int items = 5;

        for( int i = 0, n = items; i < n; i++ ) {
            Panel panel = (Panel) application.createComponent(fc, "org.primefaces.component.Panel", "org.primefaces.component.PanelRenderer");
            panel.setId("colecao" + i);
            panel.setHeader("Header Colecao " + i);
            panel.setStyleClass("dashboard-grid-custom");
            panel.setClosable(false);
            panel.setToggleable(true);

            this.dashboardCartas.getChildren().add(panel);

            DashboardColumn column = model.getColumn(i%2);
            column.addWidget(panel.getId());
            HtmlOutputText text = new HtmlOutputText();
            text.setValue("Texto colecao " + i);

            panel.getChildren().add(text);
        }

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

    public Dashboard getDashboardCartas() {
        return dashboardCartas;
    }

    public void setDashboardCartas(Dashboard dashboardCartas) {
        this.dashboardCartas = dashboardCartas;
    }
}
