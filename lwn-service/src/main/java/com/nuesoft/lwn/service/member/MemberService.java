package com.nuesoft.lwn.service.member;


import com.nuesoft.lwn.common.entity.goods.GoodsTypeResult;
import com.nuesoft.lwn.common.entity.member.Member;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;

import java.util.List;


/**
 * Created by tracy on 2016/10/31.
 * 会员逻辑应用设计
 */
public interface MemberService {

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    EUDataGridResult selectAllMember(int page, int rows);

    /**
     * 根据id删除会员
     * @param memberId
     * @return
     */
    int deleteByMemberId(Integer memberId);

    /**
     * 返回最大的ID
     * @return
     */
    int selectMaxId();

    /**
     * 增加会员
     * @param member
     * @return
     */
    int insertMember(Member member);

    /**
     * 根据id更新会员信息
     * @param member
     * @return
     */
    int updateMemberById(Member member);

    /**
     * 用条件的分页查询
     * @param page
     * @param rows
     * @param memberName
     * @return
     */
    EUDataGridResult dimSelectMember(int page, int rows, String memberName);

    /**
     * 查询全部会员
     * @return
     */
    List searchMember();

    /**
     * 查询在线的会员
     * @return
     */
    List searchMemberOnLine();

    /**
     * 根据会员id查找会员
     * @param memberId
     * @return
     */
    Member searchName(Integer memberId);
}
