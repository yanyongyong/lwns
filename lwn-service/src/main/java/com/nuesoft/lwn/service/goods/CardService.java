package com.nuesoft.lwn.service.goods;

import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.Card;

/**
 * Created by yanyong on 2016/10/26.
 * 购物车业务逻辑设计
 */
public interface CardService {

    /**
     * 查询全部
     * @return
     */
    EUDataGridResult selectAll(Integer page, Integer rows);
    /**
     * 根据Id删除
     * @param card
     * @return
     */
    int deleteById(Card card);

    /**
     * 增加
     * @param card
     * @return
     */
    int insert(Card card);

    /**
     * 根据Id查询
     * @param card
     * @return
     */
    EUDataGridResult selectById(Integer page, Integer rows, Card card);

    /**
     * 根据order查询
     * @param card
     * @return
     */
    EUDataGridResult selectByOrder(Integer page, Integer rows, Card card);

    /**
     * 根据时间查询
     * @param card
     * @return
     */
    EUDataGridResult selectByTime(Integer page, Integer rows, Card card);

    /**
     * 根据Id更新
     * @param card
     * @return
     */
    int updateById(Card card);

}
