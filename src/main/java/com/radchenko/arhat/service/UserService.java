package com.radchenko.arhat.service;

import com.radchenko.arhat.entity.User;
import com.radchenko.arhat.exceptions.BrokenRequestException;
import com.radchenko.arhat.exceptions.NotFoundEntityException;
import com.radchenko.arhat.repository.RoleRepository;
import com.radchenko.arhat.repository.UserRepository;
import com.radchenko.arhat.web.contoller.user.model.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper mapper;
    private BCryptPasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, ModelMapper mapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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
    public UserDto createUser(UserDto user) {
        if (user.getId() != null) {
            throw new BrokenRequestException("Id should be empty");
        }
        User userToSave = mapper.map(user, User.class);
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        userToSave.setRole(roleRepository.findById(1L).get());//TODO: think

        return mapper.map(userRepository.save(userToSave), UserDto.class);
    }
}
