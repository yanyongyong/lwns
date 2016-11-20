package com.nuesoft.lwn.dao.mapper.user;


import com.nuesoft.lwn.common.entity.user.User;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 用户DAO接口
 */

@Repository
public interface UserMapper {

    /**
     * 新增用户
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User selectUser(String userName);

    /**
     * 根据用户名修改密码
     * @param user
     * @return
     */
    int updatePasswordByUserName(User user);

    /**
     * 店长分页查询
     * @return
     */
    List selectAllUser();

    /**
     * 分页查询
     * @return
     */
    List selectAll();

    /**
     * 操作员分页查询
     * @return
     */
    List selectAllOperator();

    /**
     * 导购分页查询
     * @return
     */
    List selectAllGuide();

    /**
     * 根据id编辑用户
     * @param user
     */
    int updatePasswordByUserId(User user);

    /**
     * 根据id删除用户
     * @param userId
     * @return
     */
    int deleteByUserId(Integer userId);

    /**
     * 模糊查询
     * @param user
     * @return
     */
    List dimSelect(User user);

    /**
     * 模糊查询全部
     * @param user
     * @return
     */
    List dimSelectAll(User user);


}