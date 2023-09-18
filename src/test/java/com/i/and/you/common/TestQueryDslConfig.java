package com.i.and.you.common;

import com.i.and.you.user.repository.custom.impl.UserCustomRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * https://rachel0115.tistory.com/entry/QueryDsl-DataJpaTest-%EC%97%90%EC%84%9C-Repository-%ED%85%8C%EC%8A%A4%ED%8A%B8%ED%95%98%EA%B8%B0
 *
 * querydsl repository test를 위한 설정
 */
@TestConfiguration
public class TestQueryDslConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public UserCustomRepositoryImpl userRepository() {
        return new UserCustomRepositoryImpl(jpaQueryFactory());
    }

}
