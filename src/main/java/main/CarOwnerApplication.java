package main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CarOwnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarOwnerApplication.class, args);
	}
        
        

}
