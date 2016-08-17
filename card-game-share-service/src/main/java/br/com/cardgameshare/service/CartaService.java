package br.com.cardgameshare.service;

import br.com.cardgameshare.dto.ContatoDTO;
import br.com.cardgameshare.entity.Carta;
import br.com.cardgameshare.entity.TipoContato;
import br.com.cardgameshare.exception.ExcecaoNegocial;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CartaService extends Service {

    public List<Carta> listarPorNome(String nome) {
        return super.createCartaRepository().listarPorNome(nome);
    }

}
