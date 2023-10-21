package com.i.and.you.domain.user.repository.custom;

import com.i.and.you.domain.user.dto.FindUserRequest;
import com.i.and.you.domain.user.entity.User;

import java.util.List;

public interface UserCustomRepository {

    List<User> getUsers(FindUserRequest findUserRequest);
}
