package jp.co.seattle.library.dto;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class BookInfo {

    private int bookId;

    private String title;

    private String author;

    private String publisher;

    private String description;

    private String thumbnail;

    public String status;

    public BookInfo() {

    }

    public BookInfo(int bookId, String title, String author, String publisher, String description, String thumbnail,
            String status) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.thumbnail = thumbnail;
        this.status = status;
    }

}