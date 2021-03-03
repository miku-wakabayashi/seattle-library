package jp.co.seattle.library.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.seattle.library.service.BooksService;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class AccountController {
    final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BooksService booksService;

    @RequestMapping(value = "/new", method = RequestMethod.POST) //value＝actionで指定したパラメータ
    public String createAccount(Model model) {
        return "new";
    }

    /**
     * 新規アカウント作成
     *
     * @param email
     * @param name
     * @param password
     * @param model
     * @return　ホーム画面に遷移
     */
    @Transactional
    @RequestMapping(value = "/newAccount", method = RequestMethod.POST)
    public String createAccount(Locale locale,
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("password1") String password,
            Model model) {
        logger.info("Welcome register! The client locale is {}.", locale);

        // 本当は画面からパラメータを受け取って登録したい気分・・・
        String createUser = "INSERT INTO `users` (`email`, `name`,`password`) VALUES ('" + email + "','" + name + "','"
                + password + "')";
        jdbcTemplate.update(createUser);

        model.addAttribute("items", booksService.getBookList());

        return "home";
    }
}
