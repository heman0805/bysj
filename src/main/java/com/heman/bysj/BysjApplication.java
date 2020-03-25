package com.heman.bysj;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(

        exclude = SecurityAutoConfiguration.class
)
public class BysjApplication {

    public static void main(String[] args) {
        SpringApplication.run(BysjApplication.class, args);
    }

}
