package com.i.and.you.domain.notice.service;

import com.i.and.you.domain.notice.entity.Notice;
import com.i.and.you.domain.user.entity.User;

import java.util.List;

public interface NoticeService {
    Notice findById(Long noticeId);
    
    int getUnreadNoticeCount(User user);

    Long saveRelationsNotice(Notice relationsNotice);

    List<Notice> getNotices(User user);

    void delete(Notice notice);
}
