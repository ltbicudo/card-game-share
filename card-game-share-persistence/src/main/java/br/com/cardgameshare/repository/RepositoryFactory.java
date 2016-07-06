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

    private UsuarioRepository usuarioRepository = null;
    private ContatoRepository contatoRepository= null;

    public UsuarioRepository createUsuarioRepository() {
        if (this.usuarioRepository == null) {
            return new UsuarioRepository(em);
        }
        return this.usuarioRepository;
    }

    public ContatoRepository createContatoRepository() {
        if (this.contatoRepository == null) {
            return new ContatoRepository(em);
        }
        return this.contatoRepository;
    }

}
