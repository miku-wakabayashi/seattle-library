package jp.co.seattle.library.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class ThumbnailService {
    final static Logger logger = LoggerFactory.getLogger(ThumbnailService.class);

    /**
     * サムネイル画像をアップロードする
     * @param thumbnail
     * @throws IOException
     */
    public void uploadThumbnail(String thumbnail, MultipartFile file) throws IOException {

        // アップロードファイルを置く

        // 保存先を定義
        String uploadPath = "../workspace/SeattleLibrary/src/main/webapp/resources/thumbnails/";

        byte[] bytes = file.getBytes();

        // 指定ファイルへ読み込みファイルを書き込み
        BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(new File(uploadPath + thumbnail)));
        stream.write(bytes);
        stream.close();

    }

}
