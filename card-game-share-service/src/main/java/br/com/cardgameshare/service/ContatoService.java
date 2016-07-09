package br.com.cardgameshare.service;

import br.com.cardgameshare.dto.ContatoDTO;
import br.com.cardgameshare.entity.TipoContato;
import br.com.cardgameshare.exception.ExcecaoNegocial;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;

@Stateless
public class ContatoService extends Service {

    public void enviarMensagem(ContatoDTO dto) throws ExcecaoNegocial {

        try {
            super.createContatoRepository().salvar(dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME mudar para log
            super.tratarExcecaoNaoNegocial(e, "Problemas ao enviar a mensagem!");
        }
    }

    public List<TipoContato> listarTiposContato() {
        return super.createContatoRepository().listarTiposContato();
    }

}
