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

        // FIXME Implementar via dozer a conversao entre dto e entity
        // garantir que os valores em branco na tela virem NULL
        Contato contatoFake = new Contato();
        contatoFake.setMensagem(dto.getMensagem());
        contatoFake.setNome(dto.getNome());
        contatoFake.setEmail(dto.getEmail());

        super.em.merge(contatoFake);

        super.em.getTransaction().commit();

    }

}
