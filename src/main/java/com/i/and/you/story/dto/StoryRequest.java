package com.i.and.you.story.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class StoryRequest {
    private Long userId;
    private String content;
    private MultipartFile image;

        @Getter
        @Setter
        public static class Save extends StoryRequest {

        }

        @Getter
        @Setter
        public static class Update extends StoryRequest {

        }
}
