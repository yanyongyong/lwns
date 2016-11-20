package com.nuesoft.lwn.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.Unit;
import com.nuesoft.lwn.dao.mapper.goods.UnitMapper;
import com.nuesoft.lwn.service.goods.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanyong on 2016/10/24.
 * 商品库存、单位流程逻辑实现
 */

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    UnitMapper unitMapper;

    /**
     * 分页查询
     * @return
     */
    public EUDataGridResult selectAll(Integer page, Integer rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Unit> list = unitMapper.selectAll();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Unit> pageInfo = new PageInfo<Unit>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public int deleteById(Integer spId) {
        return unitMapper.deleteById(spId);
    }

    public int insert(Unit unit) {
        return unitMapper.insert(unit);
    }


    public EUDataGridResult selectById(Integer page, Integer rows, Unit unit) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Unit> list = unitMapper.selectById(unit);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Unit> pageInfo = new PageInfo<Unit>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public int updateById(Unit unit) {
        return unitMapper.updateById(unit);
    }

    public int updateBygoodsNumber(Unit unit) {
        return unitMapper.updateBygoodsNumber(unit);
    }

    public int selectMaxId() {
        return unitMapper.selectMaxId();
    }
}
