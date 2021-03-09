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
import jp.co.seattle.library.service.LendingService;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class LendingController {
    final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private BooksService booksService;
    @Autowired
    private LendingService lendingService;

    /**
     * 書籍を借りる
     *
     * @param bookId  書籍ID
     * @return　詳細画面に遷移
     */
    @Transactional
    @RequestMapping(value = "/rentBook", method = RequestMethod.POST)
    public String rentBook(Locale locale,
            @RequestParam("bookId") Integer bookId,
            Model model) {

        lendingService.regLendingData(bookId);

        model.addAttribute("bookInfo", booksService.getBookInfo(bookId));
        return "details";
    }

    /**
     * 書籍を返却する
     *
     * @param bookId  書籍ID
     * @return　詳細画面に遷移
     */
    @Transactional
    @RequestMapping(value = "/returnBook", method = RequestMethod.POST)
    public String returnBook(Locale locale,
            @RequestParam("bookId") Integer bookId,
            Model model) {

        lendingService.deleteLendingData(bookId);

        model.addAttribute("bookInfo", booksService.getBookInfo(bookId));
        return "details";
    }

}
