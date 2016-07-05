package br.com.cardgameshare.repository;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ltbicudo on 05/07/2016.
 */
@Singleton
public class RepositoryFactory {

    @PersistenceContext
    private EntityManager em;

    public UsuarioRepository createUsuarioRepository() {
        return new UsuarioRepository(em);
    }

}
