package com.nuesoft.lwn.dao.mapper.goods;


import com.nuesoft.lwn.common.entity.goods.GoodsSp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品特价DAO接口
 */
@Repository
public interface GoodsSpMapper {


    /**
     * 根据Id删除
     * @param spId
     * @return
     */
    int deleteById(Integer spId);

    /**
     * 增加特价商品
     * @param goodsSp
     * @return
     */
    int insert(GoodsSp goodsSp);

    /**
     * 查询全部
     * @return
     */
    List selectAll();

    /**
     * 根据Id查询
     * @param goodsSp
     * @return
     */
    List selectById(GoodsSp goodsSp);

    /**
     * 根据Id更新
     * @param goodsSp
     * @return
     */
    int updateById(GoodsSp goodsSp);

}