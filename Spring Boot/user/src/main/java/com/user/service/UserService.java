package com.user.service;

import com.user.dto.PasswordResetRequestDto;
import com.user.dto.PasswordResetResponseDto;
import com.user.entity.User;

import java.util.List;

public interface UserService
{
    public User saveUser(User user);
    public User findUserById(int userId);
    public User updateUser(User user,int userId);
    public User deleteUser(int userId);
    public List<User> findAllUsers();
    public User loginUser(String userEmail,String userPassword);
    public PasswordResetResponseDto sendOtpForPasswordReset(PasswordResetRequestDto passwordResetRequestDto);
    public boolean validateOTP(String userInputOtp);

    public User resetPassword(String newPassword);

}
