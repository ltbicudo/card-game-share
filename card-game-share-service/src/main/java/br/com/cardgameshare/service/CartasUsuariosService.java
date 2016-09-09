package br.com.cardgameshare.service;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.dto.CartasUsuariosDTO;
import br.com.cardgameshare.dto.EmailDTO;
import br.com.cardgameshare.dto.LoginDTO;
import br.com.cardgameshare.entity.Usuario;
import br.com.cardgameshare.exception.ExcecaoNegocial;
import br.com.cardgameshare.mail.MailSender;
import br.com.cardgameshare.security.MD5Converter;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Stateless
@TransactionManagement
public class CartasUsuariosService extends Service {

    public List<CartasUsuariosDTO> listarCartasPorUsuario(Long idUsuario) {
        return super.createCartasUsuariosRepository().listarCartasPorUsuario(idUsuario);
    }

    public void salvar(CartasUsuariosDTO dto) {

        // TODO tratamento para cartas já existentes

        super.createCartasUsuariosRepository().salvar(dto);
    }

}
