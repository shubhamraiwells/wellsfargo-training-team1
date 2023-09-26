package com.banking.teamone.repository;

import com.banking.teamone.model.EmailDetails;

public interface EmailRepo {


    String sendEmail(EmailDetails details);
}
