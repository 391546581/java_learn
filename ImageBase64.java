package com.test;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author BlueWang
 * @ClassName: ImageBase64
 * @Description:
 * @date 2019/10/15 11:15
 */
/**
 *
 * <!-- 图片缩略图 -->
 * <dependency>
 * <groupId>net.coobird</groupId>
 * <artifactId>thumbnailator</artifactId>
 * <version>0.4.8</version>
 * </dependency>
 */

public class ImageBase64 {

    public static final String B_JPG = "apos-mall-app/src/test/1.jpg";
    public static final String BASE_64 = "data:image/jpeg;base64,";

    public static void main(String[] args) {
        try {
            final byte[] bytes = Files.readAllBytes(Paths.get(B_JPG));
            System.out.println(BASE_64 +new String(Base64Utils.encode(bytes)));
            System.out.println(new File(B_JPG).length()/1024 +"KB");

            String path = "apos-mall-app/src/test/temp.jpg";
            Thumbnails.of(B_JPG).scale(1.0f).outputQuality(0.5f).outputFormat("jpg").toFile(path);
//            final BufferedImage jpg = Thumbnails.of(B_JPG).scale(1.0f).outputQuality(0.5f).outputFormat("jpg").asBufferedImage();


//            byte[] bytes1 = ((DataBufferByte) jpg.getData().getDataBuffer()).getData();

            System.out.println(BASE_64+new String(Base64Utils.encode(Files.readAllBytes(Paths.get(path)))));
            System.out.println(new File(path).length()/1024 +"KB");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}