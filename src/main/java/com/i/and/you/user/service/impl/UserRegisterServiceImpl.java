package com.i.and.you.user.service.impl;

import com.i.and.you.user.dto.AddUserRequest;
import com.i.and.you.user.dto.FindUserResponse;
import com.i.and.you.user.entity.User;
import com.i.and.you.user.repository.UserRepository;
import com.i.and.you.user.service.UserRegisterService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public User saveUser(AddUserRequest userDto) {
        User me = userDto.toEntity();
        me.changePasswordTo(passwordEncoder.encode(userDto.password()));

        if (userDto.hasYou()) {
            User you = userRepository.findById(userDto.youId())
                    .orElseThrow(() -> new EntityNotFoundException("상대방이 존재하지 않습니다."));
            me.addYou(you);
        }

        return userRepository.save(me);
    }


}
