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

    private String isbn;

    private String publishDate;

    private String thumbnail;

    public BookInfo() {

    }

    public BookInfo(int bookId, String title, String author, String publisher, String description, String isbn,
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