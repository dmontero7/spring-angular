package com.damb.springboot.backend.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringbootBackendApirestApplication implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
	SpringApplication.run(SpringbootBackendApirestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	String clave = "12345";
	for (int i = 0; i < 4; i++) {
	    String passwordBcrypt = passwordEncoder.encode(clave);
	    System.out.println(passwordBcrypt);
	}
    }

}
