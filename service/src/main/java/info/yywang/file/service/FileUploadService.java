package info.yywang.file.service;

import info.yywang.file.enums.FileType;

/**
 * @author yanyan.wang
 * @date 2016-09-08 11:28
 */
public interface FileUploadService {

    /**
     * 上传。
     * <p><pre>
     * stream需关闭。
     * </pre>
     *
     * @param bizType      文件业务类型
     * @param originalName 文件原始名称
     * @param bytes        内容输入流
     * @return 附件的Id
     */
    int upload(String bizType, FileType fileType, String originalName, byte[] bytes);
}
