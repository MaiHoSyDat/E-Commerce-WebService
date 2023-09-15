package com.example.case6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditPassDTO {
    private long accountId;
    private String pass;
    private String newPass;
    private String retypePass;
}
