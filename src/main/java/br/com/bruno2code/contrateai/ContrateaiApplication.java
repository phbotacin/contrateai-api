package br.com.bruno2code.contrateai;

import java.util.Date;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class ContrateaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContrateaiApplication.class, args);
    }

    @RequestMapping("/")
    public String handleError() {
        return "Up! " + new Date().toString();
    }
}
