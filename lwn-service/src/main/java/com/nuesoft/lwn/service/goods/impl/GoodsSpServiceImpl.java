package com.nuesoft.lwn.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.GoodsSp;
import com.nuesoft.lwn.dao.mapper.goods.GoodsSpMapper;
import com.nuesoft.lwn.service.goods.GoodsSpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanyong on 2016/10/24.
 * 商品特价业务逻辑实现
 */

@Service
public class GoodsSpServiceImpl implements GoodsSpService{

    @Autowired
    GoodsSpMapper goodsSpMapper;

    public int deleteById(Integer spId) {
        return goodsSpMapper.deleteById(spId);
    }

    public int insert(GoodsSp goodsSp) {
        return goodsSpMapper.insert(goodsSp);
    }

    public EUDataGridResult selectAll(Integer page, Integer rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<GoodsSp> list = goodsSpMapper.selectAll();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<GoodsSp> pageInfo = new PageInfo<GoodsSp>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public EUDataGridResult selectById(Integer page, Integer rows, GoodsSp goodsSp) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<GoodsSp> list = goodsSpMapper.selectById(goodsSp);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<GoodsSp> pageInfo = new PageInfo<GoodsSp>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public int updateById(GoodsSp goodsSp) {
        return goodsSpMapper.updateById(goodsSp);
    }
}
