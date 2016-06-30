package br.com.cardgameshare.service;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.exception.ExcecaoNegocial;

import javax.ejb.Stateless;

@Stateless
public class UsuarioService extends Service {

    public void validarUsuarioParaCriacao(CadastroDTO dto) throws ExcecaoNegocial {

        try {

            if (!dto.getEmail().equals(dto.getConfirmacaoEmail())) {
                throw new ExcecaoNegocial("Emails não conferem");
            }

            if (!"Lucas".equals(dto.getNome())) {
                throw new ExcecaoNegocial("O nome deve ser Lucas!");
            }

        } catch (Exception e) {
            super.tratarExcecaoNaoNegocial(e, "Problemas na validação do usuário!");
        }
    }

}
