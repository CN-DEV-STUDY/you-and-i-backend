package com.i.and.you.domain.story.facade;

import com.i.and.you.domain.story.dto.StoryRequest;
import com.i.and.you.domain.story.dto.StoryResponse;
import com.i.and.you.domain.story.entity.Story;
import com.i.and.you.domain.story.service.StoryImageService;
import com.i.and.you.domain.story.service.StoryService;
import com.i.and.you.domain.user.entity.User;
import com.i.and.you.domain.user.service.UserService;
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
