package br.com.cardgameshare.repository;

import br.com.cardgameshare.entity.Carta;
import br.com.cardgameshare.entity.TipoContato;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import java.util.List;

public class CartaRepository extends Repository {

    public CartaRepository(EntityManager em) {
        super.em = em;
    }

    public List<String> listarPorNomeDistinct(String nome) {
        Session session = super.em.unwrap(Session.class);

        Criteria criteriaCarta = session.createCriteria(Carta.class);
        criteriaCarta.setProjection(Projections.projectionList()
                .add(Projections.property("nome"), "nome"));
        criteriaCarta.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
        criteriaCarta.addOrder(Order.asc("nome"));
        criteriaCarta.setProjection(Projections.distinct(Projections.property("nome")));

        List<String> listaCartas = criteriaCarta.list();
        super.em.close();
        return listaCartas;
    }

    public List<Carta> listarPorNome(String nome) {
        Session session = super.em.unwrap(Session.class);
        Criteria criteriaCarta = session.createCriteria(Carta.class);
        criteriaCarta.add(Restrictions.like("nome", nome, MatchMode.ANYWHERE));
        criteriaCarta.addOrder(Order.asc("nome"));
        List<Carta> listaCartas = criteriaCarta.list();
        super.em.close();
        return listaCartas;
    }

}
