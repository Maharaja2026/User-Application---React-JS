package com.user.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.user.config.TwilioConfig;
import com.user.dao.UserDaoImpl;
import com.user.dto.PasswordResetRequestDto;
import com.user.dto.PasswordResetResponseDto;
import com.user.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDaoImpl dao;

    @Autowired
    private TwilioConfig twilioConfig;

    @Autowired
    private JavaMailSender mailSender;

    public User saveUser(User user)
    {
        dao.saveUser(user);
        return user;
    }

    public User findUserById(int userId)
    {
        return dao.findUserById(userId);
    }

    public User findUserByEmail(String userEmail)
    {
        return dao.findUserByEmail(userEmail);
    }

    public User updateUser(User user,int userId)
    {
        dao.updateUser(user,userId);
        return user;
    }

    public User deleteUser(int userId)
    {
        User user = findUserById(userId);
        dao.deleteUser(userId);
        return user;
    }

    public List<User> findAllUsers()
    {
        return dao.findAllUsers();
    }

    public User loginUser(String userEmail,String userPassword)
    {
        User user = dao.findUserByEmail(userEmail);
        if(user != null)
        {
            if(user.getUserPassword().equals(userPassword))
            {
                return user;
            }
        }
        return null;
    }

    @Override
    public PasswordResetResponseDto sendOtpForPasswordReset(PasswordResetRequestDto passwordResetRequestDto) {
        return null;
    }


    PasswordResetRequestDto storeOtp = new PasswordResetRequestDto();
    User tempUser = new User();

    public String sendOtp(String phoneNumber)
    {
        long userContact = Long.parseLong(phoneNumber.substring(3));
        System.out.println(userContact);
        tempUser.setUserContact(userContact);
        PhoneNumber to = new PhoneNumber(phoneNumber);
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
        String otp = generateOTP();
        storeOtp.setOneTimePassword(otp);
        String otpMessage = "Dear User, Your OTP is ##"+otp+"##.Thank You!";
        Message message = Message.creator(to,from,otpMessage).create();
        if(message != null)
        {
            if(otp != null)
            {
                return otp;
            }
        }
        return null;
    }
    public boolean validateOTP(String userInputOtp)
    {
        String otp = storeOtp.getOneTimePassword();
        System.out.println(otp);
        if(otp.equals(userInputOtp))
        {
            return true;
        }
        return false;
    }

    @Override
    public User resetPassword(String newPassword)
    {
        System.out.println(newPassword);
        long userContact = tempUser.getUserContact();
        System.out.println(userContact);
        User user = dao.findUserByContact(userContact);
        System.out.println(user);
        if(user != null)
        {
            user.setUserPassword(newPassword);
            dao.updateUser(user, user.getUserId());
            return user;
        }
        return null;
    }

    private String generateOTP()
    {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }

//    public void sendEmail(String toEmail,String subject,String body)
//    {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("maharaja2632002@gmail.com");
//        message.setTo(toEmail);
//        message.setSubject(subject);
//        message.setText(body);
//
//        mailSender.send(message);
//
//        System.out.println("Mail send succesfully");
//    }

    public ByteArrayInputStream generatePdf(List<String> data) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,outputStream);
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(BaseColor.CYAN);
        font.setSize(16);
        Paragraph paragraph = new Paragraph("Generated PDF",font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        for(String s : data)
        {
            document.add(new Paragraph(s));
        }
        document.close();
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    public void sendEmailWithAttachment(String toEmail, String subject, String body, String attachmentName, ByteArrayInputStream attachment) throws MessagingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body);
        Resource attachmentResource = new ByteArrayResource(attachment.readAllBytes());
        helper.addAttachment(attachmentName,attachmentResource);
        mailSender.send(message);
        System.out.println("PDF send to an Email successfully");
    }

}
