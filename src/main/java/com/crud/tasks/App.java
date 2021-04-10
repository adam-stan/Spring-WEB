package com.crud.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class App {

    private String name;
    private String description;
    private String version;
    private Owner owner;
    private Administrator administrator;

}
