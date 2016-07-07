package br.com.cardgameshare.service;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.dto.ContatoDTO;
import br.com.cardgameshare.entity.TipoContato;
import br.com.cardgameshare.exception.ExcecaoNegocial;
import br.com.cardgameshare.repository.RepositoryFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionManagement()
public class ContatoService extends Service {

    public void enviarMensagem(ContatoDTO dto) throws ExcecaoNegocial {

        try {
            super.createContatoRepository().save(dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME mudar para log
            super.tratarExcecaoNaoNegocial(e, "Problemas ao enviar a mensagem!");
        }
    }

    public List<TipoContato> listarTiposContato() {
        return super.createContatoRepository().listarTiposContato();
    }

}
