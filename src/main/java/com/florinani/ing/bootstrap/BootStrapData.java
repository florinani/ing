package com.florinani.ing.bootstrap;

import com.florinani.ing.domain.User;
import com.florinani.ing.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final UserRepository userRepository;

    public BootStrapData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("vasilepop", "password123", "Vasile", "Pop", "Str. Memorandumlui 31 Cluj-Napoca",
                "vasilepop@gmail.com", "07394837485");
        User user2 = new User("ioanadan", "password124", "Ioana", "Dan", "Str. Ravasului 57 Cluj-Napoca",
                "ioanadan@gmail.com", "0738373647");

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
