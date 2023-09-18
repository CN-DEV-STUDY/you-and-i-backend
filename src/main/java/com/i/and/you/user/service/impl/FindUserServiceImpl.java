package com.i.and.you.user.service.impl;

import com.i.and.you.user.dto.FindUserRequest;
import com.i.and.you.user.dto.FindUserResponse;
import com.i.and.you.user.service.FindUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FindUserServiceImpl implements FindUserService {


    @Override
    public List<FindUserResponse> findUsers(FindUserRequest findUserRequest) {
        return null;
    }
}
