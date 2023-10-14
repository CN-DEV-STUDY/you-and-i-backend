package com.i.and.you.user.service.impl;

import com.i.and.you.user.dto.SaveUserRequest;
import com.i.and.you.user.entity.User;
import com.i.and.you.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRegisterServiceImplTest {

    @InjectMocks UserServiceImpl userService;
    @Mock UserRepository userRepository;
    @Mock PasswordEncoder passwordEncoder;

    private SaveUserRequest createUserDto() {
        return new SaveUserRequest(
                "yaa4500@naver.com",
                "노현하",
                "yaa4500",
                "notEncodedPassword",
                null);
    }

    @Transactional
    @Test
    public void testSaveUser() throws Exception {
        // given
        SaveUserRequest userDto = createUserDto();
        User user = userDto.toEntity();

        // when
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(userDto);

        // then
        assertThat(user.getEmail()).isEqualTo(savedUser.getEmail());
        assertThat(user.getName()).isEqualTo(savedUser.getName());
        assertThat(user.getNickname()).isEqualTo(savedUser.getNickname());
        verify(passwordEncoder, times(1)).encode(eq("notEncodedPassword")); // 비밀번호 인코딩 검증
    }


}