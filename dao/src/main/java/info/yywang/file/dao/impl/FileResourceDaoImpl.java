package info.yywang.file.dao.impl;

import info.yywang.file.dao.FileResourceDao;
import info.yywang.file.dao.base.AbstractBaseDao;
import info.yywang.file.entity.FileResource;
import info.yywang.micro.common.sql.ParamsMap;
import info.yywang.micro.common.sql.SqlBuilder;
import info.yywang.micro.common.time.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2015-12-27 13:37
 */
@Repository("fileResourceDao")
public class FileResourceDaoImpl extends AbstractBaseDao implements FileResourceDao {

    private String TABLE_NAME = "base_fileresource";

    @Override
    public int insert(FileResource fileResource) {
        SqlBuilder sqlBuilder = SqlBuilder.insert(TABLE_NAME
                , "Path,OriginalName,FileSize,BizType,ResType,CloudType,Branch,IsDeleted,CreateTime"
                , ":path,:originalName,:fileSize,:bizType,:resType,:cloudType,:branch,:isDeleted,:createTime");
        ParamsMap paramsMap = ParamsMap.create("path", fileResource.getPath())
                .put("originalName", fileResource.getOriginName())
                .put("fileSize", fileResource.getFileSize())
                .put("bizType", fileResource.getBizType())
                .put("resType", fileResource.getResType())
                .put("cloudType", fileResource.getCloudType())
                .put("branch", fileResource.getBranch())
                .put("isDeleted", false)
                .put("createTime", DateUtils.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sqlBuilder.toString(), new MapSqlParameterSource(paramsMap.getParams()), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public FileResource find(int id) {
        SqlBuilder sqlBuilder = fileResourceSqlBuilder().where("Id=:id").deleted(false);
        ParamsMap paramsMap = ParamsMap.create("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject(sqlBuilder.toString(), paramsMap.getParams(), new FileResourceMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int update(FileResource fileResource) {
        SqlBuilder sqlBuilder = SqlBuilder.update(TABLE_NAME);
        List<String> items = new ArrayList<String>();
        Map<String, Object> params = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(fileResource.getPath())) {
            items.add("Path=:path");
            params.put("path", fileResource.getPath());
        }
        if (StringUtils.isNotBlank(fileResource.getOriginName())) {
            items.add("OriginalName=:originalName");
            params.put("originalName", fileResource.getOriginName());
        }
        if (fileResource.getFileSize() != 0) {
            items.add("FileSize=:fileSize");
            params.put("fileSize", fileResource.getFileSize());
        }
        if (StringUtils.isNotBlank(fileResource.getBizType())) {
            items.add("BizType=:bizType");
            params.put("bizType", fileResource.getBizType());
        }
        if (fileResource.getResType() != 0) {
            items.add("ResType=:resType");
            params.put("resType", fileResource.getResType());
        }
        if (fileResource.getCloudType() != 0) {
            items.add("CloudType=:cloudType");
            params.put("cloudType", fileResource.getCloudType());
        }
        if (StringUtils.isNotBlank(fileResource.getBranch())) {
            items.add("Branch=:branch");
            params.put("branch", fileResource.getBranch());
        }
        sqlBuilder.set(items).where("Id=:id").deleted(false);
        params.put("id", fileResource.getId());
        return namedParameterJdbcTemplate.update(sqlBuilder.toString(), params);
    }

    @Override
    public List<FileResource> findFileResources(List<Integer> ids) {
        SqlBuilder sqlBuilder = fileResourceSqlBuilder().where().in("Id", ids).deleted(false);
        return namedParameterJdbcTemplate.query(sqlBuilder.toString(), new FileResourceMapper());
    }

    @Override
    public List<FileResource> findAll() {
        SqlBuilder sqlBuilder = fileResourceSqlBuilder().where().deleted(false);
        return namedParameterJdbcTemplate.query(sqlBuilder.toString(), new FileResourceMapper());
    }

    private class FileResourceMapper implements RowMapper<FileResource> {
        @Override
        public FileResource mapRow(ResultSet rs, int rowNum) throws SQLException {
            FileResource fileResource = new FileResource();
            fileResource.setId(rs.getInt("Id"));
            fileResource.setPath(rs.getString("Path"));
            fileResource.setBizType(rs.getString("BizType"));
            fileResource.setOriginName(rs.getString("OriginalName"));
            fileResource.setResType(rs.getInt("ResType"));
            fileResource.setCloudType(rs.getInt("CloudType"));
            fileResource.setBranch(rs.getString("Branch"));
            fileResource.setFileSize(rs.getInt("FileSize"));
            return fileResource;
        }
    }

    private SqlBuilder fileResourceSqlBuilder() {
        SqlBuilder sqlBuilder = SqlBuilder.select(TABLE_NAME, "Id,Path,OriginalName,FileSize,BizType,ResType,CloudType,Branch");
        return sqlBuilder;
    }
}
