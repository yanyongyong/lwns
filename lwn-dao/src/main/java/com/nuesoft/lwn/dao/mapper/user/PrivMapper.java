package com.nuesoft.lwn.dao.mapper.user;


import com.nuesoft.lwn.common.entity.user.Priv;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限DAO接口
 */
@Repository
public interface PrivMapper {

    /**
     * 权限新增
     * @param priv
     * @return
     */
    int insertPriv(Priv priv);

    /**
     * 根据id删除角色
     * @param rolePrivId
     * @return
     */
    int deleteByPrivId(Integer rolePrivId);

    /**
     * 模糊查询
     * @param priv
     * @return
     */
    List dimSelectPriv(Priv priv);

    /**
     * 分页查询
     * @return
     */
    List selectAllPriv();

    /**
     * 根据id修改角色
     * @param priv
     */
    int editPrivByrolePrivId(Priv priv);
}