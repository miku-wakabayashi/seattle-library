package jp.co.seattle.library.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import jp.co.seattle.library.dto.ErrorInfo;
import jp.co.seattle.library.dto.UserInfo;

/**
 * Handles requests for the application home page.
 */
@Controller
//APIの入り口 APIとは、他のソフトウェアが外部から自分のソフトウェアへアクセスし利用できるようにしたもの
//ソフトウェアコンポーネントが互いにやりとりするのに使用するインタフェースの仕様
public class UserUtil {
    final static Logger logger = LoggerFactory.getLogger(UserUtil.class);
    private static final String EMAIL_VALID_ERROR = "ISBNの桁数または半角数字が正しくありません";
    private static final String PASSWORD_VALID_ERROR = "出版日は半角数字のYYYYMMDD形式で入力してください";

    /**
     * ユーザー情報バリデーションチェック
     * @param userInfo
     * @return errorList
     */
    public List<ErrorInfo> validUserInfo(UserInfo userInfo) {
        List<ErrorInfo> errorList = new ArrayList<ErrorInfo>();

        if (!isValidPassword(userInfo.getPassword())) {
            // エラーを設定
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage(PASSWORD_VALID_ERROR);
            errorList.add(error);
        }

        if (!isValidEmail(userInfo.getEmail())) {
            // エラーを設定
            ErrorInfo error = new ErrorInfo();
            error.setErrorMessage(EMAIL_VALID_ERROR);
            errorList.add(error);
        }

        return errorList;
    }

    /**
     * メールのバリデーションチェック
     * メール形式かどうか
     * @param isbn
     * @return true: メール形式
     *         false:メール形式ではない
     */
    public boolean isValidEmail(String mail) {
        String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        // 正規表現regexがtargetにマッチするか確認する
        Pattern p = Pattern.compile(regex); // 正規表現パターンの読み込み
        Matcher m = p.matcher(mail); // パターンと検査対象文字列の照合
        return m.matches(); // 照合結果をtrueかfalseで返却;
    }

    /**
     * パスワードのバリデーションチェック
     * 半角英数字かどうか
     * @param password
     * @return true:半角英数字
     *         false:半角英数字ではない
     */
    public boolean isValidPassword(String password) {
        String regex = "^[A-Za-z0-9]+$";
        // 正規表現regexがtargetにマッPasswordチするか確認する
        Pattern p = Pattern.compile(regex); // 正規表現パターンの読み込み
        Matcher m = p.matcher(password); // パターンと検査対象文字列の照合
        return m.matches(); // 照合結果をtrueかfalseで返却;
    }

}
