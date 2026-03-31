package com.tradewisenexus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TradeWiseNexusApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeWiseNexusApplication.class, args);
    }
}
