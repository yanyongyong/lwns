package com.nuesoft.lwn.dao.mapper.user;

import com.nuesoft.lwn.common.entity.user.Store;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by yanyong on 2016/10/18.
 * 店面DAO接口
 */

@Repository
public interface StoreMapper {


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
    List selectAllStore();

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

    /**
     * 模糊查询
     * @param store
     * @return
     */
    List dimSelectStore(Store store);

}