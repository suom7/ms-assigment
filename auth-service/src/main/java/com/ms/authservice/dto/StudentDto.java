package com.ms.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentDto {
    /**
     * User Identity - username
     */
    private String studentId;
    /**
     * User portrait photo Url
     */
    private String portraitPhotoUrl;
    /**
     * Name
     */
    private String name;
    /**
     * Phone number
     */
    private String phoneNumber;
    /**
     * Email address
     */
    private String email;
}
