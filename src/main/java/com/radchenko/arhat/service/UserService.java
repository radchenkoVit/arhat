package com.radchenko.arhat.service;

import com.radchenko.arhat.exceptions.NotFoundEntityException;
import com.radchenko.arhat.repository.UserRepository;
import com.radchenko.arhat.web.contoller.user.model.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserDto findByEmailContains(String email) {
        return userRepository
                .findByEmailContains(email)
                .map(user -> mapper.map(user, UserDto.class))
                .orElseThrow(() -> new NotFoundEntityException("User Not Found"));
    }

    public List<UserDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(u -> mapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }
}
