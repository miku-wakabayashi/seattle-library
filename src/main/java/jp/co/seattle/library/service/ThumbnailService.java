package jp.co.seattle.library.service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import jp.co.seattle.library.config.MinioConfig;

/**
 * Handles requests for the application home page.
 */
@Controller //APIの入り口
public class ThumbnailService {
    final static Logger logger = LoggerFactory.getLogger(ThumbnailService.class);

    private static final String S3_OBJECT_THUMBNAILS = "thumbnails/";
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioConfig minioConfig;

    /**
     * サムネイル画像をアップロードする
     * @param thumbnail
     * @return アップロードファイル名
     * @throws Exception
     */
    public String uploadThumbnail(String thumbnailName, MultipartFile file)
            throws Exception {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String extension = thumbnailName.substring(thumbnailName.lastIndexOf("."));

        //ファイル名をタイムスタンプにする
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestampStr = (sdf.format(timestamp));
        String fileName = timestampStr + extension;

        // S3にサムネイル画像をアップロード
        InputStream inputStream = file.getInputStream();
        ObjectWriteResponse owr = minioClient.putObject(
                PutObjectArgs.builder().bucket(minioConfig.getMinioInfo("s3.bucket-name"))
                        .object(S3_OBJECT_THUMBNAILS + fileName)
                        .stream(
                                inputStream, -1, 10485760)
                        .build());

        return fileName;

    }

    /**
     * URL取得
     * @param fileName
     * @return ファイルのURL
     * @throws Exception
     */
    public String getURL(String fileName) throws Exception {
        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(minioConfig.getMinioInfo("s3.bucket-name"))
                        .object(S3_OBJECT_THUMBNAILS + fileName)
                        .build());

        return url;

    }

    public void deleteTumbnail(String fileName) {
        if (!StringUtils.isEmpty(fileName)) {
            try {
                minioClient.removeObject(
                        RemoveObjectArgs.builder().bucket(minioConfig.getMinioInfo("s3.bucket-name"))
                                .object(S3_OBJECT_THUMBNAILS + fileName).build());

            } catch (Exception e) {
                logger.error(fileName + "：　サムネイル画像削除でエラーが発生しました。");
            }
        }
    }

}
