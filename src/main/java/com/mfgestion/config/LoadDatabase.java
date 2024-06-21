package com.mfgestion.config;

import com.mfgestion.model.User;
import com.mfgestion.model.User.Role;
import com.mfgestion.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByEmail("mfezaud@gmail.com")) {
                User admin = new User();
                admin.setNom("Admin");
                admin.setPrenom("Maycar");
                admin.setEmail("mfezaud@gmail.com");
                admin.setTel("0621488456");
                admin.setMdp(passwordEncoder.encode("admin1234"));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
            }

            if (!userRepository.existsByEmail("chauffeur@example.com")) {
                User chauffeur = new User();
                chauffeur.setNom("Chauffeur");
                chauffeur.setPrenom("User");
                chauffeur.setEmail("chauffeur@example.com");
                chauffeur.setTel("1234567890");
                chauffeur.setMdp(passwordEncoder.encode("chauffeur1234"));
                chauffeur.setRole(Role.CHAUFFEUR);
                userRepository.save(chauffeur);
            }

            if (!userRepository.existsByEmail("passager@example.com")) {
                User passager = new User();
                passager.setNom("Passager");
                passager.setPrenom("User");
                passager.setEmail("passager@example.com");
                passager.setTel("1234567890");
                passager.setMdp(passwordEncoder.encode("passager1234"));
                passager.setRole(Role.PASSAGER);
                userRepository.save(passager);
            }
        };
    }
}
