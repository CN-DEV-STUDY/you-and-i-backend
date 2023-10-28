package com.i.and.you.domain.user.repository.custom.impl;

import com.i.and.you.domain.user.dto.FindUserRequest;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.enums.UserSearchType;
import com.i.and.you.domain.user.repository.custom.UserCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.i.and.you.domain.user.entity.QUser.user;


@RequiredArgsConstructor
@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<User> getUsers(FindUserRequest findUserRequest) {
        return null;
    }

    @Override
    public Page<User> findUserUsingPaging(FindUserRequest request, Pageable pageable) {
        List<User> users = jpaQueryFactory.selectFrom(user)
                .where(searchTypeWordEq(request.searchType(), request.searchWord()))
                .orderBy(user.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalRecord = jpaQueryFactory.select(user.count())
                .from(user)
                .where(searchTypeWordEq(request.searchType(), request.searchWord()))
                .fetchOne();

        return new PageImpl<>(users, pageable, totalRecord);
    }

    public BooleanExpression searchTypeWordEq(UserSearchType searchType, String searchWord) {
        if (searchType == null || searchWord == null) {
            return null;
        }

        return switch (searchType) {
            case NAME -> user.name.contains(searchWord);
            case EMAIL -> user.email.contains(searchWord);
            case NICKNAME -> user.nickname.contains(searchWord);
        };
    }
}
