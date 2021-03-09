package jp.co.seattle.library.dto;

import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class ErrorInfo {

    private String errorMessage;

    public ErrorInfo() {

    }

    public ErrorInfo(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}