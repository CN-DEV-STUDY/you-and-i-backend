package com.i.and.you.domain.user.repository.custom;

import com.i.and.you.domain.user.dto.FindUserRequest;
import com.i.and.you.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserCustomRepository {

    List<User> getUsers(FindUserRequest findUserRequest);

    Page<User> findUserUsingPaging(FindUserRequest request, Pageable pageable);
}
