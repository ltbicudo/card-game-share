package br.com.cardgameshare.repository;

import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by ltbicudo on 05/07/2016.
 */
@Singleton
public class RepositoryFactory {

    private EntityManagerFactory entityManagerFactory;

    public RepositoryFactory() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("CardGameSharePU");
    }

    public UsuarioRepository createUsuarioRepository() {
        return new UsuarioRepository(entityManagerFactory.createEntityManager());
    }

    public ContatoRepository createContatoRepository() {
        return new ContatoRepository(entityManagerFactory.createEntityManager());
    }

    public CartaRepository createCartaRepository() {
        return new CartaRepository(entityManagerFactory.createEntityManager());
    }

    public CartasUsuariosRepository createCartasUsuariosRepository() {
        return new CartasUsuariosRepository(entityManagerFactory.createEntityManager());
    }

    @PreDestroy
    private void preDestroy() {
        this.entityManagerFactory.close();
    }

}
