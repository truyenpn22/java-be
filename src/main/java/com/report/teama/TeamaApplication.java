package com.report.teama;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.report.teama.middleware.MyMiddleware;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
@Component

public class TeamaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamaApplication.class, args);
	}
    @Bean
    public FilterRegistrationBean<MyMiddleware> myFilter() {
        FilterRegistrationBean<MyMiddleware> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyMiddleware());
        registrationBean.setUrlPatterns(Arrays.asList("/api/users"));
        System.out.print("Middleware run ning...");
        return registrationBean;
    }


}
