package br.com.cardgameshare.repository;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.entity.TipoContato;
import br.com.cardgameshare.entity.Usuario;
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

        super.em.getTransaction().begin();

        // FIXME Implementar via dozer a conversao entre dto e entity
        // garantir que os valores em branco na tela virem NULL
        Usuario usuarioFake = new Usuario();
        usuarioFake.setNome(dto.getNome());
        usuarioFake.setEmail(dto.getEmail());
        usuarioFake.setSenha(dto.getSenha());
        usuarioFake.setBloqueado(dto.getBloqueado());

        super.em.merge(usuarioFake);

        super.em.getTransaction().commit();
        super.em.close();

    }

    public List<CadastroDTO> buscarUsuarioPorEmail (CadastroDTO dto) {
        Session session = super.em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Usuario.class);
        List<Usuario> usuarios = (List<Usuario>) criteria.add(Restrictions.eq("email", dto.getEmail())).list();
        super.em.close();
        List<CadastroDTO> dtos = new ArrayList<CadastroDTO>();
        if(usuarios != null && !usuarios.isEmpty()) {
            for (Usuario usuario:
                 usuarios) {
                CadastroDTO cadastroDTO = new CadastroDTO();
                cadastroDTO.setNome(usuario.getNome());
                cadastroDTO.setEmail(usuario.getEmail());
                cadastroDTO.setBloqueado(usuario.getBloqueado());
                dtos.add(cadastroDTO);
            }
        }

        return dtos;
    }

}
