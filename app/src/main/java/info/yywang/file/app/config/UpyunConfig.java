package info.yywang.file.app.config;

import info.yywang.file.app.config.beans.UpyunConfigBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanyan.wang
 * @date 2016-09-08 11:50
 */
@Configuration
public class UpyunConfig {

    @Value("${micro.file.upyun.bucketName}")
    private String bucketName;

    @Value("${micro.file.upyun.operator}")
    private String operator;

    @Value("${micro.file.upyun.password}")
    private String password;

    @Value("${micro.file.upyun.filePath}")
    private String filePath;

    @Value("${micro.file.upyun.url}")
    private String url;

    @Value("${micro.file.upyun.prefix}")
    private String prefix;

    @Value("${micro.file.upyun.operator}")
    private String suffix;

    @Value("${micro.file.upyun.part}")
    private String part;

    @Value("${micro.file.upyun.defaultBranch}")
    private String defaultBranch;

    @Bean(name = "upyunConfigBean")
    public UpyunConfigBean upyunConfigBean() {
        UpyunConfigBean upyunConfigBean = new UpyunConfigBean();
        upyunConfigBean.setSuffix(suffix);
        upyunConfigBean.setBucketName(bucketName);
        upyunConfigBean.setDefaultBranch(defaultBranch);
        upyunConfigBean.setPart(part);
        upyunConfigBean.setOperator(operator);
        upyunConfigBean.setFilePath(filePath);
        upyunConfigBean.setUrl(url);
        upyunConfigBean.setPassword(password);
        upyunConfigBean.setPrefix(prefix);
        return upyunConfigBean;
    }
}
