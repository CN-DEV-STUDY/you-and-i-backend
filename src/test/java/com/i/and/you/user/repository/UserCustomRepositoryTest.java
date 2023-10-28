package com.i.and.you.user.repository;

import com.i.and.you.common.TestQueryDslConfig;
import com.i.and.you.domain.user.dto.FindUserRequest;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.enums.UserSearchType;
import com.i.and.you.domain.user.repository.custom.impl.UserCustomRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@AutoConfigureTestDatabase(replace = NONE)
@Import(TestQueryDslConfig.class)
@DataJpaTest
public class UserCustomRepositoryTest {

    @Autowired
    private UserCustomRepositoryImpl userCustomRepositoryImpl;

    @Test
    public void testFindUser() throws Exception {
        // given
        FindUserRequest findUserRequest = createFindUserRequest();

        // when
        List<User> userBy = userCustomRepositoryImpl.getUsers(findUserRequest);

        // then
        for (User user : userBy) {
            System.out.println("user.getName() = " + user.getName());
            System.out.println("user.getNickname() = " + user.getNickname());
            System.out.println("user.getEmail() = " + user.getEmail());
        }
    }


    private FindUserRequest createFindUserRequest() {
        return new FindUserRequest(UserSearchType.NAME, "yaa4500@naver.com");
    }
}
