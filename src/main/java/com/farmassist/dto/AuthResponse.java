package com.farmassist.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String role;
    private String token; // Token or Session Key for frontend API calls
    private String message;
}
