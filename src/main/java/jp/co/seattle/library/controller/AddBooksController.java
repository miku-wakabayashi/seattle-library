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
public class AddBooksController {
    final static Logger logger = LoggerFactory.getLogger(AddBooksController.class);

    @Autowired
    private BooksService booksService;
    @Autowired
    private BookUtil bookUtil;

    @Autowired
    private ThumbnailService thumbnailService;

    private static final String UPLAD_ERROR = "サムネイル画像のアップロードに失敗しました";

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
            @RequestParam("isbn") String isbn,
            Model model, HttpServletRequest request) {
        logger.info("Welcome insertBooks.java! The client locale is {}.", locale);

        // パラメータで受け取った書籍情報をDtoに格納する。
        // フロントからDTOで渡すようにしてもいいが、一旦分かり易いようにここで格納している。
        BookInfo bookInfo = new BookInfo();
        bookInfo.setTitle(title);
        bookInfo.setAuthor(author);
        bookInfo.setPublisher(publisher);
        bookInfo.setDescription(description);
        bookInfo.setIsbn(isbn);

        // クライアントのファイルシステムにある元のファイル名を設定する
        String thumbnail = file.getOriginalFilename();
        bookInfo.setThumbnail(thumbnail);
        bookInfo.setPublishDate(publishDateStr);
        bookInfo.setIsbn(isbn);

        // バリデーションチェックNGだった場合、書籍追加画面に遷移
        List<ErrorInfo> errorList = bookUtil.validBookInfo(bookInfo);
        if (!CollectionUtils.isEmpty(errorList)) {
            model.addAttribute(bookInfo);
            model.addAttribute("errorList", errorList);
            return "addBook";
        }

        if (!file.isEmpty()) {
            try {
                // サムネイル画像をアップロード
                thumbnailService.uploadThumbnail(thumbnail, file);
            } catch (IOException e) {
                // 異常終了時の処理
                logger.error("サムネイルアップロードでエラー発生", e);
                ErrorInfo error = new ErrorInfo();
                error.setErrorMessage(UPLAD_ERROR);
                errorList.add(error);
                model.addAttribute(errorList);
                model.addAttribute(bookInfo);
                return "addBook";
            }
        }

        // 書籍情報を新規登録する
        booksService.registBook(bookInfo);

        model.addAttribute("resultMessage", "登録完了");
        // 書籍IDが最大の書籍情報を取得する
        model.addAttribute("bookInfo", booksService.getNewerBookInfo());
        return "details";
    }

}
