package com.example.catsgram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CatsgramApplication {

    public static void main(String[] args) throws JsonProcessingException {

        SpringApplication.run(CatsgramApplication.class, args);

    }

}
