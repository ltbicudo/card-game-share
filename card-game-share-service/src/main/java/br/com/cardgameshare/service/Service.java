package br.com.cardgameshare.service;

import br.com.cardgameshare.exception.ExcecaoNegocial;
import br.com.cardgameshare.repository.RepositoryFactory;
import br.com.cardgameshare.repository.UsuarioRepository;

import javax.ejb.EJB;

public abstract class Service {

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

}
