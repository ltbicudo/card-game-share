package br.com.cardgameshare.repository;

import br.com.cardgameshare.dto.ContatoDTO;
import br.com.cardgameshare.entity.Contato;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ContatoRepository extends Repository {

    public ContatoRepository(EntityManager em) {
        super.em = em;
    }

    public void save(ContatoDTO dto) {

        super.em.getTransaction().begin();

        Contato contatoFake = new Contato();
        contatoFake.setId(1L);
        contatoFake.setMensagem(dto.getMensagem());

        // TODO Implementar via dozer a conversao entre dto e entity
        super.em.merge(contatoFake); // TODO Mudar aqui para persistir a entity

        super.em.getTransaction().commit();

    }

}
