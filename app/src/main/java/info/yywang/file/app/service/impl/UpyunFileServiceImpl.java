package info.yywang.file.app.service.impl;

import info.yywang.file.app.config.beans.UpyunConfigBean;
import info.yywang.file.dao.FileResourceDao;
import info.yywang.file.entity.FileResource;
import info.yywang.file.enums.CloudType;
import info.yywang.file.enums.FileType;
import info.yywang.file.service.FileService;
import info.yywang.file.service.FileUploadService;
import info.yywang.micro.common.exceptions.BizException;
import info.yywang.micro.common.time.DateUtils;
import info.yywang.micro.common.utils.KeyHolder;
import main.java.com.UpYun;
import org.apache.commons.io.IOUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 基于upyun的实现
 *
 * @author yanyan.wang
 * @date 2016-09-08 11:40
 */
public class UpyunFileServiceImpl implements FileService, FileUploadService {

    @Resource
    private FileResourceDao fileResourceDao;

    @Resource
    private UpyunConfigBean upyunConfigBean;

    private static final String SLASH = "/";

    private static final String HEAD = "http://";

    private static final String TAIL = ".b0.upaiyun.com";

    @Override
    public int put(String url, String bizType) {
        if (StringUtils.isEmpty(url)) {
            return 0;
        }
        FileResource fileResource = new FileResource();
        fileResource.setCloudType(CloudType.Upyun.getValue());
        fileResource.setPath(url);
        fileResource.setOriginName("");
        fileResource.setBranch(branch(url));
        fileResource.setFileSize(0);
        fileResource.setBizType(bizType);
        fileResource.setResType(FileType.getByUrl(url).getValue());
        return fileResourceDao.insert(fileResource);
    }

    @Override
    public String getUrl(int fileId) {
        if (fileId == 0) {
            return "";
        }
        FileResource fileResource = fileResourceDao.find(fileId);
        if (fileResource != null) {
            String path = fileResource.getPath();
            return url(fileResource.getBranch(), path);
        }
        return "";
    }

    @Override
    public String getUrl(int fileId, String fileStyle) {
        String url = getUrl(fileId);
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        return fileStyleUrl(url, fileStyle);
    }

    @Override
    public String getUrl(int fileId, int width, int height) {
        String url = getUrl(fileId);
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        return widthHeightUrl(url, width, height);
    }

    @Override
    public Map<Integer, String> getUrls(List<Integer> fileIds) {
        Map<Integer, String> fileMap = new HashMap<Integer, String>();
        if (CollectionUtils.isEmpty(fileIds)) {
            return fileMap;
        }
        List<FileResource> files = fileResourceDao.findFileResources(fileIds);
        for (FileResource file : files) {
            String path = file.getPath();
            fileMap.put(file.getId(), url(file.getBranch(), path));
        }
        return fileMap;
    }

    @Override
    public Map<Integer, String> getUrls(List<Integer> fileIds, String fileStyle) {
        Map<Integer, String> fileMap = getUrls(fileIds);
        Map<Integer, String> fileUrlsMap = new HashMap<>();
        Iterator iterator = fileMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer fileId = (Integer) iterator.next();
            fileUrlsMap.put(fileId, fileStyleUrl(fileMap.get(fileId), fileStyle));
        }
        return fileUrlsMap;
    }

    @Override
    public Map<Integer, String> getUrls(List<Integer> fileIds, int width, int height) {
        Map<Integer, String> fileMap = getUrls(fileIds);
        Map<Integer, String> fileUrlsMap = new HashMap<>();
        Iterator iterator = fileMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer fileId = (Integer) iterator.next();
            fileUrlsMap.put(fileId, widthHeightUrl(fileMap.get(fileId), width, height));
        }
        return fileUrlsMap;
    }

    @Override
    public int upload(String bizType, FileType fileType, String originalName, byte[] bytes) {
        UpYun upYun = new UpYun(upyunConfigBean.getBucketName(), upyunConfigBean.getOperator(), upyunConfigBean.getPassword());
        upYun.setApiDomain(UpYun.ED_AUTO);
        upYun.setTimeout(60);
        upYun.setDebug(false);
        FileResource file = new FileResource();
        String storeName = KeyHolder.getKey() + "." + originalName.substring(originalName.lastIndexOf(".") + 1);
        int fileSize = 0;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            fileSize = bytes.length;
            byte[] data = new byte[fileSize];
            bais.read(data);
            boolean result = upYun.writeFile(upyunConfigBean.getFilePath() + SLASH + getFilePath() + SLASH + storeName, data, true);
            if (!result) {
                throw new BizException("上传文件失败");
            }
            file.setPath(upyunConfigBean.getFilePath() + SLASH + getFilePath() + SLASH + storeName);
            file.setOriginName(originalName);
            file.setIsDeleted(false);
            file.setFileSize(fileSize);
            file.setBizType(bizType);
            file.setResType(fileType.getValue());
            file.setBranch(upyunConfigBean.getDefaultBranch());
            return fileResourceDao.insert(file);
        } catch (IOException e) {
            throw new BizException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(bais);
        }
    }

    private String getFilePath() {
        return DateUtils.format(DateUtils.now(), "yyyyMM");
    }

    private String fileStyleUrl(String url, String fileStyle) {
        return url + upyunConfigBean.getPart() + fileStyle;
    }

    private String widthHeightUrl(String url, int width, int height) {
        return url + upyunConfigBean.getPart() + width + "x" + height;
    }

    private String url(String branch, String path) {
        return HEAD + branch + TAIL + path;
    }

    private String branch(String url) {
        String[] paths = url.split("/");
        return paths[1];
    }
}
