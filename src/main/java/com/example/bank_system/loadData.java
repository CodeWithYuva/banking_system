package com.example.bank_system;

import com.example.bank_system.repo.userRepo;
import com.example.bank_system.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadData {

    private static final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner initDatabase(userRepo repository) {

        return args -> {
            log.info("Preloading " + repository.save(new User("user1", 7722)));
            log.info("Preloading " + repository.save(new User("user2", 2277)));
        };
    }
}