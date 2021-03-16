package jp.co.seattle.library.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
import jp.co.seattle.library.dto.BookDetailsInfo;
import jp.co.seattle.library.dto.ErrorInfo;
import jp.co.seattle.library.service.BooksService;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class BulkRegistController {
    final static Logger logger = LoggerFactory.getLogger(BulkRegistController.class);

    @Autowired
    private BooksService booksService;
    @Autowired
    private BookUtil bookUtil;

    @Autowired
    private static final String COMMA = ",";
    private static final int SPLIT_LIMIT = -1;

    @RequestMapping(value = "/bulkRegistration", method = RequestMethod.GET) //value＝actionで指定したパラメータ
    //RequestParamでname属性を取得
    public String login(Model model) {
        // 一括登録画面に遷移
        return "bulkRegist";
    }

    @Transactional
    @RequestMapping(value = "/bulkRegist", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    public String insertBook(Locale locale,
            @RequestParam("csvFile") MultipartFile csvFile,
            Model model) {

        List<String> lines = new ArrayList<String>();
        String line = null;
        List<ErrorInfo> allErrorList = new ArrayList<ErrorInfo>();

        List<BookDetailsInfo> bookList = new ArrayList<BookDetailsInfo>();
        // CSVファイル読み込み
        try {
            InputStream stream = csvFile.getInputStream();
            Reader reader = new InputStreamReader(stream);
            BufferedReader buf = new BufferedReader(reader);
            int lineNum = 0;

            // 1行ずつデータを取得する
            while ((line = buf.readLine()) != null) {
                lineNum++;
                lines.add(line);
                String data[] = line.split(COMMA, SPLIT_LIMIT);

                BookDetailsInfo bookInfo = mapData(data);
                // 登録時にnullの場合空文字を登録するようにする。
                bookInfo.setThumbnail("");

                // バリデーションチェックNGだった場合エラーリストにエラー文言追加
                if (!CollectionUtils.isEmpty(bookUtil.validBookInfo(bookInfo))) {
                    ErrorInfo errorInfo = new ErrorInfo();
                    errorInfo.setErrorMessage(lineNum + "行目の書籍情報登録でバリデーションエラー");
                    allErrorList.add(errorInfo);

                } else {
                    // 書籍リストに追加
                    bookList.add(bookInfo);
                }
            }

        } catch (Exception e) {
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrorMessage("CSVファイル読み込みでエラーが発生しました。");
            allErrorList.add(errorInfo);

        }

        // 1つでもエラーがあった場合
        if (!CollectionUtils.isEmpty(allErrorList)) {
            model.addAttribute("errorList", allErrorList);
            return "bulkRegist";
        }

        // 書籍リストの値を登録する
        for (BookDetailsInfo bookInfo : bookList) {
            booksService.registBook(bookInfo);
        }

        model.addAttribute("resultMessage", "登録完了");
        return "bulkRegist";
    }

    private BookDetailsInfo mapData(String[] data) {
        BookDetailsInfo bookInfo = new BookDetailsInfo();
        bookInfo.setTitle(data[0]);
        bookInfo.setAuthor(data[1]);
        bookInfo.setPublisher(data[2]);
        bookInfo.setPublishDate(data[3]);
        bookInfo.setIsbn(data[4]);
        bookInfo.setDescription(data[5]);

        return bookInfo;
    }

}
