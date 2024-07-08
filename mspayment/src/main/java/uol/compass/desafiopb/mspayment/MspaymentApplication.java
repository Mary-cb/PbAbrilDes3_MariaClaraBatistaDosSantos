package uol.compass.desafiopb.mspayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MspaymentApplication {
	public static void main(String[] args) {
		SpringApplication.run(MspaymentApplication.class, args);}
}
