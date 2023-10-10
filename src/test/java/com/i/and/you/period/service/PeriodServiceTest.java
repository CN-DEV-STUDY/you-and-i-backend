package com.i.and.you.period.service;

import com.i.and.you.period.repository.PeriodRepository;
import com.i.and.you.period.service.impl.PeriodServiceImpl;
import com.i.and.you.user.entity.User;
import com.i.and.you.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PeriodServiceTest {

    @Mock PeriodRepository periodRepository;
    @Mock UserRepository userRepository;

    PeriodService  periodService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        periodService = new PeriodServiceImpl(periodRepository, userRepository);
    }

    @DisplayName("정확한 기간(일수)을 반환 한다.")
    @Test
    void getPeriod() {
        // given
        User user = User.builder()
                .email("email@email.com")
                .name("홍길동")
                .nickname("홍길동")
                .password("1111")
                .build();
        given(userRepository.findById(1L)).willReturn(Optional.of(user));


        // when
        LocalDate testDate = LocalDate.of(2023, 10, 9);
        long period = periodService.getPeriod(testDate, 1L);

        // then
        long diff = ChronoUnit.DAYS.between(testDate, LocalDate.now());
        assertThat(period).isEqualTo(diff);

    }


}