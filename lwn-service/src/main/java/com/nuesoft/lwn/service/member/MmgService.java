package com.nuesoft.lwn.service.member;

import com.nuesoft.lwn.common.entity.member.Mmg;

/**
 * Created by yanyong on 2016/11/9.
 */
public interface MmgService {


    /**
     * 插入会员和等级一一对应
     * @param mmg
     * @return
     */
    int insert(Mmg mmg);

    /**
     * 根据会员id更新
     * @param mmg
     * @return
     */
    int updateByMemberId(Mmg mmg);


}
