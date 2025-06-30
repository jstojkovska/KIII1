package com.example.vezba1.security;

public class JwtConstants {
    public static final String SECRET_KEY="f5ac7f3c541bd7158f1e6cb15a21144682c534e485bd886fc4c213801fa66a478365e5002cd03d476e53f6854da18b2189f06eda1112646c86a7541cca41b08d";
    public static final Long EXPIRATION_TIME=864000000L;
    public static final String HEADER="Authorization";
    public static final String TOKEN_PREFIX="Bearer ";
}
