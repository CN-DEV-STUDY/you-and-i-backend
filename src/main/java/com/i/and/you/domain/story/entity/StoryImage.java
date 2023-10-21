package com.i.and.you.domain.story.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Builder(access = PRIVATE)
@Entity
public class StoryImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_image_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @Comment("이미지 저장 경로")
    @Column(nullable = false)
    private String filePath;

    @Comment("원본 파일 명")
    @Column(nullable = false, length = 100)
    private String originalName;

    @Comment("저장 파일 명")
    @Column(nullable = false, length = 100)
    private String storedName;

    @Comment("파일 확장자")
    @Column(nullable = false, length = 5)
    private String extension;
}
