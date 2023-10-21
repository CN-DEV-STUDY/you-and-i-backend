package com.i.and.you.domain.story.repository;

import com.i.and.you.domain.story.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
