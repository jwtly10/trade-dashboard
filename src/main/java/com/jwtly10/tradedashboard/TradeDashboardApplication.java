package com.jwtly10.tradedashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "src/main/java/com/jwtly10/tradedashboard/*" })
public class TradeDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeDashboardApplication.class, args);
	}

}
