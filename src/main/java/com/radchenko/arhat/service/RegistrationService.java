package com.radchenko.arhat.service;

import com.radchenko.arhat.entity.User;
import com.radchenko.arhat.repository.RoleRepository;
import com.radchenko.arhat.repository.UserRepository;
import com.radchenko.arhat.web.contoller.user.model.RegistrationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private ModelMapper mapper;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, RoleRepository roleRepository, ModelMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegistrationRequest request) {
        User user = mapper.map(request, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findById(1L).get());//TODO: think
        userRepository.save(user);
    }
}
