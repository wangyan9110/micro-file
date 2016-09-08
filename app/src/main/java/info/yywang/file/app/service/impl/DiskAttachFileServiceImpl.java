package info.yywang.file.app.service.impl;

import info.yywang.file.enums.FileType;
import info.yywang.file.service.FileService;
import info.yywang.file.service.FileUploadService;

import java.util.List;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2016-09-08 11:42
 */
public class DiskAttachFileServiceImpl implements FileService,FileUploadService {

    @Override
    public int put(String url, String bizType) {
        return 0;
    }

    @Override
    public String getUrl(int fileId) {
        return null;
    }

    @Override
    public String getUrl(int fileId, String fileStyle) {
        return null;
    }

    @Override
    public String getUrl(int fileId, int width, int height) {
        return null;
    }

    @Override
    public Map<Integer, String> getUrls(List<Integer> fileIds) {
        return null;
    }

    @Override
    public Map<Integer, String> getUrls(List<Integer> fileIds, String fileStyle) {
        return null;
    }

    @Override
    public Map<Integer, String> getUrls(List<Integer> fileIds, int width, int height) {
        return null;
    }

    @Override
    public int upload(String bizType, FileType fileType, String originalName, byte[] bytes) {
        return 0;
    }
}
