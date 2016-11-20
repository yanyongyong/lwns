package com.nuesoft.lwn.service.member;

import com.nuesoft.lwn.common.entity.member.Grade;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;

import java.util.List;

/**
 * Created by yanyong on 2016/11/1.
 * 会员等级业务逻辑设计
 */
public interface GradeService {

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
     * 查询积分
     * @return
     */
    List selectIntegra();

    /**
     * 分页查询
     * @return
     */
    EUDataGridResult select(Integer page, Integer rows);

    /**
     * 根据id编辑
     * @param grade
     * @return
     */
    int updateById(Grade grade);




}
