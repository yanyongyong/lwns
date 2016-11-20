package com.nuesoft.lwn.service.goods;

import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.Unit;

/**
 * Created by yanyong on 2016/10/24.
 * 商品库存、单位流程逻辑设计
 */
public interface UnitService {

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
    EUDataGridResult selectById(Integer page,Integer rows, Unit unit);

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
