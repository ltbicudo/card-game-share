package br.com.cardgameshare.service;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.entity.Usuario;
import br.com.cardgameshare.exception.ExcecaoNegocial;
import br.com.cardgameshare.repository.RepositoryFactory;
import br.com.cardgameshare.repository.UsuarioRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManagerFactory;

@Stateless
@TransactionManagement
public class UsuarioService extends Service {

    public void validarUsuarioParaCriacao(CadastroDTO dto) throws ExcecaoNegocial {

        try {

            if (!dto.getEmail().equals(dto.getConfirmacaoEmail())) {
                throw new ExcecaoNegocial("Emails não conferem");
            }

        } catch (Exception e) {
            e.printStackTrace(); // FIXME mudar para log
            super.tratarExcecaoNaoNegocial(e, "Problemas na validação do usuário!");
        }
    }

    public void salvar(CadastroDTO dto) throws ExcecaoNegocial {
        super.createUsuarioRepository().salvar(dto);
    }


}
