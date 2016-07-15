package br.com.cardgameshare.repository;

import br.com.cardgameshare.entity.Carta;

import javax.persistence.EntityManager;

/**
 * Created by fstockchneider on 15/07/2016.
 */
public class CartaRepository extends Repository {

    public CartaRepository(EntityManager em) {
        super.em = em;
    }

    public void salvar(Carta dto) {

        super.abrirTransacao();

        // FIXME Receber DTO (hoje está recendo entity) e usar orika para converter

        super.em.merge(dto);

        super.persistirTransacao();
        super.fecharTransacao();
    }
}
