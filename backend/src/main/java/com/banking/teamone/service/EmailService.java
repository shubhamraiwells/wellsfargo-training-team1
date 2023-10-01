package com.banking.teamone.service;



import com.banking.teamone.model.EmailDetails;
import com.banking.teamone.repository.EmailRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService  {


    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String sender;


    /**
     * Sends an email using the provided EmailDetails object.
     *
     * @param details The EmailDetails object containing recipient, subject, and message body.
     * @return A message indicating the result of the email sending operation.
     */

    public String sendEmail(EmailDetails details) {
        try{
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            // Return success message if the email is sent successfully

            return "Mail Sent successfully";
        }catch(Exception e){
            // Return an error message if there's an exception while sending the email

            return "Error while sending Mail";
        }
    }
}