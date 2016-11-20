package com.nuesoft.lwn.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.Role;
import com.nuesoft.lwn.dao.mapper.user.RoleMapper;
import com.nuesoft.lwn.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanyong on 2016/10/17.
 * 角色业务流程逻辑实现
 */

@Service
public class RoleServiceImpl implements RoleService{


    @Autowired
    RoleMapper roleMapper;

    public int UpdateByName(Role role) {
        return roleMapper.UpdateByName(role);
    }

    /**
     * 角色新增
     * @param role
     * @return
     */
    public int insertRole(Role role) {
        return roleMapper.insertRole(role);
    }

    /**
     * 根据id删除角色
     * @param roleId
     */
    public int deleteByRoleId(Integer roleId) {
        return roleMapper.deleteByRoleId(roleId);
    }

    /**
     * 模糊查询
     * @param page
     * @param rows
     * @param role
     * @return
     */
    public EUDataGridResult dimSelectRole(int page, int rows, Role role) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Role> list = roleMapper.dimSelectRole(role);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 分页查询
     * @param page,要显示第几页
     * @param rows，每页显示的行数
     * @return
     */
    public EUDataGridResult selectAllRole(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Role> list = roleMapper.selectAllRole();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 根据id编辑角色
     * @param role
     */
    public int editByRoleId(Role role) {
        return roleMapper.editByRoleId(role);
    }
}
