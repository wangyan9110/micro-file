package info.yywang.file.utils;


import info.yywang.file.enums.FileType;
import info.yywang.file.service.FileUploadService;
import info.yywang.micro.common.exceptions.BizException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @author yanyan.wang
 * @date 2016-04-07 17:07
 */
public class FileUtils {

    private static FileUploadService fileUploadService = (FileUploadService) SpringContextUtils.getBean("fileUploadService");

    public static int upload(String bizType, FileType fileType, String originalName, InputStream inputStream) {
        try {
            ByteArrayOutputStream ops = new ByteArrayOutputStream();
            int c;
            while ((c = inputStream.read()) != -1) {
                ops.write(c);
            }
            ops.flush();
            return fileUploadService.upload(bizType, fileType, originalName, ops.toByteArray());
        } catch (IOException e) {
            throw new BizException(e.getMessage(), e);
        }
    }

    public static int upload(String bizType, FileType fileType, String originalName, ByteBuffer buffer) {
        return fileUploadService.upload(bizType, fileType, originalName, buffer.array());
    }
}
