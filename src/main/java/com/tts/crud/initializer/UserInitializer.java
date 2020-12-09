package com.tts.crud.initializer;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tts.crud.entity.UserEntity;
import com.tts.crud.repository.UserRepository;

@Component
class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) {
        ArrayList<UserEntity> users = new ArrayList<UserEntity>(
                Arrays.asList(
                        new UserEntity("Jack", "Smith"),
                        new UserEntity("Adam", "Johnson"),
                        new UserEntity("Dana", "Bay"),
                        new UserEntity("Enier", "Ramos"),
                        new UserEntity("Yulia", "Garcia")
                )
        );
        userRepository.saveAll(users);
    }
}
