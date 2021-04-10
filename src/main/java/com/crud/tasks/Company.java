package com.crud.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private String name;
    private String goal;
    private String email;
    private String phone;

}
