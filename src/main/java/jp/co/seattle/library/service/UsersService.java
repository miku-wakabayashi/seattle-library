package jp.co.seattle.library.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import jp.co.seattle.library.dto.UserInfo;
import jp.co.seattle.library.rowMapper.UserCountRowMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
//APIの入り口 APIとは、他のソフトウェアが外部から自分のソフトウェアへアクセスし利用できるようにしたもの
//ソフトウェアコンポーネントが互いにやりとりするのに使用するインタフェースの仕様
public class UsersService {
    final static Logger logger = LoggerFactory.getLogger(UsersService.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * ユーザー情報を登録する
     *
     * @return ユーザー情報
     * @throws SQLException
     */
    public void registUser(UserInfo userInfo) {

        String sql = "INSERT INTO users (email, password,reg_date,upd_date) VALUES ('"
                + userInfo.getEmail()
                + "','"
                + userInfo.getPassword()
                + "',sysdate(),sysdate()" + ")";
        jdbcTemplate.update(sql);
    }

    public UserInfo selectUserInfo(String email, String password) {
        String sql = "SELECT email,password FROM users WHERE email = '"
                + email
                + "'";

        UserInfo selectedUserInfo = new UserInfo();
        try {
            selectedUserInfo = jdbcTemplate.queryForObject(sql, new UserCountRowMapper());
        } catch (EmptyResultDataAccessException e) {
            selectedUserInfo = null;
        }

        return selectedUserInfo;

    }

}
