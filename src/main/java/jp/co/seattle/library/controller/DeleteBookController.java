package jp.co.seattle.library.controller;

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

import jp.co.seattle.library.service.BooksService;
import jp.co.seattle.library.service.ThumbnailService;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class DeleteBookController {
    final static Logger logger = LoggerFactory.getLogger(DeleteBookController.class);

    @Autowired
    private BooksService booksService;
    @Autowired
    private ThumbnailService thumbnailService;

    /**
     * 削除処理サンプルメソッド
     *
     * @param locale ロケール情報
     * @param model モデル情報
     * @return 遷移先画面名
     */
    @Transactional
    @RequestMapping(value = "/deleteBook", method = RequestMethod.POST)
    public String deleteBook(
            Locale locale,
            @RequestParam("bookId") Integer bookId,
            Model model) {
        logger.info("Welcome delete! The client locale is {}.", locale);

        // 削除前にサムネイルファイル名を取得しておく
        String beforeThumbnaileName = booksService.getThumbnailName(bookId);
        // データ削除
        booksService.deleteBook(bookId);
        // サムネイルファイル削除
        thumbnailService.deleteTumbnail(beforeThumbnaileName);
        model.addAttribute("resultMessage", "削除完了");

        model.addAttribute("bookList", booksService.getBookList());

        return "home";

    }

}
