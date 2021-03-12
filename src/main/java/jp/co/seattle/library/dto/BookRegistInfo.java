package jp.co.seattle.library.dto;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class BookRegistInfo {
    private String bookId;

    private String title;

    private String author;

    private String publisher;

    private String description;

    private String isbn;

    private String publishDate;

    private String thumbnail;

    public BookRegistInfo() {

    }

    public BookRegistInfo(String bookId, String title, String author, String publisher, String description, String isbn,
            String publishDate, String thumbnail) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.thumbnail = thumbnail;
    }

}