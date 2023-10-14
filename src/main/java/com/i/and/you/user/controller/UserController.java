package com.i.and.you.user.controller;

import com.i.and.you.common.api.ApiResult;
import com.i.and.you.user.dto.SaveUserResponse;
import com.i.and.you.user.dto.SaveUserRequest;
import com.i.and.you.user.service.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    public ResponseEntity<ApiResult<SaveUserResponse>> save(@RequestBody SaveUserRequest request) {
        return ApiResult.createSuccess(userFacade.save(request));
    }
}
