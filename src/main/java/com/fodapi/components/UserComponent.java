package com.fodapi.components;

import com.fodapi.models.user.UserEntity;
import com.fodapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserComponent {

    private final UserRepository userRepository;

    public UserEntity getUser(Long userId){
        Optional<UserEntity> byId = userRepository.findById(userId);
        return byId.orElse(null);
    }

    public UserEntity getUserByMail(String mail){
        return userRepository.findByEmail(mail);
    }

    public void saveUser(UserEntity userEntity){
        userRepository.saveAndFlush(userEntity);
    }
}
