package uol.compass.desafiopb.mscustomer;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MscustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscustomerApplication.class, args);}}
