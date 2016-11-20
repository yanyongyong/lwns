package com.nuesoft.lwn.dao.mapper.user;


import com.nuesoft.lwn.common.entity.user.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色DAO接口
 */
@Repository
public interface RoleMapper {

    /**
     * 角色新增
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
     * @return
     */
    List dimSelectRole(Role role);

    /**
     * 分页查询
     * @return
     */
    List selectAllRole();

    /**
     * 根据姓名查询
     * @param role
     * @return
     */
    int UpdateByName(Role role);

    /**
     * 根据id修改角色
     * @param role
     */
    int editByRoleId(Role role);
}