package com.i.and.you.domain.notice.service;

import com.i.and.you.domain.notice.entity.Notice;
import com.i.and.you.domain.user.entity.User;

public interface NoticeService {
    int getUnreadNoticeCount(User user);

    Long saveRelationsNotice(Notice relationsNotice);
}
