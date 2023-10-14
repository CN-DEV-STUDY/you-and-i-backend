package com.i.and.you.story.entity;

import com.i.and.you.story.dto.StoryRequest;
import com.i.and.you.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
@Entity
public class Story {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "story")
    private StoryImage storyImage;

    //==생성 메서드==//
    public static Story createStory(StoryRequest.Save request, User user) {
        return Story.builder()
                .user(user)
                .content(request.getContent())
                .build();
    }
}
