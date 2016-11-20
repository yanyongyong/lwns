package com.nuesoft.lwn.dao.mapper.member;


import com.nuesoft.lwn.common.entity.member.Mmg;
import org.springframework.stereotype.Repository;

@Repository
public interface MmgMapper {


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