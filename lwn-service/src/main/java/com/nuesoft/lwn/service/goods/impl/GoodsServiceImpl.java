package com.nuesoft.lwn.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.Goods;
import com.nuesoft.lwn.dao.mapper.goods.GoodsMapper;
import com.nuesoft.lwn.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanyong on 2016/10/21.
 * 商品业务流程逻辑实现
 */

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 添加用户
     * @return
     */
    public int insert(Goods goods) {
        return goodsMapper.insert(goods);
    }

    /**
     * 分页查询
     * @return
     */
    public EUDataGridResult select(Integer page,Integer rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Goods> list = goodsMapper.select();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public List selectAll() {
        return goodsMapper.select();
    }

    public EUDataGridResult selectById(Integer page, Integer rows, Goods goods) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Goods> list = goodsMapper.selectById(goods);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Goods> pageInfo = new PageInfo<Goods>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public Goods selectByName(String goodsName) {
        return goodsMapper.selectByName(goodsName);
    }

    /**
     * 根据id更新商品
     * @param good
     * @return
     */
    public int updateBygoodId(Goods good) {
        return goodsMapper.updateBygoodId(good);
    }

    /**
     * 根据id删除商品
     * @param goodId
     * @return
     */
    public int deleteBygoodId(Integer goodId) {
        return goodsMapper.deleteBygoodId(goodId);
    }

    public int selectMaxId() {
        return goodsMapper.selectMaxId();
    }


}
