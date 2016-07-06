package br.com.cardgameshare.service;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.dto.ContatoDTO;
import br.com.cardgameshare.exception.ExcecaoNegocial;
import br.com.cardgameshare.repository.RepositoryFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ContatoService extends Service {

    public void enviarMensagem(ContatoDTO dto) throws ExcecaoNegocial {

        try {
            super.createContatoRepository().save(dto);
        } catch (Exception e) {
            super.tratarExcecaoNaoNegocial(e, "Problemas na validação do usuário!");
        }
    }

}
