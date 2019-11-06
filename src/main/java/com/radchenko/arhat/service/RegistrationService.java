package com.radchenko.arhat.service;

import com.radchenko.arhat.entity.Role;
import com.radchenko.arhat.entity.User;
import com.radchenko.arhat.repository.UserRepository;
import com.radchenko.arhat.service.mail.MailSenderService;
import com.radchenko.arhat.utils.RegistrationMailHelper;
import com.radchenko.arhat.web.contoller.user.model.RegistrationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RegistrationService {
    private UserRepository userRepository;
    private ModelMapper mapper;
    private BCryptPasswordEncoder passwordEncoder;
    private MailSenderService mailSenderService;

    @Autowired
    public RegistrationService(UserRepository userRepository, ModelMapper mapper, BCryptPasswordEncoder passwordEncoder, MailSenderService mailSenderService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
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
        String messageBody = RegistrationMailHelper.getActivationBody(user.getActivationCode());
        mailSenderService.send(user.getEmail(), "Activation code", messageBody);
    }
}
