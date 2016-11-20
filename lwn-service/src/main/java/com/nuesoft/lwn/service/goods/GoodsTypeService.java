package com.nuesoft.lwn.service.goods;

import com.nuesoft.lwn.common.entity.goods.GoodsTypeResult;

import java.util.List;

/**
 * Created by yanyong on 2016/10/20.
 * 商品类型流程逻辑设计
 */
public interface GoodsTypeService {

    /**
     * 根据id查询
     * @param parentId
     * @return
     */
    List<GoodsTypeResult> selectByType(Integer parentId);
}
