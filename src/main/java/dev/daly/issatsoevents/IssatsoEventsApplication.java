package dev.daly.issatsoevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class IssatsoEventsApplication {


    public static void main(String[] args) {
        SpringApplication.run(IssatsoEventsApplication.class, args);
    }

}
