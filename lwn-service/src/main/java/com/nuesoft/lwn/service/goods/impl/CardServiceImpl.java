package com.nuesoft.lwn.service.goods.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.Card;
import com.nuesoft.lwn.dao.mapper.goods.CardMapper;
import com.nuesoft.lwn.service.goods.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanyong on 2016/10/26.
 * 购物车业务流程逻辑实现
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardMapper cardMapper;

    public EUDataGridResult selectAll(Integer page, Integer rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Card> list = cardMapper.selectAll();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Card> pageInfo = new PageInfo<Card>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public int deleteById(Card card) {
        return cardMapper.deleteById(card);
    }

    public int insert(Card card) {
        return cardMapper.insert(card);
    }

    public EUDataGridResult selectById(Integer page, Integer rows, Card card) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Card> list = cardMapper.selectById(card);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Card> pageInfo = new PageInfo<Card>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public EUDataGridResult selectByOrder(Integer page, Integer rows, Card card) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Card> list = cardMapper.selectByOrder(card);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Card> pageInfo = new PageInfo<Card>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public EUDataGridResult selectByTime(Integer page, Integer rows, Card card) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Card> list = cardMapper.selectByTime(card);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Card> pageInfo = new PageInfo<Card>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public int updateById(Card card) {
        return cardMapper.updateById(card);
    }
}
