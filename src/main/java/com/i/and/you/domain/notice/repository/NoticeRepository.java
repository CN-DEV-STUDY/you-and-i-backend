package com.i.and.you.domain.notice.repository;

import com.i.and.you.domain.notice.entity.Notice;
import com.i.and.you.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    int countByUserAndIsRead(User user, boolean isRead);
}
