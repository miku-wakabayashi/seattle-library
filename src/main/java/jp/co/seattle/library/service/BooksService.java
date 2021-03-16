package jp.co.seattle.library.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import jp.co.seattle.library.dto.BookDetailsInfo;
import jp.co.seattle.library.dto.BookInfo;
import jp.co.seattle.library.rowMapper.BookDetailsInfoRowMapper;
import jp.co.seattle.library.rowMapper.BookInfoRowMapper;
import software.amazon.awssdk.utils.StringUtils;

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

    private static final String LENDING_STATUS_ON = "貸出し可";

    /**
     * 書籍リストを取得する
     *
     * @return 書籍リスト
     * @throws SQLException
     */
    public List<BookInfo> getBookList() {

        // JSPに渡すデータを設定する
        List<BookInfo> getedBookList = jdbcTemplate.query(
                "select * from books",
                new BookInfoRowMapper());

        return getedBookList;
    }

    /**
     * 書籍IDに紐づく書籍詳細情報を取得する
     *
     * @param bookId
     * @return 書籍情報
     */
    public BookDetailsInfo getBookInfo(int bookId) {

        // JSPに渡すデータを設定する
        String sql = "SELECT b.id, b.title, b.author, b.description, b.publisher,b.publish_date,b.thumbnail,b.isbn,(select lending_status from lending_manage where book_id ="
                + bookId
                + ")as lending_status FROM books b LEFT OUTER JOIN lending_manage lm on b.id = lm.book_id WHERE b.id ="
                + bookId;

        BookDetailsInfo bookDetailsInfo = jdbcTemplate.queryForObject(sql, new BookDetailsInfoRowMapper());
        if (StringUtils.isEmpty(bookDetailsInfo.getLendingStatus())) {
            bookDetailsInfo.setLendingStatus(LENDING_STATUS_ON);
        }
        return bookDetailsInfo;
    }

    /**
     * 書籍情報を更新する
     *
     * @param bookInfo 書籍情報
     */
    public void updateBookInfo(BookDetailsInfo bookInfo) {

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
    public void registBook(BookDetailsInfo bookInfo) {

        String sql = "INSERT INTO books (title, author,publisher,publish_date,description,thumbnail,isbn,reg_date,upd_date) VALUES ('"
                + bookInfo.getTitle() + "','" + bookInfo.getAuthor() + "','" + bookInfo.getPublisher() + "',"
                + bookInfo.getPublishDate() + ",'"
                + bookInfo.getDescription() + "','"
                + bookInfo.getThumbnail() + "','"
                + bookInfo.getIsbn() + "',"
                + "sysdate(),"
                + "sysdate())";

        logger.info(sql);
        jdbcTemplate.update(sql);
    }

    /**
     * BookIdが最大の書籍詳細情報を取得する
     *
     * @return 書籍情報
     */
    public BookDetailsInfo getNewerBookInfo() {

        // JSPに渡すデータを設定する
        String sql = "SELECT b.id, b.title, b.author, b.description, b.publisher,b.publish_date,b.thumbnail,b.isbn,lm.book_id as lending_status FROM books b LEFT OUTER JOIN lending_manage lm on b.id = lm.book_id WHERE b.id = (SELECT MAX(id) FROM books)";
        BookDetailsInfo BookDetaillist = jdbcTemplate.queryForObject(sql, new BookDetailsInfoRowMapper());
        return BookDetaillist;
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
