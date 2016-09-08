package info.yywang.file.app.config.beans;

import lombok.Data;

/**
 * @author yanyan.wang
 * @date 2016-09-08 12:04
 */
@Data
public class UpyunConfigBean {

    private String bucketName;

    private String operator;

    private String password;

    private String filePath;

    private String url;

    private String prefix;

    private String suffix;

    private String part;

    private String defaultBranch;

}
