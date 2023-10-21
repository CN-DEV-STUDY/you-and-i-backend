package com.i.and.you.period.service;

import com.i.and.you.domain.period.repository.PeriodRepository;
import com.i.and.you.domain.period.service.PeriodService;
import com.i.and.you.domain.period.service.impl.PeriodServiceImpl;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.repository.UserRepository;
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
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PeriodServiceTest {

    @Mock PeriodRepository periodRepository;
    @Mock UserRepository userRepository;

    PeriodService periodService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        periodService = new PeriodServiceImpl(periodRepository, userRepository);
    }

    @DisplayName("정확한 기간(일수)을 저장 한다.")
    @Test
    void savePeriod() {
        User user = User.builder()
                .email("email@email.com")
                .name("홍길동")
                .nickname("홍길동")
                .password("1111")
                .build();
        given(userRepository.findById(1L)).willReturn(Optional.of(user));


        // when
        LocalDate testDate = LocalDate.of(2023, 10, 9);
        long period = periodService.savePeriod(testDate, "1L");

        // then
        long diff = ChronoUnit.DAYS.between(testDate, LocalDate.now());
        assertThat(period).isEqualTo(diff);
    }

    @DisplayName("정확한 기간(일수)을 반환 한다.")
    @Test
    void getPeriod() {
        // given

        // when

        // then

    }
}