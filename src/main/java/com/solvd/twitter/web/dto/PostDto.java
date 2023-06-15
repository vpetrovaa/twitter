package com.solvd.twitter.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private String id;

    @NotNull(message = "Text is required")
    @Size(min = 1, max = 70, message = "Text must be more than 1 and less than 200 " +
            "characters")
    private String text;

    private LocalDateTime postedTime;

}
