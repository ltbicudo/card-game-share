package br.com.cardgameshare.repository;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.entity.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsuarioRepository extends Repository {

    public UsuarioRepository(EntityManager em) {
        super.em = em;
    }

    public void salvar(CadastroDTO dto) {

        super.em.getTransaction().begin();

        // FIXME Implementar via dozer a conversao entre dto e entity
        // garantir que os valores em branco na tela virem NULL
        Usuario usuarioFake = new Usuario();;

        super.em.merge(usuarioFake);

        super.em.getTransaction().commit();
        super.em.close();

    }

}
