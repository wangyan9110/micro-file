package info.yywang.file.dao.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author yanyan.wang
 * @date 2015-12-14 23:48
 */
public abstract class AbstractBaseDao {

    /**
     * the jdbcTemplate
     */
    @Autowired(required = false)
    @Qualifier("jdbcTemplate")
    protected JdbcTemplate jdbcTemplate;

    /**
     * namedParameterJdbcTemplate 带name参数的jdbcTemplate
     */
    @Autowired(required = false)
    @Qualifier("namedParameterJdbcTemplate")
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

}