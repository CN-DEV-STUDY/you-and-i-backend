package com.i.and.you.domain.story.service.impl;

import com.i.and.you.domain.story.service.StoryImageService;
import com.i.and.you.domain.story.repository.StoryImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoryImageServiceImpl implements StoryImageService {

    private final StoryImageRepository storyImageRepository;
}
