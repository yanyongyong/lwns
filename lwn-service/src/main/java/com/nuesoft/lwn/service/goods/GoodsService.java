package com.nuesoft.lwn.service.goods;

import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.Goods;

import java.util.List;

/**
 * Created by yanyong on 2016/10/21.
 * 商品业务管理逻辑设计
 */
public interface GoodsService {

    /**
     * 添加用户
     * @return
     */
    int insert(Goods goods);

    /**
     * 分页查询
     * @return
     */
    EUDataGridResult select(Integer page,Integer rows);

    List selectAll();


    EUDataGridResult selectById(Integer page,Integer rows, Goods goods);


    /**
     * 根据名称查询
     * @param goodsName
     * @return
     */
    Goods selectByName(String goodsName);


    /**
     * 根据id更新商品
     * @param goodId
     * @return
     */
    int updateBygoodId(Goods goodId);

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
