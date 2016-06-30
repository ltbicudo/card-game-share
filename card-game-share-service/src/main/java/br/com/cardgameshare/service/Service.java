package br.com.cardgameshare.service;

import br.com.cardgameshare.exception.ExcecaoNegocial;

public abstract class Service {

    protected void tratarExcecaoNaoNegocial(Exception e, String message) throws ExcecaoNegocial {
        if (e instanceof ExcecaoNegocial) {
            throw (ExcecaoNegocial) e;
        }
        throw new ExcecaoNegocial(message, e);
    }

}
