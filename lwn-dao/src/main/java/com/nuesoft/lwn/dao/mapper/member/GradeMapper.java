package com.nuesoft.lwn.dao.mapper.member;

import com.nuesoft.lwn.common.entity.member.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 会员等级DAO接口
 */
@Repository
public interface GradeMapper {

    /**
     * 根据id编辑
     * @param grade
     * @return
     */
    int updateById(Grade grade);

    /**
     * 添加
     * @param grade
     * @return
     */
    int insert(Grade grade);

    /**
     * 返回最大的ID
     * @return
     */
    int selectMaxId();

    /**
     * 分页查询
     * @return
     */
    List select();

}