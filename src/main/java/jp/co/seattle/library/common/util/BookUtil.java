package jp.co.seattle.library.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import jp.co.seattle.library.dto.BookDetailsInfo;
import jp.co.seattle.library.dto.ErrorInfo;

/**
 * Handles requests for the application home page.
 */
@Controller
//APIの入り口 APIとは、他のソフトウェアが外部から自分のソフトウェアへアクセスし利用できるようにしたもの
//ソフトウェアコンポーネントが互いにやりとりするのに使用するインタフェースの仕様
public class BookUtil {
    final static Logger logger = LoggerFactory.getLogger(BookUtil.class);
    private static final String ISBN_ERROR = "ISBNの桁数または半角数字が正しくありません";
    private static final String PUBLISHDATE_ERROR = "出版日は半角数字のYYYYMMDD形式で入力してください";

    public List<ErrorInfo> validBookInfo(BookDetailsInfo bookinfo) {
        List<ErrorInfo> errorList = new ArrayList<ErrorInfo>();

        // ISBNバリデーションチェック
        if (!bookinfo.getIsbn().isEmpty()) {
            if (!isValidISBN(bookinfo.getIsbn())) {
                // エラーを設定
                ErrorInfo error = new ErrorInfo();
                error.setErrorMessage(ISBN_ERROR);
                errorList.add(error);
            }
        }

        // 出版日チェック
        if (!isDateFormat(bookinfo.getPublishDate())) {
            // Date型に変換できなかった場合エラーを設定する
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage(PUBLISHDATE_ERROR);
            errorList.add(error);
        }
        return errorList;
    }

    /**
     * ISBNのバリデーションチェック
     * 10桁または13桁の半角英数字かどうかチェック
     * @param isbn
     * @return true: 10桁または13桁の半角英数字
     *         false:10桁または13桁の半角英数字ではない
     */
    public boolean isValidISBN(String isbn) {
        boolean result = false;
        String regex10 = "[0-9]{10}";
        // 正規表現regexがtargetにマッチするか確認する
        Pattern p10 = Pattern.compile(regex10); // 正規表現パターンの読み込み
        Matcher m10 = p10.matcher(isbn); // パターンと検査対象文字列の照合
        result = m10.matches(); // 照合結果をtrueかfalseで取得

        if (!result) {
            String regex13 = "[0-9]{13}";
            Pattern p13 = Pattern.compile(regex13); // 正規表現パターンの読み込み
            Matcher m13 = p13.matcher(isbn); // パターンと検査対象文字列の照合
            result = m13.matches(); // 照合結果をtrueかfalseで取得
            return result;
        }

        return result; // 照合結果をtrueかfalseで返却;
    }

    /**
     *  日付型(yyyymmdd)かチェック
     * @param target 対象文字列
     * @return true:日付型
     *         false:日付型ではない
     */
    public boolean isDateFormat(String target) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");

        // Date型変換
        try {
            Date publishDate = sdf.parse(target);
        } catch (ParseException e) {
            return false;
        }

        return true;

    }
}
