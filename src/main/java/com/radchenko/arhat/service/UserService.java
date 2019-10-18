package com.radchenko.arhat.service;

import com.radchenko.arhat.entity.User;
import com.radchenko.arhat.exceptions.BrokenRequestException;
import com.radchenko.arhat.exceptions.NotFoundEntityException;
import com.radchenko.arhat.repository.UserRepository;
import com.radchenko.arhat.web.contoller.user.model.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public UserDto findByEmailContains(String email) {
        if (email != null && !email.isBlank()) {
            throw new BrokenRequestException("Email is empty");
        }

        return userRepository
                .findByEmailContains(email)
                .map(user -> mapper.map(user, UserDto.class))
                .orElseThrow(() -> new NotFoundEntityException("User Not Found"));
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(u -> mapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void activateUser(String code) {
        User user  = userRepository
                .findByActivationCode(code)
                .orElseThrow(() -> new NotFoundEntityException("Activation code not exist"));

        user.setActive(true);
    }
}
