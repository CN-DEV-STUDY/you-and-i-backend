package com.i.and.you.domain.story.service.impl;

import com.i.and.you.domain.story.entity.Story;
import com.i.and.you.domain.story.repository.StoryRepository;
import com.i.and.you.domain.story.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;

    @Override
    public Long save(Story story) {
        storyRepository.save(story);
        return story.getId();
    }
}
