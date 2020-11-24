package jp.co.seattle.library.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jp.co.seattle.library.service.BooksService;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class AddBooksController {
    final static Logger logger = LoggerFactory.getLogger(AddBooksController.class);

    @Autowired
    private BooksService booksService;

    private static final int VALIDATEIN_ERROR = 1;
    private static final int VALIDATEIN_AUTHOR = 31;

    @RequestMapping(value = "/addBook", method = RequestMethod.GET) //value＝actionで指定したパラメータ
    //RequestParamでname属性を取得
    public String login(Model model) {
        return "addBook";
    }

    @Transactional
    @RequestMapping(value = "/insertBook", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String insertBook(Locale locale,
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("publisher") String publisher,
            @RequestParam("description") String description,
            @RequestParam("thumbnail") MultipartFile file,
            @RequestParam("publishDate") String publishDateStr,
            Model model) {
        logger.info("Welcome insertBooks.java! The client locale is {}.", locale);

        String forward = "detqils";

        // 著者名文字数チェック
        if (author.length() > VALIDATEIN_AUTHOR) {
            model.addAttribute("authorError", VALIDATEIN_ERROR);
            forward = "home";
        }

        // 出版日チェック
        // Date型変換
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date publishDate = null;
        try {
            publishDate = sdf.parse(publishDateStr);
        } catch (ParseException e) {
            model.addAttribute("pubulishDateError", VALIDATEIN_ERROR);
            forward = "home";
        }

        //
        if (forward.equals("home")) {
            return forward;
        }

        String thumbnail;
        thumbnail = file.getOriginalFilename();
        String path = new File(".").getAbsoluteFile().getParent();
        logger.info(path);
        try {
            // アップロードファイルを置く
            File uploadFile = new File("./src/main/webapp/resources/thumbnails/" + thumbnail);
            byte[] bytes = file.getBytes();
            BufferedOutputStream uploadFileStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
            uploadFileStream.write(bytes); //？
            uploadFileStream.close();

        } catch (Exception e) {
            // 異常終了時の処理
        } catch (Throwable t) {
            // 異常終了時の処理
        }

        booksService.registerBook(title, author, publisher, description, thumbnail, publishDate);

        model.addAttribute("resultMessage", "登録完了");
        // 書籍IDが最大の書籍情報を取得する
        model.addAttribute("bookInfo", booksService.getNewerBookInfo());

        return forward;
    }
}
