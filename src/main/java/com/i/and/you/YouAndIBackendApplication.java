package com.i.and.you;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class YouAndIBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouAndIBackendApplication.class, args);
    }

}
