package com.nuesoft.lwn.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.SpCategory;
import com.nuesoft.lwn.dao.mapper.goods.SpCategoryMapper;
import com.nuesoft.lwn.service.goods.SpCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanyong on 2016/10/24.
 */

@Service
public class SpCategoryServiceImpl implements SpCategoryService{

    @Autowired
    SpCategoryMapper spCategoryMapper;

    public EUDataGridResult selectAll(Integer page, Integer rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<SpCategory> list = spCategoryMapper.selectAll();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<SpCategory> pageInfo = new PageInfo<SpCategory>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
