package com.i.and.you.domain.notice.service.impl;

import com.i.and.you.domain.notice.entity.Notice;
import com.i.and.you.domain.notice.exception.NoticeNotFoundException;
import com.i.and.you.domain.notice.repository.NoticeRepository;
import com.i.and.you.domain.notice.service.NoticeService;
import com.i.and.you.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public Notice findById(Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(NoticeNotFoundException::new);
    }

    @Override
    public int getUnreadNoticeCount(User user) {
        return noticeRepository.countByUserAndIsReadAndDeletedFalse(user, false);
    }

    @Transactional
    @Override
    public Long saveRelationsNotice(Notice relationsNotice) {
        noticeRepository.save(relationsNotice);
        return relationsNotice.getId();
    }

    @Override
    public List<Notice> getNotices(User user) {
        return noticeRepository.findAllByUserAndDeletedFalse(user);
    }

    @Transactional
    @Override
    public void delete(Notice notice) {
        notice.delete();
    }
}
