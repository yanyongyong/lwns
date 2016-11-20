package com.nuesoft.lwn.dao.mapper.goods;


import com.nuesoft.lwn.common.entity.goods.Card;
import com.nuesoft.lwn.common.entity.goods.Card;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车DAO接口
 */
@Repository
public interface CardMapper {
    /**
     * 查询全部
     * @return
     */
    List selectAll();

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
    List selectById(Card card);

    /**
     * 根据order查询
     * @param card
     * @return
     */
    List selectByOrder(Card card);

    /**
     * 根据时间查询
     * @param card
     * @return
     */
    List selectByTime(Card card);

    /**
     * 根据Id更新
     * @param card
     * @return
     */
    int updateById(Card card);
}