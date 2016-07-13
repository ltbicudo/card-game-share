package br.com.cardgameshare.service;

import br.com.cardgameshare.dto.CadastroDTO;
import br.com.cardgameshare.dto.LoginDTO;
import br.com.cardgameshare.entity.Usuario;
import br.com.cardgameshare.exception.ExcecaoNegocial;
import br.com.cardgameshare.repository.RepositoryFactory;
import br.com.cardgameshare.repository.UsuarioRepository;
import br.com.cardgameshare.security.MD5Converter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManagerFactory;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Stateless
@TransactionManagement
public class UsuarioService extends Service {

    public void validarUsuarioParaCriacao(CadastroDTO dto) throws ExcecaoNegocial {

        try {

            if (!dto.getEmail().equals(dto.getConfirmacaoEmail())) {
                throw new ExcecaoNegocial("Emails não conferem");
            }

            CadastroDTO usuarioEncontrato = super.createUsuarioRepository().buscarUsuarioPorEmail(dto.getEmail());
            if (usuarioEncontrato != null) {
                throw new ExcecaoNegocial("Usuário já cadastrado");
            }

            if (!dto.getSenha().equals(dto.getConfirmacaoSenha())) {
                throw new ExcecaoNegocial("Senhas não conferem");
            }

        } catch (Exception e) {
            e.printStackTrace(); // FIXME mudar para log
            super.tratarExcecaoNaoNegocial(e, "Problemas na validação do usuário!");
        }
    }

    public void cadastrar(CadastroDTO dto) throws ExcecaoNegocial {
        dto.setSenha(MD5Converter.convertStringToMd5(dto.getSenha()));
        dto.setBloqueado(Boolean.FALSE);
        super.createUsuarioRepository().salvar(dto);
    }

    public void validarUsuarioParaEntrar(LoginDTO dto) throws ExcecaoNegocial {

        try {

            // Validação do campo email
            if (dto.getEmail() == null || "".equals(dto.getEmail().trim())) {
                throw new ExcecaoNegocial("Informe seu email");
            }

            // Validação do campo senha
            if (dto.getSenha() == null || "".equals(dto.getSenha().trim())) {
                throw new ExcecaoNegocial("Informe sua senha");
            }

        } catch (Exception e) {
            e.printStackTrace(); // FIXME mudar para log
            super.tratarExcecaoNaoNegocial(e, "Problemas no login do usuário!");
        }
    }

    public void entrar(LoginDTO dto) throws ExcecaoNegocial {

        // Localizar a conta
        CadastroDTO usuarioEncontrado = super.createUsuarioRepository().buscarUsuarioPorEmail(dto.getEmail());
        if (usuarioEncontrado == null) {
            throw new ExcecaoNegocial("Conta não encontrada");
        }

        // Verificar a senha com a conta
        CadastroDTO usuarioLogin = super.createUsuarioRepository().validarSenhaUsuario(dto.getEmail(), dto.getSenha());
        if (usuarioLogin == null) {
            throw new ExcecaoNegocial("Senha inválida");
        }

        usuarioLogin.setDataUltimoLogin(new Date());
        super.createUsuarioRepository().atualizarUsuario(usuarioLogin);
    }

    public void enviarSenhaNova(LoginDTO dto) throws ExcecaoNegocial {

        // Localizar a conta
        CadastroDTO usuarioEncontrado = super.createUsuarioRepository().buscarUsuarioPorEmail(dto.getEmailCadastro());
        if (usuarioEncontrado == null) {
            throw new ExcecaoNegocial("Conta não encontrada");
        }

        // Geração da senha nova

        // Salvar a senha na base de dados

        // Enviar email contendo a senha e instruções para se conectar usando essa senha nova
    }

}
