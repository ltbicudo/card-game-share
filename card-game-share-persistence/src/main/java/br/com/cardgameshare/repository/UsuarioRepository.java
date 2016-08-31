package br.com.cardgameshare.repository;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.entity.TipoContato;
import br.com.cardgameshare.entity.Usuario;
import br.com.cardgameshare.security.MD5Converter;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
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
            cadastroDTO.setId(usuarioEncontrado.getId());
            cadastroDTO.setNome(usuarioEncontrado.getNome());
            cadastroDTO.setEmail(usuarioEncontrado.getEmail());
            cadastroDTO.setSenha(usuarioEncontrado.getSenha());
            cadastroDTO.setBloqueado(usuarioEncontrado.getBloqueado());
            cadastroDTO.setDataUltimoLogin(usuarioEncontrado.getDataUltimoLogin());
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
            cadastroDTO.setId(usuarioEncontrado.getId());
            cadastroDTO.setNome(usuarioEncontrado.getNome());
            cadastroDTO.setEmail(usuarioEncontrado.getEmail());
            cadastroDTO.setSenha(usuarioEncontrado.getSenha());
            cadastroDTO.setBloqueado(usuarioEncontrado.getBloqueado());
            cadastroDTO.setDataUltimoLogin(usuarioEncontrado.getDataUltimoLogin());
            return cadastroDTO;
        }
        return null;
    }

    public void atualizarUsuario(CadastroDTO dto) {

        super.abrirTransacao();
        // FIXME mudar para orika
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setBloqueado(dto.getBloqueado());
        usuario.setDataUltimoLogin(dto.getDataUltimoLogin());

        super.em.merge(usuario);
        super.persistirTransacao();
        super.fecharTransacao();

    }

    public void atualizarSenhaGeradaUsuario(String email, String senha) {

        super.abrirTransacao();
        Session session = super.obterSession();
        String hql = "update Usuario u set u.senha = :senha where u.email = :email";
        Query query = session.createQuery(hql);
        query.setString("senha", senha);
        query.setString("email", email);
        query.executeUpdate();
        super.persistirTransacao();
        super.fecharTransacao();

    }

    public Usuario obterUsuarioPorId(Long idUsuario) {
        Session session = super.obterSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.eq("id", idUsuario));
        criteria.setFetchMode("cartas", FetchMode.JOIN);

        Usuario usuarioEncontrado = (Usuario) criteria.uniqueResult();
        super.fecharTransacao();
        return usuarioEncontrado;
    }

}
