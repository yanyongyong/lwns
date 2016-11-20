package com.nuesoft.lwn.dao.mapper.member;


import com.nuesoft.lwn.common.entity.member.Member;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 会员DAO接口
 */
@Repository
public interface MemberMapper {


    /*分页查询全部*/
    List selectAllMember();

    /**
     * 返回最大的ID
     * @return
     */
    int selectMaxId();

    /**
     * 根据id删除会员
     * @param memberId
     * @return
     */
    int deleteByMemberId(Integer memberId);

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
     * 根据会员名字查询
     * @param memberName
     * @return
     */
    List dimSelectMember(String memberName);

    /**
     * 会员查询
     * @return
     */
    List searchMember();

    /**
     * 在线会员查询
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