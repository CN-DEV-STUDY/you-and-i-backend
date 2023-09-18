package com.i.and.you.user.service;

import com.i.and.you.user.dto.FindUserRequest;
import com.i.and.you.user.dto.FindUserResponse;

import java.util.List;

public interface FindUserService {

    public List<FindUserResponse> findUsers(FindUserRequest findUserRequest);
}
