package com.i.and.you.domain.story.repository;

import com.i.and.you.domain.story.entity.StoryImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryImageRepository extends JpaRepository<StoryImage, Long> {
}
