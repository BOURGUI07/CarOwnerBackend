package main;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarOwnerApplication {
    private static final Logger logger = LogManager.getLogger(CarOwnerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CarOwnerApplication.class, args);
                logger.info("Application Started Sucessfully");
                System.out.println("Hello");
	}
       
        
        

}
