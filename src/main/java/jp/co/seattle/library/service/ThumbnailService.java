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
    private static final String UPLOAD_DIR = "../workspace/SeattleLibrary/src/main/webapp/resources/thumbnails/";

    /**
     * サムネイル画像をアップロードする
     * @param thumbnail
     * @throws IOException
     */
    public void uploadThumbnail(String thumbnail, MultipartFile file) throws IOException {
        thumbnail = file.getOriginalFilename();
        String path = new File(".").getAbsoluteFile().getParent();

        // アップロードファイルを置く
        File uploadFile = new File("./src/main/webapp/resources/thumbnails/" + thumbnail);
        byte[] bytes = file.getBytes();
        BufferedOutputStream uploadFileStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
        uploadFileStream.write(bytes);
        uploadFileStream.close();
    }

}
