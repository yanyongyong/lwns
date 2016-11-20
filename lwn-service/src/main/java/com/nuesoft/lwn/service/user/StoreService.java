package com.nuesoft.lwn.service.user;

import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.Store;

/**
 * Created by yanyong on 2016/10/18.
 *店面逻辑应用设计
 */
public interface StoreService {

    /**
     * 新增店面
     * @param store
     * @return
     */
    int insertStore(Store store);


    /**
     * 店面分页查询
     * @return
     */
    EUDataGridResult selectAllStore(int page, int rows);

    /**
     * 模糊查询
     * @param store
     */
    EUDataGridResult dimSelectStore(int page, int rows, Store store);


    /**
     * 根据id编辑店面
     * @param store
     */
    int updateStoreId(Store store);

    /**
     * 根据id删除店面
     * @param storeId
     * @return
     */
    int deleteByStoreId(Integer storeId);


}
