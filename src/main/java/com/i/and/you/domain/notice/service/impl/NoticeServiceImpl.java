package com.i.and.you.domain.notice.service.impl;

import com.i.and.you.domain.notice.entity.Notice;
import com.i.and.you.domain.notice.repository.NoticeRepository;
import com.i.and.you.domain.notice.service.NoticeService;
import com.i.and.you.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public int getUnreadNoticeCount(User user) {
        return noticeRepository.countByUserAndIsRead(user, false);
    }

    @Override
    public Long saveRelationsNotice(Notice relationsNotice) {
        noticeRepository.save(relationsNotice);
        return relationsNotice.getId();
    }
}
