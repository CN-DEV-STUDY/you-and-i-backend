package com.i.and.you.story.service.impl;

import com.i.and.you.story.dto.StoryRequest;
import com.i.and.you.story.dto.StoryResponse;
import com.i.and.you.story.entity.Story;
import com.i.and.you.story.repository.StoryRepository;
import com.i.and.you.story.service.StoryService;
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
