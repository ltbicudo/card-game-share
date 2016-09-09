package br.com.cardgameshare.repository;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.dto.CartaDTO;
import br.com.cardgameshare.dto.CartasUsuariosDTO;
import br.com.cardgameshare.entity.CartasUsuarios;
import br.com.cardgameshare.entity.CartasUsuariosPK;
import br.com.cardgameshare.entity.Usuario;
import br.com.cardgameshare.security.MD5Converter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CartasUsuariosRepository extends Repository {

    public CartasUsuariosRepository(EntityManager em) {
        super.em = em;
    }

    public List<CartasUsuariosDTO> listarCartasPorUsuario(Long idUsuario) {
        Session session = super.obterSession();
        Criteria criteria = session.createCriteria(CartasUsuarios.class);
        criteria.createAlias("usuario", "usuario");
        criteria.add(Restrictions.eq("usuario.id", idUsuario));
        criteria.setFetchMode("carta", FetchMode.JOIN);

        List<CartasUsuarios> cartasEncontradas = (List<CartasUsuarios>) criteria.list();
        super.fecharTransacao();

        List<CartasUsuariosDTO> cartasRetorno = new ArrayList<CartasUsuariosDTO>();
        if (cartasEncontradas != null && !cartasEncontradas.isEmpty()) {
            for (CartasUsuarios cartasUsuariosAtual : cartasEncontradas) {
                // FIXME mudar para orika
                CartasUsuariosDTO cartasUsuariosDTOAtual = new CartasUsuariosDTO();
                cartasUsuariosDTOAtual.setIdUsuario(idUsuario);
                cartasUsuariosDTOAtual.setIdCarta(cartasUsuariosAtual.getCarta().getId());
                cartasUsuariosDTOAtual.setNomeCarta(cartasUsuariosAtual.getCarta().getNome());
                cartasUsuariosDTOAtual.setIdColecao(cartasUsuariosAtual.getCarta().getColecao().getId());
                cartasUsuariosDTOAtual.setNomeColecao(cartasUsuariosAtual.getCarta().getColecao().getNome());
                cartasUsuariosDTOAtual.setCodigoColecao(cartasUsuariosAtual.getCarta().getColecao().getCodigo());
                cartasUsuariosDTOAtual.setQuantidade(cartasUsuariosAtual.getQuantidade());
                cartasRetorno.add(cartasUsuariosDTOAtual);
            }
        }

        return cartasRetorno;
    }

    public void salvar(CartasUsuariosDTO dto) {

        super.em.getTransaction().begin();

        // FIXME mudar para orika
        CartasUsuarios cartasUsuarios = new CartasUsuarios();
        CartasUsuariosPK cartasUsuariosPK = new CartasUsuariosPK();
        cartasUsuariosPK.setIdUsuario(dto.getIdUsuario());
        cartasUsuariosPK.setIdCarta(dto.getIdCarta());
        cartasUsuarios.setPk(cartasUsuariosPK);
        cartasUsuarios.setQuantidade(dto.getQuantidade());

        super.em.merge(cartasUsuarios);

        super.em.getTransaction().commit();
        super.em.close();

    }

}
