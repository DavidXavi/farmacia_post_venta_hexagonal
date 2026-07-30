package com.posfarmacia.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.posfarmacia")
@org.springframework.data.jpa.repository.config.EnableJpaRepositories(basePackages = "com.posfarmacia.adapter.out.persistence")
@org.springframework.boot.persistence.autoconfigure.EntityScan(basePackages = "com.posfarmacia.adapter.out.persistence")
public class PosFarmaciaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PosFarmaciaApplication.class, args);
    }
}
