package com.i.and.you.user.repository;

import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testSave() throws Exception {
        // given
        User user = createUser();
        // when
        User savedUser = userRepository.save(user);
        // then
        assertEquals(user, savedUser);
    }

    @Test
    public void testFindBy() throws Exception {
        // given
        User user = createUser();
        User savedUser = userRepository.save(user);

        // when
        User findUser = userRepository.findById(savedUser.getId())
                .orElseThrow(() -> new Exception("유저를 찾을 수 없습니다."));

        // then
        assertEquals(savedUser, findUser);
    }

    private User createUser() {
        return User.builder()
                .email("yaa4500@naver.com")
                .name("노현하")
                .nickname("yaa4500")
                .password("1234")
                .build();
    }

}