package com.radchenko.arhat.service;

import com.radchenko.arhat.config.MailProperties;
import com.radchenko.arhat.entity.Role;
import com.radchenko.arhat.entity.User;
import com.radchenko.arhat.repository.UserRepository;
import com.radchenko.arhat.service.mail.MailManager;
import com.radchenko.arhat.web.contoller.user.model.RegistrationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class RegistrationService {
    private UserRepository userRepository;
    private ModelMapper mapper;
    private BCryptPasswordEncoder passwordEncoder;
    private MailManager mailManager;
    private MailProperties mailProperties;

    @Autowired
    public RegistrationService(UserRepository userRepository, ModelMapper mapper, BCryptPasswordEncoder passwordEncoder, MailManager mailManager, MailProperties mailProperties) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.mailManager = mailManager;
        this.mailProperties = mailProperties;
    }

    @Transactional
    public void register(RegistrationRequest request) {
        User user = mapper.map(request, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setActive(true);//TODO -> make false by default for production
        userRepository.save(user);

        //TODO: move logic to controller?
        Map<String, Object> model = new HashMap<>();
        model.put("activation_link", mailProperties.generateActivationCode(user.getActivationCode()));
        mailManager.send(user.getEmail(),"Activate your account", "activation-mail.ftl", model);
        //TODO make subject, and temlpate name as an Enum
    }
}
