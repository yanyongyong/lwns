package com.nuesoft.lwn.service.goods;

import com.nuesoft.lwn.common.entity.user.EUDataGridResult;

/**
 * Created by yanyong on 2016/10/24.
 * 商品特价类型逻辑设计
 */
public interface SpCategoryService {

   /**
    * 分页查询商品特价类型信息
    * @param page
    * @param rows
    * @return
    */
   EUDataGridResult selectAll(Integer page, Integer rows);
}
