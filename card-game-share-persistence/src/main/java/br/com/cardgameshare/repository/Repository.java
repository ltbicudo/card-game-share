package br.com.cardgameshare.repository;

import org.hibernate.Session;

import javax.persistence.EntityManager;

/**
 * Created by lucas on 05/07/2016.
 */
public class Repository {

    protected EntityManager em;

    protected void abrirTransacao() {
        if (this.em != null) {
            this.em.getTransaction().begin();
        }
    }

    protected void persistirTransacao() {
        if (this.em != null) {
            this.em.getTransaction().commit();
        }
    }

    protected void fecharTransacao() {
        if (this.em != null) {
            this.em.close();
        }
    }

    protected Session obterSession() {
        if (this.em != null) {
            return this.em.unwrap(Session.class);
        }
        return null;
    }

}
