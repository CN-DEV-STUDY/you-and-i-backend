package com.i.and.you.story.service;

import com.i.and.you.story.dto.StoryRequest;
import com.i.and.you.story.dto.StoryResponse;
import com.i.and.you.story.entity.Story;
import com.i.and.you.user.entity.User;
import com.i.and.you.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoryFacade {

    private final UserService userService;
    private final StoryService storyService;
    private final StoryImageService storyImageService;

    public StoryResponse.Save save(StoryRequest.Save request) {
        User user = userService.findById(request.getUserId());

        Story story = Story.createStory(request, user);

        return new StoryResponse.Save(storyService.save(story));
    }
}
