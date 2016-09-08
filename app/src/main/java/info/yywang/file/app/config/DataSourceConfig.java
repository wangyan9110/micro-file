package info.yywang.file.app.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanyan.wang
 * @date 2016-07-06 22:09
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.druid.main.username}")
    private String username;

    @Value("${spring.druid.main.password}")
    private String password;

    @Value("${spring.druid.main.url}")
    private String url;

    @Value("${spring.druid.main.type}")
    private String driverClass;

    @Value("${spring.druid.main.initialSize}")
    private int initialSize;

    @Value("${spring.druid.main.maxActive}")
    private int maxActive;

    @Value("${spring.druid.main.minIdle}")
    private int minIdle;

    @Value("${spring.druid.main.maxWait}")
    private long maxWait;

    @Value("${spring.druid.main.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${spring.druid.main.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;

    @Value("${spring.druid.main.validationQuery}")
    private String validationQuery;

    @Value("${spring.druid.main.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.druid.main.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.druid.main.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.druid.main.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.druid.main.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    private Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Primary
    @Bean(name = "mainDataSource")
    public DataSource mainDataSource() {
        DruidDataSource mainDataSource = new DruidDataSource();
        mainDataSource.setUrl(url);
        mainDataSource.setUsername(username);
        mainDataSource.setPassword(password);
        mainDataSource.setDriverClassName(driverClass);
        mainDataSource.setInitialSize(initialSize);
        mainDataSource.setMaxActive(maxActive);
        mainDataSource.setMinIdle(minIdle);
        mainDataSource.setMaxWait(maxWait);
        mainDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        mainDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        mainDataSource.setValidationQuery(validationQuery);
        mainDataSource.setTestWhileIdle(testWhileIdle);
        mainDataSource.setTestOnReturn(testOnReturn);
        mainDataSource.setTestOnBorrow(testOnBorrow);
        mainDataSource.setPoolPreparedStatements(poolPreparedStatements);
        mainDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

        try {
            mainDataSource.setFilters("com.iydsj.sw.smart.cat.dao.CatLoggerFilter,stat");
            Slf4jLogFilter loggFilter = new Slf4jLogFilter();
            loggFilter.setStatementExecutableSqlLogEnable(false);
            List<Filter> filters = new ArrayList<>();
            filters.add(loggFilter);
            mainDataSource.setProxyFilters(filters);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return mainDataSource;
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(DataSource mainDataSource) {
        return new DataSourceTransactionManager(mainDataSource);
    }
}
