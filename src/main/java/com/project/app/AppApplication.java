package com.project.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}

/*

Package Structure :

controller: REST Controllers for handling API requests
service: Business Logic Layer
repository: Spring Data repository layer
dto: Data Transfer Object classes
exception: Custom Exception Handling classes
mapper: Conversion layer between Entities and DTOs
entity: JPA Entity classes


Shortcuts :
for beautify the code : Command + Option + L
for Spelling Mistakes : Option + Return

*/