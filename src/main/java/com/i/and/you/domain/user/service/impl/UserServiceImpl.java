package com.i.and.you.domain.user.service.impl;

import com.i.and.you.domain.user.dto.SaveUserRequest;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.repository.UserRepository;
import com.i.and.you.domain.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Long saveUser(SaveUserRequest request) {
        User me = request.toEntity();
        me.changePasswordTo(passwordEncoder.encode(request.password()));

        if (request.hasYou()) {
            User you = userRepository.findById(request.youId())
                    .orElseThrow(() -> new EntityNotFoundException("상대방이 존재하지 않습니다."));
            me.addYou(you);
        }

        User savedUser = userRepository.save(me);

        return savedUser.getId();
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다."));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다."));
    }
}
