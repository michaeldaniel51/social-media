package danny.socialmedia.controllers;


import danny.socialmedia.entities.Mail;
import danny.socialmedia.services.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {



     private final SendMailService sendMailService;



    @PostMapping("/send")
    public ResponseEntity<String> sendMail(@RequestBody Mail mail) {
        sendMailService.sendMail(mail);
        return new ResponseEntity<>("Email Sent successfully", HttpStatus.OK);
    }

    @PostMapping("/attachment")
    public ResponseEntity<String> sendAttachmentEmail(@RequestBody Mail mail) throws MessagingException {
        sendMailService.sendMailWithAttachments(mail);
        return new ResponseEntity<>("Attachment mail sent successfully", HttpStatus.OK);
    }
}





