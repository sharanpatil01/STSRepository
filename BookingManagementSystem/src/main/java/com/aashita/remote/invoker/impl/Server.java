package com.aashita.remote.invoker.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import com.aashita.remote.invoker.CabBookingService;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Server {
 
    @Bean(name = "/booking") HttpInvokerServiceExporter accountService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService( new CabBookingServiceImpl() );
        exporter.setServiceInterface( CabBookingService.class );
        return exporter;
    }
 
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}