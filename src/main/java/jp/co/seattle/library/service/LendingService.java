package jp.co.seattle.library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

/**
 * Handles requests for the application home page.
 */
@Controller
//APIの入り口 APIとは、他のソフトウェアが外部から自分のソフトウェアへアクセスし利用できるようにしたもの
//ソフトウェアコンポーネントが互いにやりとりするのに使用するインタフェースの仕様
public class LendingService {
    final static Logger logger = LoggerFactory.getLogger(LendingService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 書籍を借りる
     * @param bookId 書籍ID
     */
    public void regLendingData(Integer bookId) {

        String sql = "INSERT INTO lending_manage (book_id,rent_date,reg_date,upd_date) VALUES ("
                + bookId
                + ",sysdate(),sysdate(),sysdate())";
        jdbcTemplate.update(sql);
    }

    /**
     * 貸出管理テーブルのデータを削除する
     * @param bookId 書籍ID
     */
    public void deleteLendingData(Integer bookId) {

        String sql = "delete from lending_manage where book_id = "
                + bookId;
        jdbcTemplate.update(sql);
    }

}
