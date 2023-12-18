package com.example.taskprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TaskProcessingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskProcessingApplication.class, args);
    }

}
