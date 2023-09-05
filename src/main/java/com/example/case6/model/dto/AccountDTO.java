package com.example.case6.model.dto;

import com.example.case6.model.Role;
import com.example.case6.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private long id;//
    private String email;
    private String name;
    private String username;
    private Status status;

    private Role role;
    private String token;

    public AccountDTO(long id, String email, String username, Status status, Role role, String token) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.status = status;
        this.role = role;
        this.token = token;
    }

    public AccountDTO(long id, String email, String name, String username, Status status, Role role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.username = username;
        this.status = status;
        this.role = role;

    }
}
