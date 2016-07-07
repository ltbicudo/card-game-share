package br.com.cardgameshare.repository;

import br.com.cardgameshare.dto.ContatoDTO;
import br.com.cardgameshare.entity.Contato;
import br.com.cardgameshare.entity.TipoContato;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.criteria.CriteriaBuilderImpl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ContatoRepository extends Repository {

    public ContatoRepository(EntityManager em) {
        super.em = em;
    }

    public void save(ContatoDTO dto) {

        super.em.getTransaction().begin();

        // FIXME Implementar via dozer a conversao entre dto e entity
        // garantir que os valores em branco na tela virem NULL
        Contato contatoFake = new Contato();
        contatoFake.setTipoContato(super.em.find(TipoContato.class, dto.getMotivo()));
        contatoFake.setMensagem(dto.getMensagem());
        contatoFake.setNome(dto.getNome());
        contatoFake.setEmail(dto.getEmail());

        super.em.merge(contatoFake);

        super.em.getTransaction().commit();

    }

    public List<TipoContato> listarTiposContato() {
        Session session = super.em.unwrap(Session.class);
        Criteria criteriaTipoContato = session.createCriteria(TipoContato.class);
        return criteriaTipoContato.list();
    }

}
