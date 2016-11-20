package com.nuesoft.lwn.service.goods;

import com.nuesoft.lwn.common.entity.goods.Mgu;

/**
 * Created by yanyong on 2016/10/26.
 */
public interface MguService {

    /**
     * 插入goods和unit对应的id好
     * @param mgu
     * @return
     */
    int insert(Mgu mgu);
}
