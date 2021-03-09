package jp.co.seattle.library.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.seattle.library.service.BooksService;

/**
 * Handles requests for the application home page.
 */
@Controller /** APIの入り口 */
public class LoginController {
    final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private BooksService booksService;

    @RequestMapping(value = "/", method = RequestMethod.GET) //value＝＞実行した場所
    public String first(Model model) {
        return "login"; //jspファイル名
    }

    /**
     * ログイン処理
     *
     * @param email メールアドレス
     * @param password パスワード
     * @param model
     * @return　ホーム画面に遷移
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {
        // 本来はPWとメールアドレスの認証などの処理をここに記載する
        // 今回は空実装にしてある

        logger.info("Welcome to home");

        String path1 = new File(".").getAbsoluteFile().getParent();
        logger.info(path1);

        // 本の情報を取得して画面側に渡す
        model.addAttribute("bookList", booksService.getBookList());
        return "home";

    }
}