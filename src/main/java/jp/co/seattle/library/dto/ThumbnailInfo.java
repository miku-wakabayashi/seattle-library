package jp.co.seattle.library.dto;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ThumbnailInfo {

    private String thumbnailName;
    private String thumbnailUrl;

    public ThumbnailInfo() {

    }

    public ThumbnailInfo(String thumbnailName, String thumbnailUrl) {
        this.thumbnailName = thumbnailName;
        this.thumbnailUrl = thumbnailUrl;

    }

}