package com.nuesoft.lwn.dao.mapper.goods;


import com.nuesoft.lwn.common.entity.goods.GoodsSp;
import com.nuesoft.lwn.common.entity.goods.Unit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品库存、单位DAO接口
 */
@Repository
public interface UnitMapper {


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
     * 增加库存
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

    /**
     * 根据商品id更新
     * @param unit
     * @return
     */
    int updateBygoodsNumber(Unit unit);

    /**
     * 查询id最大值
     * @return
     */
    int selectMaxId();
}