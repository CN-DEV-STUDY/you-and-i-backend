package com.i.and.you.user.repository.custom;

import com.i.and.you.user.dto.FindUserRequest;
import com.i.and.you.user.entity.User;

import java.util.List;

public interface UserCustomRepository {

    List<User> getUsers(FindUserRequest findUserRequest);
}
