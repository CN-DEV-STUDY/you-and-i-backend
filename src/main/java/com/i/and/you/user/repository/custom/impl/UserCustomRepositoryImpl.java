package com.i.and.you.user.repository.custom.impl;

import com.i.and.you.user.dto.FindUserRequest;
import com.i.and.you.user.entity.User;
import com.i.and.you.user.repository.custom.UserCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.i.and.you.user.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> getUsers(FindUserRequest findUserRequest) {
        return jpaQueryFactory.selectFrom(user)
                .where(nameEq(findUserRequest.name()),
                        nicknameEq(findUserRequest.nickname()),
                        emailEq(findUserRequest.email()))
                .orderBy(user.id.desc())
                .fetch();
    }

    public BooleanExpression nameEq(String name) {
        return name != null ? user.name.eq(name) : null;
    }

    public BooleanExpression nicknameEq(String nickname) {
        return nickname != null ? user.nickname.eq(nickname) : null;
    }

    public BooleanExpression emailEq(String email) {
        return email != null ? user.email.eq(email) : null;
    }
}
