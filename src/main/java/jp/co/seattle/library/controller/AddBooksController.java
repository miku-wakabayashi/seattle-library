package jp.co.seattle.library.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import jp.co.seattle.library.dto.BookInfo;
import jp.co.seattle.library.service.BooksService;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class AddBooksController {
    final static Logger logger = LoggerFactory.getLogger(AddBooksController.class);

    @Autowired
    private BooksService booksService;

    private static final String AUTHOR_ERROR = "著者名は30文字以内で入力してください";
    private static final String PUBLISHDATE_ERROR = "出版日はYYYYMMDD形式で入力してください";

    private static final String UPLAD_ERROR = "サムネイル画像のアップロードに失敗しました";
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

        // パラメータで受け取った書籍情報をDtoに格納する。
        // TODO フロントからDTOで渡すようにしてもいいが、一旦分かり易いようにここで格納している。
        BookInfo bookInfo = new BookInfo();
        bookInfo.setTitle(title);
        bookInfo.setAuthor(author);
        bookInfo.setPublisher(publisher);
        bookInfo.setDescription(description);
        // クライアントのファイルシステムにある元のファイル名を設定する
        String thumbnail = file.getOriginalFilename();
        logger.info(file.getName());
        bookInfo.setThumbnail(thumbnail);
        bookInfo.setPublishDate(publishDateStr);
        logger.info(thumbnail);

        boolean isValid = true;

        // 著者名文字数チェック
        if (author.length() > VALIDATEIN_AUTHOR) {
            // エラーを設定
            model.addAttribute("authorError", AUTHOR_ERROR);
            isValid = false;
        }

        // 出版日チェック
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");

        try {
            // Date型変換
            Date publishDate = sdf.parse(publishDateStr);
        } catch (ParseException e) {
            // Date型に変換できなかった場合エラーを設定する
            model.addAttribute("pubulishDateError", PUBLISHDATE_ERROR);
            isValid = false;
        }

        // バリデーションチェックNGだった場合、書籍追加画面に遷移
        if (!isValid) {
            model.addAttribute(bookInfo);
            return "addBook";
        }
        if (!file.isEmpty()) {
            try {
                // サムネイル画像をアップロード
                uploadThumbnail(thumbnail, file);
            } catch (IOException e) {
                // 異常終了時の処理
                logger.error("サムネイルアップロードでエラー発生", e);
                model.addAttribute("thumbnailUploadError", UPLAD_ERROR);
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

    ////////////////
    // 下請けメソッド
    /////
    /**
     * サムネイル画像をアップロードする
     * @param thumbnail
     * @throws IOException
     */
    private void uploadThumbnail(String thumbnail, MultipartFile file) throws IOException {
        // アップロードファイルを置く
        File uploadFile = new File("./src/main/webapp/resources/thumbnails/" + thumbnail);
        byte[] bytes = file.getBytes();
        BufferedOutputStream uploadFileStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
        uploadFileStream.write(bytes); //？
        uploadFileStream.close();
    }

}
