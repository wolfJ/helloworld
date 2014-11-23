package org.wolf.carmanager;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    @Bean
    javax.servlet.MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("256KB");
        factory.setMaxRequestSize("256KB");
        return factory.createMultipartConfig();
    }

}
