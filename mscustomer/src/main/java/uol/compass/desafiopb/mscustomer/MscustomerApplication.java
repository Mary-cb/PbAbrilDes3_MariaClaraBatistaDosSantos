package uol.compass.desafiopb.mscustomer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableRabbit
@EnableFeignClients
public class MscustomerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MscustomerApplication.class, args);}}
