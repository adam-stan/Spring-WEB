package com.crud.tasks;

import org.springframework.stereotype.Repository;

@Repository
public class DataBase {

    public Application application() {
        return new Application(new App("Tasks Application", "Application has been created on Kodilla course!",
                        "1.0.0", new Owner("John", "Doe"),

                                new Administrator("john.doe@test.pl", new Address("Super Cool Street", "42"))),

                        new Company("TaskCrudAppCreator", "Our goal is to make the world a better place",
                        "tasks@crud.com", "345345223"));
        }
}

