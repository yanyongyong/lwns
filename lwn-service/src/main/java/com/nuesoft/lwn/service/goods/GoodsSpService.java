package com.nuesoft.lwn.service.goods;

import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.GoodsSp;

/**
 * Created by yanyong on 2016/10/24.
 * 商品特价逻辑设计
 */
public interface GoodsSpService {

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
    EUDataGridResult selectAll(Integer page,Integer rows);

    /**
     * 根据Id查询
     * @param goodsSp
     * @return
     */
    EUDataGridResult selectById(Integer page,Integer rows, GoodsSp goodsSp);

    /**
     * 根据Id更新
     * @param goodsSp
     * @return
     */
    int updateById(GoodsSp goodsSp);
}
