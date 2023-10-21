package com.i.and.you.domain.story.controller;

import com.i.and.you.domain.story.dto.StoryRequest;
import com.i.and.you.domain.story.dto.StoryResponse;
import com.i.and.you.domain.story.facade.StoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/stories")
@RestController
public class StoryController {

    private final StoryFacade storyFacade;

    @PostMapping
    public StoryResponse.Save save(StoryRequest.Save request) {
        return storyFacade.save(request);
    }
}
