package br.com.cardgameshare.repository;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.entity.TipoContato;
import br.com.cardgameshare.entity.Usuario;
import br.com.cardgameshare.security.MD5Converter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository extends Repository {

    public UsuarioRepository(EntityManager em) {
        super.em = em;
    }

    public void salvar(CadastroDTO dto) {

        super.abrirTransacao();

        // FIXME Implementar via orika a conversao entre dto e entity
        // garantir que os valores em branco na tela virem NULL
        Usuario usuarioFake = new Usuario();
        usuarioFake.setNome(dto.getNome());
        usuarioFake.setEmail(dto.getEmail());
        usuarioFake.setSenha(dto.getSenha());
        usuarioFake.setBloqueado(dto.getBloqueado());

        super.em.merge(usuarioFake);

        super.persistirTransacao();
        super.fecharTransacao();

    }

    public CadastroDTO buscarUsuarioPorEmail(String email) {
        Session session = super.obterSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("email", email));

        Usuario usuarioEncontrado = (Usuario) criteria.uniqueResult();
        super.fecharTransacao();

        if(usuarioEncontrado != null) {
            // FIXME mudar para orika
            CadastroDTO cadastroDTO = new CadastroDTO();
            cadastroDTO.setNome(usuarioEncontrado.getNome());
            cadastroDTO.setEmail(usuarioEncontrado.getEmail());
            cadastroDTO.setBloqueado(usuarioEncontrado.getBloqueado());
            return cadastroDTO;
        }
        return null;
    }

    public CadastroDTO validarSenhaUsuario(String email, String senha) {
        Session session = super.obterSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("email", email));
        criteria.add(Restrictions.eq("senha", MD5Converter.convertStringToMd5(senha)));

        Usuario usuarioEncontrado = (Usuario) criteria.uniqueResult();
        super.fecharTransacao();

        if(usuarioEncontrado != null) {
            // FIXME mudar para orika
            CadastroDTO cadastroDTO = new CadastroDTO();
            cadastroDTO.setNome(usuarioEncontrado.getNome());
            cadastroDTO.setEmail(usuarioEncontrado.getEmail());
            cadastroDTO.setBloqueado(usuarioEncontrado.getBloqueado());
            return cadastroDTO;
        }
        return null;
    }

}
