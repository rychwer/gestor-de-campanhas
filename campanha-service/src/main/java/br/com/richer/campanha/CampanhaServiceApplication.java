package br.com.richer.campanha;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "br.com.richer.campanha" })
@EnableRabbit
public class CampanhaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampanhaServiceApplication.class, args);
    }
}
