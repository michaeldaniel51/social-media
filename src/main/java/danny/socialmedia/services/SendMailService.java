package danny.socialmedia.services;

import danny.socialmedia.entities.Mail;

import javax.mail.MessagingException;

public interface SendMailService {

        void sendMail(Mail mail);

        void sendMailWithAttachments(Mail mail) throws MessagingException;
    }



