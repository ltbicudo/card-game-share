package br.com.cardgameshare.navigation;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.omnifaces.util.Faces;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * ManagedBean para controle dos fluxos de requisição da aplicação.
 *
 */
@ManagedBean(name = "nav", eager = true)
@ApplicationScoped
@URLMappings(mappings = {
        @URLMapping(id = "inicio", pattern = "/", viewId = "/WEB-INF/pages/landing.xhtml"),
        @URLMapping(id = "cadastrar", pattern = "/cadastrar", viewId = "/WEB-INF/pages/cadastro/cadastro.xhtml"),
        @URLMapping(id = "contato", pattern = "/contato", viewId = "/WEB-INF/pages/contato/contato.xhtml"),
        @URLMapping(id = "sell-list", pattern = "/sell-list", viewId = "/WEB-INF/pages/sell-list/sell-list.xhtml"),
        @URLMapping(id = "my-cards", pattern = "/my-cards", viewId = "/WEB-INF/pages/my-cards/my-cards.xhtml")
    }
)
public class NavigationContext {

    /**
     * Método para direcionar para o mapeamento pretty desejado.
     *
     * Deve-se sempre informar o valor desejado colocando o prefixo "pretty:" no texto.
     *
     * Exemplo:
     *  Para navegar para a tela de início devemos passar o seguinte parâmetro
     *      pretty:inicio
     *
     * @param mappingId mapeamento a ser utilizado
     * @return mapeamento que será navegado
     */
    public String direcionarParaTela(String mappingId) {
        return mappingId;
    }

}
