package com.aashita.remote.invoker.client;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import com.aashita.remote.invoker.BookingException;
import com.aashita.remote.invoker.CabBookingService;

@Configuration
public class Client {
 
    @Bean
    public HttpInvokerProxyFactoryBean invoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8080/booking");
        invoker.setServiceInterface(CabBookingService.class);
        return invoker;
    }
 
    public static void main(String[] args) throws BookingException {
        CabBookingService service = SpringApplication
          .run(Client.class, args)
          .getBean(CabBookingService.class);
        System.out.println(service.bookRide("13 Seagate Blvd, Key Largo, FL 33037"));
    }
}