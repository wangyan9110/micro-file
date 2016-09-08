package info.yywang.file.app.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

/**
 * @author yanyan.wang
 * @date 2016-08-17 10:36
 */
@Configuration
@Import({DataSourceConfig.class})
@AutoConfigureAfter({DataSourceConfig.class})
public class JdbcTemplateConfig {

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(DataSource mainDataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(mainDataSource);
        return jdbcTemplate;
    }

    @Bean(name = "namedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource mainDataSource) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(mainDataSource);
        return namedParameterJdbcTemplate;
    }
}
