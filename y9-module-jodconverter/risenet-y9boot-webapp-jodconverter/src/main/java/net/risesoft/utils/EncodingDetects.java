package net.risesoft.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.mozilla.universalchardet.UniversalDetector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncodingDetects {
    private static UniversalDetector detector = new UniversalDetector(null);
    private static final int DEFAULT_LENGTH = 4096;

    public static String getJavaEncode(String filePath) {
        return getJavaEncode(new File(filePath));
    }

    public static String getJavaEncode(File file) {
        int len = Math.min(DEFAULT_LENGTH, (int)file.length());
        byte[] content = new byte[len];
        try (InputStream fis = Files.newInputStream(file.toPath())) {
            fis.read(content, 0, len);
        } catch (IOException e) {
            LOGGER.error("文件读取失败:{}", file.getPath());
        }
        return getJavaEncode(content);
    }

    public static String getJavaEncode(byte[] content) {
        detector.reset();
        detector.handleData(content, 0, content.length);
        detector.dataEnd();
        String charsetName = detector.getDetectedCharset();
        if (charsetName == null) {
            charsetName = Charset.defaultCharset().name();
        }
        return charsetName;
    }
}
