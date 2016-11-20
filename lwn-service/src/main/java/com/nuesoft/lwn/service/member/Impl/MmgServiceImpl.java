package com.nuesoft.lwn.service.member.Impl;

import com.nuesoft.lwn.common.entity.member.Mmg;
import com.nuesoft.lwn.dao.mapper.member.MmgMapper;
import com.nuesoft.lwn.service.member.MmgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yanyong on 2016/11/9.
 */

@Service
public class MmgServiceImpl  implements MmgService{

    @Autowired
    MmgMapper mmgMapper;

    public int insert(Mmg mmg) {
        return mmgMapper.insert(mmg);
    }

    public int updateByMemberId(Mmg mmg) {
        return mmgMapper.updateByMemberId(mmg);
    }

}
