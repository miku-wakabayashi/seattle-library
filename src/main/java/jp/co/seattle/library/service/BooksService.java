package jp.co.seattle.library.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import jp.co.seattle.library.dto.BookInfo;
import jp.co.seattle.library.rowMapper.BookInfoRowMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
//APIの入り口 APIとは、他のソフトウェアが外部から自分のソフトウェアへアクセスし利用できるようにしたもの
//ソフトウェアコンポーネントが互いにやりとりするのに使用するインタフェースの仕様
public class BooksService {
    final static Logger logger = LoggerFactory.getLogger(BooksService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    /**
     * 書籍リストを取得する
     *
     * @return 書籍リスト
     * @throws SQLException
     */
    public List<BookInfo> getBookList() {

        // JSPに渡すデータを設定する
        List<BookInfo> getedBookList = jdbcTemplate.query("select * from books",
                new BookInfoRowMapper());

        return getedBookList;
    }

    /**
     * 書籍IDに紐づく書籍情報を取得する
     *
     * @param bookId
     * @return 書籍情報
     */
    public BookInfo getBookInfo(int bookId) {

        // JSPに渡すデータを設定する
        String sql = "SELECT * from books WHERE id =" + bookId;
        logger.info(sql);
        BookInfo bookInfo = jdbcTemplate.queryForObject(sql, new BookInfoRowMapper());
        bookInfo.setPublishDate(bookInfo.getPublishDate());
        return bookInfo;
    }

    /**
     * 書籍情報を更新する
     *
     * @param bookInfo 書籍情報
     */
    public void updateBookInfo(BookInfo bookInfo) {

        String sql = "UPDATE books SET title ='" + bookInfo.getTitle() + "',author ='" + bookInfo.getAuthor()
                + "',publisher ='" + bookInfo.getPublisher()
                + "',publish_date ='" + bookInfo.getPublishDate()
                + "',description='" + bookInfo.getDescription()
                + "',thumbnail='" + bookInfo.getThumbnail()
                + "',isbn='" + bookInfo.getIsbn()
                + "',upd_date='" + timestamp
                + "' WHERE id =" + bookInfo.getBookId();
        jdbcTemplate.update(sql);
    }

    /**
     * 書籍を登録する
     *
     * @param title
     * @param author
     * @param publisher
     * @param description
     * @param thumbnail
     */
    public void registBook(BookInfo bookInfo) {

        String sql = "INSERT INTO books (title, author,publisher,publish_date,description,thumbnail,isbn,reg_date,upd_date) VALUES ('"
                + bookInfo.getTitle() + "','" + bookInfo.getAuthor() + "','" + bookInfo.getPublisher() + "',"
                + bookInfo.getPublishDate() + ",'"
                + bookInfo.getDescription() + "','"
                + bookInfo.getThumbnail() + "','"
                + bookInfo.getIsbn() + "',"
                + "sysdate(),"
                + "sysdate())";
        jdbcTemplate.update(sql);
    }

    /**
     * BookIdが最大の書籍情報を取得する
     *
     * @return 書籍情報
     */
    public BookInfo getNewerBookInfo() {

        // JSPに渡すデータを設定する
        String sql = "SELECT * FROM books WHERE id = (SELECT MAX(id) FROM books)";
        BookInfo Booklist = jdbcTemplate.queryForObject(sql, new BookInfoRowMapper());
        return Booklist;
    }

    /**
     * 削除処理を行う
     *
     * @param bookId 書籍ID
     */
    public void deleteBook(Integer bookId) {
        String sql = "DELETE FROM books WHERE id =" + bookId;
        jdbcTemplate.execute(sql);
    }
}
