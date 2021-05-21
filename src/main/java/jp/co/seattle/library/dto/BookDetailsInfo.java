package jp.co.seattle.library.dto;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class BookDetailsInfo {

    private int bookId;

    private String title;

    private String author;

    private String publisher;

    private String description;

    private String isbn;

    private String publishDate;

    private String thumbnailUrl;

    private String thumbnailName;

    private String lendingStatus;

    public BookDetailsInfo() {

    }

    public BookDetailsInfo(int bookId, String title, String author, String publisher, String description, String isbn,
            String publishDate, String thumbnailUrl, String thumbnailName, String lendingStatus) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailName = thumbnailName;
        this.lendingStatus = lendingStatus;
    }

}