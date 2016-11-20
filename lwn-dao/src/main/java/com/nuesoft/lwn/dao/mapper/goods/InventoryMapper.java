package com.nuesoft.lwn.dao.mapper.goods;


import com.nuesoft.lwn.common.entity.goods.Unit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryMapper {

    /**
     * 查询全部
     * @return
     */
    List selectAll();

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
    List selectById(Unit unit);

    /**
     * 根据Id更新
     * @param unit
     * @return
     */
    int updateById(Unit unit);

}