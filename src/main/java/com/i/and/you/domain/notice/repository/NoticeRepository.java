package com.i.and.you.domain.notice.repository;

import com.i.and.you.domain.notice.entity.Notice;
import com.i.and.you.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllByUserAndDeletedFalse(User user);

    int countByUserAndIsReadAndDeletedFalse(User user, boolean read);
}
