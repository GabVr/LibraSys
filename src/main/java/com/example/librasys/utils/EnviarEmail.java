package com.example.librasys.utils;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;

public class EnviarEmail {


    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private String port;

    @Value("${mail.smtp.username}")
    private static String remetente;

    @Value("${mail.smtp.password}")
    private static String senha;

        public static void main(String[] args) {
            Properties props = new Properties();
            /** Parâmetros de conexão com servidor Hotmail */
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "smtp.live.com");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props,
                    new jakarta.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication()
                        {
                            return new PasswordAuthentication(remetente, senha);
                        }
                    });
            session.setDebug(true);

            /** Ativa Debug para sessão */


            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(remetente)); //Remetente

                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(remetente)); //Destinatário(s)
                message.setSubject("Enviando email com JavaMail");//Assunto
                message.setText("Enviei este email utilizando JavaMail!");
                /**Método para enviar a mensagem criada*/
                Transport.send(message);

                System.out.println("Feito!!!");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
}
