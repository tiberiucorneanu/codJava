package com.example.demo.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ContextConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)	
          .apiInfo(apiInfo())
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo(
          "Documentație REST API pentru lucrarea de licență", 
          "Această pagină este menită să ofere suportul necesar pentru utilizator, cu scopul de a înțelege funcționalitatea din spate.", 
          "UPB, ETTI, 2015-2019", 
          "Terms of service", 
          new Contact("Corneanu Tiberiu", "http://localhost:4200/autentificare", "tiberiu.corneanu@stud.etti.upb.ro"), 
          "License of API", "API license URL", Collections.emptyList());
    }
}