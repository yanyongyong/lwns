package com.nuesoft.lwn.service.goods;

import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.Unit;

/**
 * Created by yanyong on 2016/10/24.
 */
public interface InventoryService {

    /**
     * 查询全部
     * @return
     */
    EUDataGridResult selectAll(Integer page, Integer rows);

    /**
     * 根据Id删除
     * @param spId
     * @return
     */
    int deleteById(Integer spId);

    /**
     * 增加特价商品
     * @param unit
     * @return
     */
    int insert(Unit unit);


    /**
     * 根据Id查询
     * @param unit
     * @return
     */
    EUDataGridResult selectById(Integer page, Integer rows, Unit unit);

    /**
     * 根据Id更新
     * @param unit
     * @return
     */
    int updateById(Unit unit);


}
