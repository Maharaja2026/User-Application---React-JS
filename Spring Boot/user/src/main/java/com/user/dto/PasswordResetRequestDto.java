package com.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PasswordResetRequestDto
{
    private String phoneNumber;
    private String userName;
    private String oneTimePassword;
}
