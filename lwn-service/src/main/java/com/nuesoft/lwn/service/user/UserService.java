package com.nuesoft.lwn.service.user;


import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.User;

/**
 * Created by 严勇 on 2016/10/6.
 * 用户逻辑应用设计
 */

public interface UserService {

    /**
     * 新增用户
     * @param user
     */
    int insert(User user);

    /**
     *登录检测
     * @param userName
     * @param password
     * @return
     */
    boolean loginCheck(String userName, String password);

    /**
     * 根据用户名修改密码
     * @param user
     * @return
     */
    int updatePasswordByUserName(User user);

    /**
     * 根据id编辑用户
     * @param user
     */
    int updatePasswordByUserId(User user);

    /**
     * 店长分页查询
     * @param page,要显示第几页
     * @param rows，每页显示的行数
     * @return
     */
    EUDataGridResult selectAllUser(int page, int rows);

    /**
     * 分页查询
     * @param page,要显示第几页
     * @param rows，每页显示的行数
     * @return
     */
    EUDataGridResult selectAll(int page, int rows);

    /**
     * 操作员分页查询
     * @param page,要显示第几页
     * @param rows，每页显示的行数
     * @return
     */
    EUDataGridResult selectAllOperator(int page, int rows);

    /**
     * 导购分页查询
     * @param page,要显示第几页
     * @param rows，每页显示的行数
     * @return
     */
    EUDataGridResult selectAllGuide(int page, int rows);

    /**
     * 根据id删除用户
     * @param userId
     * @return
     */
    int deleteByUserId(Integer userId);

    /**
     * 模糊查询
     * @param user
     */
    EUDataGridResult dimSelect(int page, int rows,User user);

    /**
     * 模糊查询全部
     * @param user
     */
    EUDataGridResult dimSelectAll(int page, int rows,User user);

    /**
     * 根据用户名查用户信息
     * @param userName
     * @return
     */
    User selectUser(String userName);
}
