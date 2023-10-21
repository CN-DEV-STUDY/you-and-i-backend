package com.i.and.you.domain.story.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StoryResponse {

    private Long storyId;

    @Getter
    @Setter
    public static class Save extends StoryResponse {

        public Save(Long storyId) {
            super(storyId);
        }
    }

    @Getter
    @Setter
    public static class Update extends StoryResponse {
        public Update(Long storyId) {
            super(storyId);
        }
    }
}
