package com.nuesoft.lwn.service.user;

import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.Priv;

/**
 * Created by yanyong on 2016/10/17.
 * 权限业务流程逻辑设计
 */

public interface PrivService {

    /**
     * 权限新增
     * @param priv
     * @return
     */
    int insertPriv(Priv priv);

    /**
     * 根据id删除权限
     * @param rolePrivId
     * @return
     */
    int deleteByPrivId(Integer rolePrivId);

    /**
     * 模糊查询
     * @param priv
     */
    EUDataGridResult dimSelectPriv(int page, int rows, Priv priv);

    /**
     * 分页查询
     * @param page,要显示第几页
     * @param rows，每页显示的行数
     * @return
     */
    EUDataGridResult selectAllPriv(int page, int rows);

    /**
     * 根据id编辑权限
     * @param priv
     */
    int editPrivByrolePrivId(Priv priv);
}
