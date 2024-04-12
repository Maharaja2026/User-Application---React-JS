package com.user.entity;


import com.user.dto.Address;
import com.user.dto.PhoneNumber;
import lombok.*;
import org.flywaydb.core.internal.util.JsonUtils;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patient
{
    private int medicalRecordNumber;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private Address address;
    private List<PhoneNumber> phoneNumbers;
    private String email;
}

