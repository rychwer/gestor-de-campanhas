package br.com.richer.torcedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCircuitBreaker
@EnableFeignClients
@ComponentScan(basePackages = { "br.com.richer.torcedor" })
public class TorcedorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TorcedorServiceApplication.class, args);
    }
}
