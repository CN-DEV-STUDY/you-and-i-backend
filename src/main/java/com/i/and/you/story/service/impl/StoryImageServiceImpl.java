package com.i.and.you.story.service.impl;

import com.i.and.you.story.repository.StoryImageRepository;
import com.i.and.you.story.service.StoryImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoryImageServiceImpl implements StoryImageService {

    private final StoryImageRepository storyImageRepository;
}
