package com.user;

import com.twilio.Twilio;
import com.user.config.TwilioConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserAppication
{
    @Autowired
    private TwilioConfig twilioConfig;

    @PostConstruct
    public void initializeTwilio()
    {
        Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
    }

    public static void main(String[] args) {
        SpringApplication.run(UserAppication.class);
    }
}
