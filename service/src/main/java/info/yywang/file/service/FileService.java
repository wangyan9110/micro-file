package info.yywang.file.service;

import java.util.List;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2015-12-15 00:54
 */
public interface FileService {

    /**
     * 适用于通过URL解析文件信息
     *
     * @param url
     * @return
     */
    int put(String url, String bizType);

    /**
     * 获取文件地址
     * <p>
     * 全路径如http://baidu.com/img.png
     * </p>
     *
     * @param fileId 文件id
     * @return 文件地址
     */
    String getUrl(int fileId);

    /**
     * 获取文件地址
     * <p>
     * 仅适用于图片
     * fileStyle可以在云厂商设置需要的缩略图尺寸，一般是宽度限定 高度自定义
     * 自定义的设置  由FileResource设定，可以在字典表中设定
     * 若fileStyle 不支持 则抛出UnSupportException;
     * </p>
     *
     * @param fileId    文件id
     * @param fileStyle 文件样式
     * @return 文件地址
     */
    String getUrl(int fileId, String fileStyle);

    /**
     * 获取文件地址
     * <p>
     * 仅适用于图片
     * fileStyle可以在云厂商设置需要的缩略图尺寸，一般是宽度限定 高度自定义
     * 自定义的设置  由FileResource设定，可以在字典表中设定
     * 若fileStyle 不支持 则抛出UnSupportException;
     * </p>
     *
     * @param fileId 文件id
     * @param height 文件高度
     * @param width  文件宽度
     * @return 文件地址
     */
    String getUrl(int fileId, int width, int height);

    /**
     * 批量获取文件地址
     * <p>
     * key为文件id,
     * value为文件地址
     * </p>
     *
     * @param fileIds 文件id
     * @return 文件地址
     */
    Map<Integer, String> getUrls(List<Integer> fileIds);

    /**
     * 批量获取文件地址
     * <p>
     * key为文件id
     * value为文件地址
     * 仅适用于图片
     * fileStyle可以在云厂商设置需要的缩略图尺寸，一般是宽度限定 高度自定义
     * 自定义的设置  由FileResource设定，可以在字典表中设定
     * 若fileStyle 不支持 则抛出UnSupportException;
     * </p>
     *
     * @param fileIds   文件id
     * @param fileStyle fileStyle
     * @return 文件地址
     */
    Map<Integer, String> getUrls(List<Integer> fileIds, String fileStyle);

    /**
     * 批量获取文件地址
     * <p>
     * key为文件id
     * value为文件地址
     * 仅适用于图片
     * fileStyle可以在云厂商设置需要的缩略图尺寸，一般是宽度限定 高度自定义
     * 自定义的设置  由FileResource设定，可以在字典表中设定
     * 若fileStyle 不支持 则抛出UnSupportException;
     * </p>
     *
     * @param fileIds 文件ids
     * @param height  文件高度
     * @param width   文件宽度
     * @return 文件地址
     */
    Map<Integer, String> getUrls(List<Integer> fileIds, int width, int height);
}
