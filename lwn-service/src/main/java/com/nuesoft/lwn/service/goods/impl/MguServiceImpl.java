package com.nuesoft.lwn.service.goods.impl;

import com.nuesoft.lwn.common.entity.goods.Mgu;
import com.nuesoft.lwn.dao.mapper.goods.MguMapper;
import com.nuesoft.lwn.service.goods.MguService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanyong on 2016/10/26.
 */

@Service
public class MguServiceImpl implements MguService{

    @Autowired
    MguMapper mguMapper;

    public int insert(Mgu mgu) {
        return mguMapper.insert(mgu);
    }
}
