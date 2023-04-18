package com.example.address.service.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfiguration {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
