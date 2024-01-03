package com.example.accessingdatamysql;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppUserDatabaseLoader {

    @Bean
    CommandLineRunner initAppUserDatabase(AppUserRepository repository) {

        return args -> {
            repository.save(new AppUser("Bella", "Blue", "110 Sparrow St.", "Roberts", "6085551023", "bella@email.com"));
            repository.save(new AppUser("Pauly", "Purple", "638 Cardinal Ave.", "Hammond", "6085551749", "pauly@email.com"));
            repository.save(new AppUser("Ricky", "Red", "2693 Eagle St.", "Baldwin", "6085558763", "ricky@email.com"));
            repository.save(new AppUser("Barney", "Brown", "563 Robin St.", "Milwaukee", "6085553198", "barney@email.com"));
            repository.save(new AppUser("Wanda", "White", "2387 Penguin Pl.", "New Richmond", "6085552765", "wanda@email.com"));
            repository.save(new AppUser("Brenda", "Black", "105 Hummingbird Ln.", "River Falls", "6085552654", "brenda@email.com"));
            repository.save(new AppUser("Ophelia", "Orange", "1450 Seagull St.", "Monona", "6085555387", "ophelia@email.com"));
            repository.save(new AppUser("Yasmin", "Yellow", "345 Canary Ave.", "Cumberland", "6085557683", "yasmin@email.com"));
            repository.save(new AppUser("Glenda", "Green", "2749 Woodpecker Trail", "Madison", "6085559435", "glenda@email.com"));
            repository.save(new AppUser("Franny", "Fushia", "2335 Finch La.", "Milwaukee", "6085555487", "franny@email.com"));
            repository.save(new AppUser("Louisa", "Lavender", "1224 Pelican St.", "Balsam Lake", "6085559436", "louisa@email.com"));
            repository.save(new AppUser("Paisley", "Pink", "1440 Pigeon Ave.", "Spooner", "6085559437", "paisley@email.com"));
            
            repository.save(new AppUser("Mandy", "Maroon", "637 Hazel Ave.", "Madison", "6085551023", "Mandy@email.com"));
            repository.save(new AppUser("Cindy", "Cyan", "638 Hazel Ave.", "Madison", "6085551749", "Cindy@email.com"));
            repository.save(new AppUser("Gilda", "Gray", "639 Hazel Ave.", "Madison", "6085558763", "Gilda@email.com"));
            repository.save(new AppUser("Bessie", "Bronze", "563 Juniper St.", "Madison", "6085553198", "Bessie@email.com"));
            repository.save(new AppUser("Tammy", "Turquoise", "567 Juniper St.", "Madison", "6085552765", "Tammy@email.com"));
            repository.save(new AppUser("Andy", "Aqua", "105 Olive St.", "Madison", "6085552654", "Andy@email.com"));
            repository.save(new AppUser("Nellie", "Navy", "1440 Willow Ave.", "Milwaukee", "6085555387", "Nellie@email.com"));
            repository.save(new AppUser("Tommy", "Teal", "1224 Elm St.", "Milwaukee", "6085557683", "Tommy@email.com"));
            repository.save(new AppUser("Gabriel", "Gold", "345 Maple St.", "Milwaukee", "6085559435", "Gabriel@email.com"));
            repository.save(new AppUser("Simon", "Silver", "1450 Oak Blvd.", "Milwaukee", "6085555487", "Simon@email.com"));
            repository.save(new AppUser("Peter", "Pink", "567 Birch St.", "Milwaukee", "6085559435", "Peter@email.com"));
            repository.save(new AppUser("Morgan", "Magenta", "637 Aspen St.", "Milwaukee", "6085555487", "Morgan@email.com"));            
        };
    }
}
