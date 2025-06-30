package com.example.vezba1.dto;

import com.example.vezba1.model.domain.User;
import com.example.vezba1.model.enumerations.Role;


public record CreateUserDto(String username, String password,
                            String repeatPassword,
                            String name,
                            String surname,
                            Role role
) {
    public User toUser() {
        return new User(username, password, name, surname, role);
    }

}
