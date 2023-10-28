package com.i.and.you.domain.user.facade;

import com.i.and.you.domain.user.dto.FindUserRequest;
import com.i.and.you.domain.user.dto.FindUserResponse;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.service.UserService;
import com.i.and.you.global.api.ApiResult;
import com.i.and.you.global.api.Pagination;
import com.i.and.you.global.jwt.TokenProvider;
import com.i.and.you.domain.user.dto.SaveUserRequest;
import com.i.and.you.domain.user.dto.SaveUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

import static com.i.and.you.domain.user.dto.FindUserResponse.*;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    public SaveUserResponse save(SaveUserRequest request) {
        Long userId = userService.saveUser(request);

        return new SaveUserResponse(
                tokenProvider.generateToken(userService.findById(userId), Duration.ofMinutes(30))
        );
    }

    public ResponseEntity findUserUsingPaging(FindUserRequest request, Pageable pageable) {
        Page<User> userPaging = userService.findUserUsingPaging(request, pageable);

        return ApiResult.createSuccess(entityToDto(userPaging.getContent()), Pagination.from(userPaging));

    }
}
