package com.nuesoft.lwn.dao.mapper.goods;


import com.nuesoft.lwn.common.entity.goods.SpCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品特价类型DAO设计
 */
@Repository
public interface SpCategoryMapper {


    /**
     * 查询全部
     * @return
     */
    List selectAll();

}