package info.yywang.file.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yanyan.wang
 * @date 2015-12-27 13:24
 */
@Data
public class FileResource implements Serializable{

    /**
     * the id
     */
    private int id;

    /**
     * 相对地址
     */
    private String path;

    /**
     * 原始文件
     */
    private String originName;

    /**
     * 文件大小
     */
    private int fileSize;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 资源类型
     */
    private int resType;

    /**
     * 云类型
     */
    private int cloudType;

    /**
     * 分支
     */
    private String branch;

    /**
     * 是否已删除
     */
    private Boolean isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
