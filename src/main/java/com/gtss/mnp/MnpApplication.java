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

//    @Bean
//    CommandLineRunner run(OperatorService operatorService) {
//        return args -> {
////				userService.saveRole(new RoleDto(null, "ROLE_USER"));
////				userService.saveRole(new RoleDto(null, "ROLE_MANAGER"));
////				userService.saveRole(new RoleDto(null, "ROLE_ADMIN"));
////				userService.saveRole(new RoleDto(null, "ROLE_SUPER_ADMIN"));
////
////				userService.saveUser(new UserDto(null, "Assem", "123456", "bio1"));
////				userService.saveUser(new UserDto(null, "Ahmed", "123456", "bio2"));
////				userService.saveUser(new UserDto(null, "MO", "123456", "bio3"));
////				userService.saveUser(new UserDto(null, "MOSalah", "123456", "bio4"));
////
//            operatorService.createOperator(new OperatorDto(null, "vodafone", "010"));
//            operatorService.createOperator(new OperatorDto(null, "etisalat", "011"));
//            operatorService.createOperator(new OperatorDto(null, "orange", "012"));
//        };
//    }
}
