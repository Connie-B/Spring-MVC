package com.example.accessingdatamysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication 
@ComponentScan(basePackages = {"com.example.accessingdatamysql", "com.example.quoters"} )
@EntityScan(basePackages={"com.example.accessingdatamysql", "com.example.quoters"}) 
@EnableJpaRepositories(basePackages={"com.example.accessingdatamysql", "com.example.quoters"}) 
public class AccessingDataMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}

}
