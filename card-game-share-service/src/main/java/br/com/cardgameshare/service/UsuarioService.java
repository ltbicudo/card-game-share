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
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManagerFactory;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
        String senhaNova = "testeSenhaNova_123";

        // Salvar a senha na base de dados
        super.createUsuarioRepository().atualizarSenhaGeradaUsuario(dto.getEmailCadastro(), MD5Converter.convertStringToMd5(senhaNova));

        // Enviar email contendo a senha e instruções para se conectar usando essa senha nova
        this.enviarEmailTemp(dto.getEmailCadastro());

    }

    private void enviarEmailTemp(String remetente) {

        try {
            // Step1
            System.out.println("\n 1st ===> setup Mail Server Properties..");
            Properties mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            System.out.println("Mail Server Properties have been setup successfully..");

            // Step2
            System.out.println("\n\n 2nd ===> get Mail Session..");
            Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            MimeMessage generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(remetente));
            generateMailMessage.setSubject("Greetings from Crunchify..");
            String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
            generateMailMessage.setContent(emailBody, "text/html");
            System.out.println("Mail Session has been created successfully..");

            // Step3
            System.out.println("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password
            // if you have 2FA enabled then provide App Specific Password
            transport.connect("smtp.gmail.com", "cardgameshare@gmail.com", "slipKnot@1910");
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
