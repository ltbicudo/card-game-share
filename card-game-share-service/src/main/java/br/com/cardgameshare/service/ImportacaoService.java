package br.com.cardgameshare.service;

import br.com.cardgameshare.entity.Carta;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;

/**
 * Created by fstockchneider on 15/07/2016.
 */
@Stateless
@TransactionManagement
public class ImportacaoService extends Service {

    public void cadastrar(Carta dto) {
        super.createCartaRepository().salvar(dto);
    }
}
