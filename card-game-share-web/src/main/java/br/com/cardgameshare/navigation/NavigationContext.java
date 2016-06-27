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
        @URLMapping(id = "cadastrar", pattern = "/cadastrar", viewId = "/WEB-INF/pages/cadastro/cadastro.xhtml")
    }
)
public class NavigationContext {

    public String direcionarParaTela(String mappingId) {
        return mappingId;
    }

}
