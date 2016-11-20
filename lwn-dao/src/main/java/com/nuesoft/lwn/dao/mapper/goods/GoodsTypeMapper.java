package com.nuesoft.lwn.dao.mapper.goods;


import com.nuesoft.lwn.common.entity.goods.GoodsType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsTypeMapper {

    /**
     * 根据id删除
     * @param typeId
     * @return
     */
    int deleteByTypeId(Integer typeId);

    /**
     * 查询全部
     * @return
     */
    List selectByType(Integer typeId);

    /**
     * 根据id更新类别
     * @param goodsType
     * @return
     */
    int updateByTypeId(GoodsType goodsType);


}