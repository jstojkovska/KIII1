package com.example.vezba1.dto;

import com.example.vezba1.model.domain.JwtTokenLog;

import java.util.Date;

public record JwtTokenLogDto(String username,
                             String token,
                             Date issuedAt,
                             Date expiresAt) {
    public static JwtTokenLogDto from(JwtTokenLog jwtTokenLog) {
        return new JwtTokenLogDto(jwtTokenLog.getUsername(), jwtTokenLog.getToken(), jwtTokenLog.getIssuedAt(), jwtTokenLog.getExpiresAt());
    }
}
