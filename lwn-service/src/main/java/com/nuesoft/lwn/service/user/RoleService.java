package com.nuesoft.lwn.service.user;

import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.Role;

/**
 * Created by yanyong on 2016/10/17.
 * 角色业务逻辑设计
 */
public interface RoleService {


    /**
     * 根据姓名更新
     * @param role
     * @return
     */
    int UpdateByName(Role role);

    /**
     * 新增角色
     * @param role
     * @return
     */
    int insertRole(Role role);

    /**
     * 根据id删除角色
     * @param roleId
     * @return
     */
    int deleteByRoleId(Integer roleId);

    /**
     * 模糊查询
     * @param role
     */
    EUDataGridResult dimSelectRole(int page, int rows, Role role);

    /**
     * 分页查询
     * @param page,要显示第几页
     * @param rows，每页显示的行数
     * @return
     */
    EUDataGridResult selectAllRole(int page, int rows);

    /**
     * 根据id编辑角色
     * @param role
     */
    int editByRoleId(Role role);
}
