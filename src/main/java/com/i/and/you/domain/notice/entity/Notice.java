package com.i.and.you.domain.notice.entity;

import com.i.and.you.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Notice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String title;
    private String content;

    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean isRead;

    private LocalDateTime sentAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_user_id")
    private User sender;

    @Column(columnDefinition = "TINYINT", length = 1)
    private boolean deleted;

    //== 생성 메서드 ==//
    public static Notice createRelationsNotice(User sender, User receiver) {
        return Notice.builder()
                .user(receiver)
                .title("새로운 유앤아이가 생겼어요!")
                .content(String.format("%s(%s)님이 유앤아이를 신청했습니다.", sender.getName(), sender.getEmail()))
                .sender(sender)
                .sentAt(LocalDateTime.now())
                .isRead(false)
                .deleted(false)
                .build();
    }

    //== 비즈니스 로직 ==//
    public boolean isMyNotice(User user) {
        return this.user == user;
    }

    public void delete() {
        this.deleted = true;
    }
}
