package com.nuesoft.lwn.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.User;
import com.nuesoft.lwn.dao.mapper.user.UserMapper;
import com.nuesoft.lwn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 严勇 on 2016/10/6.
 * 用户业务流程逻辑实现
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     *新增用户
     * @param user
     */
    public int insert(User user) {
       return userMapper.insert(user);
   }

    /**
     *登录检测
     * @param userName
     * @param password
     * @return
     */
    public boolean loginCheck(String userName, String password) {
        User user = userMapper.selectUser(userName);
        if (user != null){
            if (password.equals(user.getPassword())){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     *根据用户名修改密码
     * @param user
     */
    public int updatePasswordByUserName(User user) {
        return userMapper.updatePasswordByUserName(user);
    }

    /**
     * 根据id编辑用户
     * @param user
     */
    public int updatePasswordByUserId(User user) {
        return userMapper.updatePasswordByUserId(user);
    }

    /**
     * 店长分页查询
     * @param page
     * @param rows
     * @return
     */
    public EUDataGridResult selectAllUser(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<User> list = userMapper.selectAllUser();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    public EUDataGridResult selectAll(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<User> list = userMapper.selectAll();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 操作员分页查询
     * @param page
     * @param rows
     * @return
     */
    public EUDataGridResult selectAllOperator(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<User> list = userMapper.selectAllOperator();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 导购分页查询
     * @param page
     * @param rows
     * @return
     */
    public EUDataGridResult selectAllGuide(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<User> list = userMapper.selectAllGuide();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 根据id删除用户
     * @param userId
     */
    public int deleteByUserId(Integer userId) {
        return userMapper.deleteByUserId(userId);
    }



    /**
     * 模糊查询
     * @param page
     * @param rows
     * @return
     */
    public EUDataGridResult dimSelect(int page, int rows,User user) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<User> list = userMapper.dimSelect(user);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 模糊查询
     * @param page
     * @param rows
     * @return
     */
    public EUDataGridResult dimSelectAll(int page, int rows,User user) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<User> list = userMapper.dimSelectAll(user);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    public User selectUser(String userName) {
        return userMapper.selectUser(userName);
    }


}
