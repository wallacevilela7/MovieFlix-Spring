package tech.wvs.movieflix2.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.wvs.movieflix2.controller.dto.request.UserRequest;
import tech.wvs.movieflix2.mapper.UserMapper;
import tech.wvs.movieflix2.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(UserRequest request) {
        var entity = UserMapper.toEntity(request);
        String password = entity.getPassword();

        entity.setPassword(passwordEncoder.encode(password));

        userRepository.save(entity);
    }
}
