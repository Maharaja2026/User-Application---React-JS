package com.user.controller;

import com.itextpdf.text.DocumentException;
import com.user.entity.User;
import com.user.service.UserServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/user")
public class UserController
{
    @Autowired
    UserServiceImpl service;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {
        System.out.println(user);
        service.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("login")
    public ResponseEntity<User> loginUser(@RequestParam String userEmail, @RequestParam String userPassword)
    {
        User user = service.loginUser(userEmail,userPassword);
        if(user != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return null;
    }

    @PostMapping("/sendOTP")
    public String sendOTP(@RequestParam("phoneNumber") String phoneNumber) {
        System.out.println(phoneNumber);
        String otp = service.sendOtp(phoneNumber);
        if(otp != null)
        {
            return otp;
        }
        return null;
    }

    @PostMapping("/validateOTP")
    public ResponseEntity<String> validateOTP(@RequestParam String userInputOtp) {
        boolean isValidate = service.validateOTP(userInputOtp);
        if(isValidate)
        {
            return ResponseEntity.status(HttpStatus.OK).body("Valid OTP Please reset your Password..!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP Please retry..!");
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestParam String newPassword,@RequestParam String reEnterPassword)
    {
        if (!newPassword.equals(reEnterPassword))
        {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }
        User user = service.resetPassword(newPassword);
        if(user != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body("Your Password reset Successfully..!");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Your Password reset failed..!");
    }

//******************************************************************


    @GetMapping("{userId}")
    public User findUserById(@PathVariable("userId") int userId)
    {
        return service.findUserById(userId);
    }

    @GetMapping("/getUser")
    public User findUserByEmail(@RequestParam String userEmail)
    {
        return service.findUserByEmail(userEmail);
    }


    @PutMapping("{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public User updateUser(@RequestBody User user,@PathVariable("userId") int userId)
    {
        return service.updateUser(user,userId);
    }

    @DeleteMapping("{userId}")
    @ResponseStatus(code = HttpStatus.OK)
    public User deleteUser(@PathVariable("userId") int userId)
    {
        return service.deleteUser(userId);
    }

    @GetMapping
    public List<User> findAllUsers()
    {
        return service.findAllUsers();
    }

//    @PostMapping("sendEmail")
//    public ResponseEntity sendEmail(@RequestParam String toEmail,@RequestParam String subject,@RequestParam String body)
//    {
//        service.sendEmail(toEmail,subject,body);
//        return ResponseEntity.status(HttpStatus.OK).body("Mail send succesfully");
//    }

    @PostMapping("sendPDFtoEmail")
    public String generatePdfAndSendEmail(@RequestParam String toEmail,@RequestParam String subject,@RequestParam String body,@RequestParam String attachmentName) throws DocumentException, MessagingException {
        List<String> data = Arrays.asList("Data 1", "Data 2", "Data 3");
        ByteArrayInputStream pdf = service.generatePdf(data);
        service.sendEmailWithAttachment(toEmail,subject,body,attachmentName,pdf);
        return "PDF generated and email sent!";
    }



}
