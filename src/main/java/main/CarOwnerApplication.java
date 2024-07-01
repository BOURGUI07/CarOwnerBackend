package main;


import java.util.Arrays;
import main.entity.Car;
import main.entity.Owner;
import main.repo.CarRepo;
import main.repo.OwnerRepo;
import main.security.AppUser;
import main.security.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class CarOwnerApplication {
    @Autowired
    public CarOwnerApplication(AppUserRepository userRepo, OwnerRepo ownerRepo, CarRepo carRepo) {
        this.userRepo = userRepo;
        this.ownerRepo = ownerRepo;
        this.carRepo = carRepo;
    }
    private final AppUserRepository userRepo;
    private final OwnerRepo ownerRepo;
    private final CarRepo carRepo;

	public static void main(String[] args) {
		SpringApplication.run(CarOwnerApplication.class, args);
	}
        @Bean
        public CommandLineRunner commandLineRunner() throws Exception{
            return runner ->{
                var owner1 = new Owner("Anas", "Zirari");
                var owner2 = new Owner("Anas", "Hilane");
                ownerRepo.saveAll(Arrays.asList(owner1, owner2));
                carRepo.save(new Car("BMW", "Red", "F45-55G", 2024, 45000, owner1));
                carRepo.save(new Car("Mazda", "Grey", "X45-55G", 1999, 35222, owner1));
                carRepo.save(new Car("WW", "Blue", "F45-55M", 2007, 42000, owner2));
                userRepo.save(new AppUser("youness", "$2a$10$cwm3l5P0oMqE1OHPMXFUjOWro5jr9fnQa/.31guKW0I5zH8G5TlnK", "ADMIN"));
                userRepo.save(new AppUser("yassine", "$2a$10$utrd.EGuHYGXyCfI/zu2Teodnhh3Y7FkKqrMK7A4VZmNBtQuNcCSK", "GUEST"));
            };
        }
        
        

}
