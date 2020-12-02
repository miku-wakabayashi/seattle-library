package jp.co.seattle.library.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;

import jp.co.seattle.library.dto.BookInfo;

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

    private static final String DEL_FLG_OFF = "0";
    private static final String STATUS_FREE = "0";

    /**
     * 書籍リストを取得する
     *
     * @return 書籍リスト
     */
    public List<BookInfo> getBookList() {

        // JSPに渡すデータを設定する
        RowMapper<BookInfo> rowMapper = new BeanPropertyRowMapper<BookInfo>(BookInfo.class);
        List<BookInfo> Booklist = jdbcTemplate.query("select * from books", rowMapper);
        return Booklist;
    }

    /**
     * 書籍IDに紐づく書籍情報を取得する
     *
     * @param bookId
     * @return 書籍情報
     */
    public BookInfo getBookInfo(int bookId) {

        // JSPに渡すデータを設定する
        RowMapper<BookInfo> rowMapper = new BeanPropertyRowMapper<BookInfo>(BookInfo.class);
        String sql = "SELECT * from books WHERE id =" + bookId;
        logger.info(sql);
        BookInfo bookInfo = jdbcTemplate.queryForObject(sql, rowMapper);
        bookInfo.setPublishDate(bookInfo.getPublishDate());
        return bookInfo;
    }

    public int getMaxBookId() {

        int bookMaxId = jdbcTemplate.queryForObject(
                "select Max(book_id) as maxId from books ORDER BY id DESC", Integer.class);

        return bookMaxId;
    }

    /**
     * 書籍情報を更新する
     *
     * @param bookId
     * @param title
     * @param author
     * @param publisher
     * @param description
     * @param thumbnail
     */
    public void updateBook(int bookId, String title, String author, String publisher, String description,
            String thumbnail) {

        String sql = "UPDATE  `books ` SET title ='" + title + "',author ='" + author + "',publisher ='" + publisher
                + "',description='" + description + "',thumbnail='" + thumbnail + "' WHERE id =" + bookId;
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
        String sql = "INSERT INTO books (title, author,publisher,publish_date,description,thumbnail,update_date,del_flg,status) VALUES ('"
                + bookInfo.getTitle() + "','" + bookInfo.getAuthor() + "','" + bookInfo.getPublisher() + "',"
                + bookInfo.getPublishDate() + ",'"
                + bookInfo.getDescription() + "','" + bookInfo.getThumbnail()
                + "',sysdate(),'" + DEL_FLG_OFF + "','" + STATUS_FREE + "')";
        jdbcTemplate.update(sql);
    }

    /**
     * BookIdが最大の書籍情報を取得する
     *
     * @return 書籍情報
     */
    public BookInfo getNewerBookInfo() {

        // JSPに渡すデータを設定する
        RowMapper<BookInfo> rowMapper = new BeanPropertyRowMapper<BookInfo>(BookInfo.class);
        String sql = "SELECT * from books WHERE id = (select max(id) from books)";
        logger.info(sql);
        BookInfo Booklist = jdbcTemplate.queryForObject(sql, rowMapper);
        return Booklist;
    }

    /**
     * 論理削除処理を行う
     *
     * @param bookId
     */
    public void deleteBook(Integer bookId) {
        String sql = "UPDATE  `books ` SET  del_flg ='1'" + " WHERE id =" + bookId;
        jdbcTemplate.update(sql);

    }
}
