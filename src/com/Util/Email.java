package com.Util;


import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *@author Miguel Angel de Pablo
 *<p>Util Class used to send email to any server from the account defined in myEmail attribute</p>
 * */
public class Email {
    final String myEmail = "app.messages.management.agent@gmail.com";
    final String myPassword = "bqprueba2015";
    final String SMTPserver = "smtp.gmail.com";
    final String port = "465";
    String mailReceptor = null;
    String subject = null;
    String body = null;

    public Email(String mailReceptor, String subject,
            String body) {
        this.mailReceptor = mailReceptor;
        this.subject = subject;
        this.body = body;

        Properties props = new Properties();
        props.put("mail.smtp.user", myEmail);
        props.put("mail.smtp.host", SMTPserver);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        SecurityManager security = System.getSecurityManager();

        try {
            Authenticator auth = new autentificadorSMTP();
            Session session = Session.getInstance(props, auth);
            // session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(body);
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress(myEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    mailReceptor));
            Transport.send(msg);
        } catch (Exception mex) {
            mex.printStackTrace();
            //log fatal
        }

    }

    private class autentificadorSMTP extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myEmail, myPassword);
        }
    }

    

}