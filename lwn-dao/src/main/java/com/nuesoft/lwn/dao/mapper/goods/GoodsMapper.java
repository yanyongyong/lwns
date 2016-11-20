package com.nuesoft.lwn.dao.mapper.goods;


import com.nuesoft.lwn.common.entity.goods.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品DAO接口
 */
@Repository
public interface GoodsMapper {

    /**
     * 添加用户
     * @return
     */
    int insert(Goods goods);

    /**
     * 分页查询
     * @return
     */
    List select();

    /**
     * 根据名称查询
     * @param goodsName
     * @return
     */
    Goods selectByName(String goodsName);

    /**
     * 根据id查询
     * @param goods
     * @return
     */
    List selectById(Goods goods);

    /**
     * 根据id更新商品
     * @param good
     * @return
     */
    int updateBygoodId(Goods good);

    /**
     * 根据id删除商品
     * @param goodId
     * @return
     */
    int deleteBygoodId(Integer goodId);

    /**
     * 查询id最大值
     * @return
     */
    int selectMaxId();

}