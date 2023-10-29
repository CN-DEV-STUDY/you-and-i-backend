package com.i.and.you.domain.user.controller;

import com.i.and.you.domain.user.dto.*;
import com.i.and.you.global.api.ApiResult;
import com.i.and.you.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    public ResponseEntity<ApiResult<SaveUserResponse>> save(@RequestBody SaveUserRequest request) {
        return ApiResult.createSuccess(userFacade.save(request));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResult<List<FindUserResponse>>> findUserUsingPaging(FindUserRequest request, Pageable pageable) throws InterruptedException {
        return userFacade.findUserUsingPaging(request, pageable);
    }

    @PostMapping("/relations")
    public ResponseEntity<ApiResult<Void>> setRelations(@RequestBody SetRelationsRequest request) {
        userFacade.setRelations(request);
        return ApiResult.createSuccessWithNoContent();
    }
}
