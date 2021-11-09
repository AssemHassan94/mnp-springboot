package com.gtss.mnp;

import com.gtss.mnp.dto.OperatorDto;
import com.gtss.mnp.service.MobileNumberService;
import com.gtss.mnp.service.OperatorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MnpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MnpApplication.class, args);
    }
}
