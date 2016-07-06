package br.com.cardgameshare.repository;

import br.com.cardgameshare.dto.ContatoDTO;

import javax.persistence.EntityManager;

public class ContatoRepository extends Repository {

    public ContatoRepository(EntityManager em) {
        super.em = em;
    }

    public void save(ContatoDTO dto) {
        // TODO Implementar via dozer a conversao entre dto e entity
        super.em.merge(dto); // TODO Mudar aqui para persistir a entity
    }

}
