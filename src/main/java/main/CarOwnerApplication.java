package main;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CarOwnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarOwnerApplication.class, args);
                System.out.println("Hello");
	}
}
