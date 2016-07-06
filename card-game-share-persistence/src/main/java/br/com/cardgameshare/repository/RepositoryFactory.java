package br.com.cardgameshare.repository;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * Created by ltbicudo on 05/07/2016.
 */
@Singleton
public class RepositoryFactory {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;

    private UsuarioRepository usuarioRepository = null;
    private ContatoRepository contatoRepository= null;

    public RepositoryFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("CardGameSharePU");
        em = entityManagerFactory.createEntityManager();
    }

    public UsuarioRepository createUsuarioRepository() {
        if (this.usuarioRepository == null) {
            this.usuarioRepository = new UsuarioRepository(em);
        }
        return this.usuarioRepository;
    }

    public ContatoRepository createContatoRepository() {
        if (this.contatoRepository == null) {
            this.contatoRepository = new ContatoRepository(em);
        }
        return this.contatoRepository;
    }

}
