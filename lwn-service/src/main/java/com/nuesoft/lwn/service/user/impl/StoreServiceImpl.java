package com.nuesoft.lwn.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.Store;
import com.nuesoft.lwn.dao.mapper.user.StoreMapper;
import com.nuesoft.lwn.service.user.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yanyong on 2016/10/18.
 * 店面业务流程逻辑实现
 */

@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    StoreMapper storeMapper;

    /**
     * 增加店面
     * @param store
     * @return
     */
    public int insertStore(Store store) {
        return storeMapper.insertStore(store);
    }

    /**
     * 查询全部
     * @param page
     * @param rows
     * @return
     */
    public EUDataGridResult selectAllStore(int page, int rows) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Store> list =storeMapper.selectAllStore();
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Store> pageInfo = new PageInfo<Store>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 模糊查询
     * @param page
     * @param rows
     * @param  store
     * @return
     */
    public EUDataGridResult dimSelectStore(int page, int rows, Store store) {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Store> list = storeMapper.dimSelectStore(store);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Store> pageInfo = new PageInfo<Store>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 根据id编辑店面
     * @param store
     */
    public int updateStoreId(Store store) {
        return storeMapper.updateStoreId(store);
    }

    /**
     *根据id删除店面
     * @param storeId
     */
    public int deleteByStoreId(Integer storeId) {
        return storeMapper.deleteByStoreId(storeId);
    }
}
