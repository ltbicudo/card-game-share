package br.com.cardgameshare.service;

import br.com.cardgameshare.exception.ExcecaoNegocial;
import br.com.cardgameshare.repository.*;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.inject.Named;

@Singleton
public class Service {

    @EJB
    private RepositoryFactory repositoryFactory;

    protected void tratarExcecaoNaoNegocial(Exception e, String message) throws ExcecaoNegocial {
        if (e instanceof ExcecaoNegocial) {
            throw (ExcecaoNegocial) e;
        }
        throw new ExcecaoNegocial(message, e);
    }

    protected UsuarioRepository createUsuarioRepository() {
        return this.repositoryFactory.createUsuarioRepository();
    }

    protected ContatoRepository createContatoRepository() {
        return this.repositoryFactory.createContatoRepository();
    }

    protected CartaRepository createCartaRepository() {
        return this.repositoryFactory.createCartaRepository();
    }

    protected CartasUsuariosRepository createCartasUsuariosRepository() {
        return this.repositoryFactory.createCartasUsuariosRepository();
    }
}
