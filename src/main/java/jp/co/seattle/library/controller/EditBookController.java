package jp.co.seattle.library.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jp.co.seattle.library.common.util.BookUtil;
import jp.co.seattle.library.dto.BookInfo;
import jp.co.seattle.library.dto.ErrorInfo;
import jp.co.seattle.library.service.BooksService;
import jp.co.seattle.library.service.ThumbnailService;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class EditBookController {
    final static Logger logger = LoggerFactory.getLogger(EditBookController.class);
    private static final String UPLAD_ERROR = "サムネイル画像のアップロードに失敗しました";

    @Autowired
    private BooksService booksService;
    @Autowired
    private ThumbnailService thumbnailService;
    @Autowired
    private BookUtil bookUtil;

    @Transactional
    @RequestMapping(value = "/editBook", method = RequestMethod.POST)
    public String editBook(Locale locale,
            @RequestParam("bookId") Integer bookId,
            Model model) {
        logger.info("Welcome edit.java! The client locale is {}.", locale);

        model.addAttribute("bookInfo", booksService.getBookInfo(bookId));

        return "edit";
    }

    @Transactional
    @RequestMapping(value = "/updateBook", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String editBook(Locale locale,
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("publisher") String publisher,
            @RequestParam("description") String description,
            @RequestParam("thumbnail") MultipartFile file,
            @RequestParam("publishDate") String publishDateStr,
            @RequestParam("bookId") Integer bookId,
            @RequestParam("isbn") String isbn,
            Model model, HttpServletRequest request) {
        logger.info("Welcome updateBooks.java! The client locale is {}.", locale);

        // パラメータで受け取った書籍情報をDtoに格納する。
        //フロントからDTOで渡すようにしてもいいが、一旦分かり易いようにここで格納している。
        BookInfo bookInfo = new BookInfo();
        bookInfo.setTitle(title);
        bookInfo.setAuthor(author);
        bookInfo.setPublisher(publisher);
        bookInfo.setPublishDate(publishDateStr);
        bookInfo.setDescription(description);
        bookInfo.setBookId(bookId);
        bookInfo.setIsbn(isbn);

        // バリデーションチェックNGだった場合、書籍追加画面に遷移
        List<ErrorInfo> errorList = bookUtil.validBookInfo(bookInfo);
        if (!CollectionUtils.isEmpty(errorList)) {
            model.addAttribute(bookInfo);
            model.addAttribute("errorList", errorList);
            return "editBook";
        }

        // クライアントのファイルシステムにある元のファイル名を設定する
        String thumbnail = file.getOriginalFilename();
        bookInfo.setThumbnail(thumbnail);
        bookInfo.setPublishDate(publishDateStr);

        if (!file.isEmpty()) {
            try {
                // サムネイル画像をアップロード
                thumbnailService.uploadThumbnail(thumbnail, file);

            } catch (IOException e) {
                // 異常終了時の処理
                logger.error("サムネイルアップロードでエラー発生", e);
                model.addAttribute("thumbnailUploadError", UPLAD_ERROR);
                model.addAttribute(bookInfo);
                return "addBook";
            }
        }

        booksService.updateBookInfo(bookInfo);

        model.addAttribute("resultMessage", "登録完了");
        model.addAttribute("bookInfo", booksService.getBookInfo(bookId));

        return "details";
    }

}
