package uol.compass.desafiopb.mscalculate;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MscalculateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscalculateApplication.class, args);
	}

}
