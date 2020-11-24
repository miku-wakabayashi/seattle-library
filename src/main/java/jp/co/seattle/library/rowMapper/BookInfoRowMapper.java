package jp.co.seattle.library.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import jp.co.seattle.library.dto.BookInfo;

@Configuration
public class BookInfoRowMapper implements RowMapper<BookInfo>{

	@Override
    public BookInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Query結果（ResultSet rs）を、Userオブジェクトに格納する実装
		BookInfo bookInfo = new BookInfo(
                rs.getInt("bookId")
                ,rs.getString("title")
                ,rs.getString("author")
                ,rs.getString("publisher")
                ,rs.getString("description")
                ,rs.getString("thumbnail")
                ,rs.getString("status")
        );

        return bookInfo;
    }

}