package info.yywang.file.dao;


import info.yywang.file.entity.FileResource;

import java.util.List;

/**
 * @author yanyan.wang
 * @date 2015-12-27 13:33
 */
public interface FileResourceDao {

    /**
     * 插入文件资源
     *
     * @param fileResource 文件资源
     * @return 文件资源id
     */
    int insert(FileResource fileResource);

    /**
     * 查询某个文件资源
     *
     * @param id the id
     * @return 文件资源
     */
    FileResource find(int id);

    /**
     * 查询某些文件资源
     *
     * @param ids the ids
     * @return 文件资源
     */
    List<FileResource> findFileResources(List<Integer> ids);

    /**
     * 查找所有的文件信息
     * @return
     */
    List<FileResource> findAll();

    /**
     * 更新文件资源
     * @param fileResource
     * @return
     */
    int update(FileResource fileResource);
}
