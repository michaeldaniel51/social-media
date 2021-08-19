package danny.socialmedia.services;

import danny.socialmedia.entities.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
public class SendMailServiceImpl implements SendMailService{

    private final JavaMailSender javaMailSender;



    @Override
    public void sendMail(Mail mail) {

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(mail.getRecipient(), mail.getRecipient());

            msg.setSubject(mail.getSubject());
            msg.setText(mail.getMessage());

            javaMailSender.send(msg);
        }


    @Override
    public void sendMailWithAttachments(Mail mail) throws MessagingException {
          MimeMessage msg = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(msg, true);

            helper.setTo("to_@email");

            helper.setSubject("Testing the email service");

            helper.setText("Get the attached image", true);

            helper.addAttachment("dan.jpg", new ClassPathResource("dan.jpg"));

            javaMailSender.send(msg);
        }
    }


