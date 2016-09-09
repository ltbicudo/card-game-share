package br.com.cardgameshare.managedbean.mycards;

import br.com.cardgameshare.dto.CartaColecaoDTO;
import br.com.cardgameshare.dto.CartaDTO;
import br.com.cardgameshare.entity.Carta;
import br.com.cardgameshare.entity.CartasUsuarios;
import br.com.cardgameshare.entity.Colecao;
import br.com.cardgameshare.entity.Usuario;
import br.com.cardgameshare.managedbean.login.LoginManager;
import br.com.cardgameshare.service.CartaService;
import br.com.cardgameshare.service.UsuarioService;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import org.primefaces.component.button.Button;
import org.primefaces.component.column.Column;
import org.primefaces.component.dashboard.Dashboard;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.row.Row;
import org.primefaces.component.spacer.Spacer;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ManagedBean para controle da tela de Have List
 */
@ManagedBean
@SessionScoped
public class MyCardsManager {

    @EJB
    private CartaService cartaService;

    @EJB
    private UsuarioService usuarioService;

    private Usuario usuario;

    private String nomeCartaPesquisada;
    private CartaDTO cartaSelecionada;
    private String idCartaColecaoSelecionada;
    private Dashboard dashboardCartas;

    @PostConstruct
    private void init() {
    }

    @URLAction(mappingId = "my-cards")
    public String load() {
        if (!LoginManager.isUsuarioLogado()) {
            return "pretty:inicio";
        }

        this.prepararDashboardCartas();

        return null;
    }

    private void prepararDashboardCartas() {

        this.usuario = this.usuarioService.obterUsuarioPorId(LoginManager.obterUsuarioLogado().getId());

        if (this.usuario.getCartas().isEmpty()) {

        } else {

            FacesContext fc = FacesContext.getCurrentInstance();
            Application application = fc.getApplication();

            this.dashboardCartas = (Dashboard) application.createComponent(fc, "org.primefaces.component.Dashboard", "org.primefaces.component.DashboardRenderer");
            this.dashboardCartas.setId("dashboardCartas");

            DashboardModel model = new DefaultDashboardModel();
            DashboardColumn colunaEsquerda = new DefaultDashboardColumn();
            model.addColumn(colunaEsquerda);
            DashboardColumn colunaDireita = new DefaultDashboardColumn();
            model.addColumn(colunaDireita);
            this.dashboardCartas.setModel(model);

            // Ordenação da lista por coleção
            Collections.sort(this.usuario.getCartas(), new Comparator<CartasUsuarios>() {
                @Override
                public int compare(CartasUsuarios o1, CartasUsuarios o2) {
                    return o1.getCarta().getColecao().getId().compareTo(o2.getCarta().getColecao().getId());
                }
            });

            Long idColecaoAtual = null;
            boolean colunaAtualEsquerda = true;
            Panel panelAtual = null;
            PanelGrid panelGrid = null;

            for (CartasUsuarios cartasUsuariosAtual : this.usuario.getCartas()) {

                Colecao colecaoAtual = cartasUsuariosAtual.getCarta().getColecao();

                if (idColecaoAtual == null || !colecaoAtual.getId().equals(idColecaoAtual)) {
                    // Nova coleção

                    // Panel Coleção
                    panelAtual = new Panel();
                    panelAtual.setId("colecao_" + colecaoAtual.getCodigo());
                    panelAtual.setHeader(colecaoAtual.getNome() + " - " + colecaoAtual.getCodigo());
                    panelAtual.setStyleClass("dashboard-grid-custom");
                    panelAtual.setClosable(false);
                    panelAtual.setToggleable(true);
                    this.dashboardCartas.getChildren().add(panelAtual);
                    DashboardColumn column = model.getColumn(colunaAtualEsquerda ? 0 : 1);
                    column.addWidget(panelAtual.getId());

                    // Panel Grid Cartas
                    panelGrid = new PanelGrid();
                    panelGrid.setStyleClass("dashboard-panel-grid-custom");

                    // Linha
                    Row linha = new Row();
                    Column coluna = new Column();

                    // Carta
                    HtmlOutputText textoCarta = new HtmlOutputText();
                    textoCarta.setValue(cartasUsuariosAtual.getCarta().getNome());
                    coluna.getChildren().add(textoCarta);

                    // Quantidade

                    // Botão excluir
                    Button botaoExcluir = new Button();
                    botaoExcluir.setValue("Excluir");
                    coluna.getChildren().add(botaoExcluir);

                    linha.getChildren().add(coluna);
                    panelGrid.getChildren().add(linha);
                    panelAtual.getChildren().add(panelGrid);

                    // Troca da coluna
                    colunaAtualEsquerda = !colunaAtualEsquerda;
                    idColecaoAtual = colecaoAtual.getId();
                } else {
                    // Coleção já existente na lista

                    // Linha
                    Row linha = new Row();
                    Column coluna = new Column();

                    // Carta
                    HtmlOutputText textoCarta = new HtmlOutputText();
                    textoCarta.setValue(cartasUsuariosAtual.getCarta().getNome());
                    coluna.getChildren().add(textoCarta);

                    // Quantidade

                    // Botão excluir
                    Button botaoExcluir = new Button();
                    botaoExcluir.setValue("Excluir");
                    coluna.getChildren().add(botaoExcluir);

                    linha.getChildren().add(coluna);
                    panelGrid.getChildren().add(linha);
                    panelAtual.getChildren().add(panelGrid);
                }

            }

        }

    }

    public List<String> buscarCartas(String query) {
        return this.cartaService.listarPorNomeDistinct(query);
    }

    public void prepararCartaSelecionada() {
        CartaDTO cartaDTO = new CartaDTO();
        List<Carta> listaCartaColecao = this.cartaService.listarPorNome(this.nomeCartaPesquisada);
        List<CartaColecaoDTO> listaCartaColecaoDTO = new ArrayList<CartaColecaoDTO>();
        long contador = 0;
        for (Carta cartaAtual : listaCartaColecao) {
            CartaColecaoDTO cartaColecaoDTOAtual = new CartaColecaoDTO();
            cartaColecaoDTOAtual.setId(contador++);
            cartaColecaoDTOAtual.setIdCarta(cartaAtual.getId());
            cartaColecaoDTOAtual.setIdColecao(cartaAtual.getColecao().getId());
            cartaColecaoDTOAtual.setNomeColecao(cartaAtual.getColecao().getNome());
            cartaColecaoDTOAtual.setGathererURLImage(cartaAtual.getGathererURLImage());
            cartaColecaoDTOAtual.setUrlLogo(cartaAtual.getColecao().getUrlLogo());
            listaCartaColecaoDTO.add(cartaColecaoDTOAtual);
        }
        cartaDTO.setIndiceColecaoAtual(0);
        cartaDTO.setListaCartaColecao(listaCartaColecaoDTO);
        this.cartaSelecionada = cartaDTO;
    }

    public void selecionarCartaColecao() {
        this.cartaSelecionada.setIndiceColecaoAtual(Integer.parseInt(this.idCartaColecaoSelecionada));
    }

    public CartaDTO getCartaSelecionada() {
        return cartaSelecionada;
    }

    public void setCartaSelecionada(CartaDTO cartaSelecionada) {
        this.cartaSelecionada = cartaSelecionada;
    }

    public Dashboard getDashboardCartas() {
        return dashboardCartas;
    }

    public void setDashboardCartas(Dashboard dashboardCartas) {
        this.dashboardCartas = dashboardCartas;
    }

    public String getNomeCartaPesquisada() {
        return nomeCartaPesquisada;
    }

    public void setNomeCartaPesquisada(String nomeCartaPesquisada) {
        this.nomeCartaPesquisada = nomeCartaPesquisada;
    }

    public String getIdCartaColecaoSelecionada() {
        return idCartaColecaoSelecionada;
    }

    public void setIdCartaColecaoSelecionada(String idCartaColecaoSelecionada) {
        this.idCartaColecaoSelecionada = idCartaColecaoSelecionada;
    }
}
