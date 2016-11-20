package com.nuesoft.lwn.service.member.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.lwn.common.entity.goods.Card;
import com.nuesoft.lwn.common.entity.member.Grade;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.dao.mapper.member.GradeMapper;
import com.nuesoft.lwn.service.member.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanyong on 2016/11/1.
 * 会员等级业务逻辑实现
 */

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    GradeMapper gradeMapper;

    public int insert(Grade grade) {
        return gradeMapper.insert(grade);
    }

    public int selectMaxId() {
        return gradeMapper.selectMaxId();
    }

    public List selectIntegra() {
        return gradeMapper.select();
    }

    public EUDataGridResult select(Integer page, Integer rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Grade> list = gradeMapper.select();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Grade> pageInfo = new PageInfo<Grade>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public int updateById(Grade grade) {
        return gradeMapper.updateById(grade);
    }


}
