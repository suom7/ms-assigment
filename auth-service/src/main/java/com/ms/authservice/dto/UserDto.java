package com.ms.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    /**
     * id generate
     */
    private Long id;
    /**
     * Student identity
     */
    private String studentId;
    /**
     * access token
     */
    private String token;
}
