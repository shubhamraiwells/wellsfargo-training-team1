package com.banking.teamone.service;



import com.banking.teamone.model.EmailDetails;
import com.banking.teamone.repository.EmailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService implements EmailRepo {


    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendEmail(EmailDetails details) {
        try{
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail Sent successfully";
        }catch(Exception e){
            return "Error while sending Mail";
        }
    }
}