package com.javaprojects.petsapi;

import com.javaprojects.petsapi.config.BeanConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PetsApplication {
    public static void main(String[] args){
        SpringApplication.run(PetsApplication.class, args);
    }

}
