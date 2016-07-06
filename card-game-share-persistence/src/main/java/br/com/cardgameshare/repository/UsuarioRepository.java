package br.com.cardgameshare.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UsuarioRepository extends Repository {

    public UsuarioRepository(EntityManager em) {
        super.em = em;
    }

}
