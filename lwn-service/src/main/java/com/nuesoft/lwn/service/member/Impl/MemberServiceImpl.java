package com.nuesoft.lwn.service.member.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.lwn.common.entity.goods.GoodsTypeResult;
import com.nuesoft.lwn.common.entity.member.Member;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.dao.mapper.member.MemberMapper;
import com.nuesoft.lwn.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by tracy on 2016/10/31.
 * 会员业务逻辑实现
 */
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;

    public EUDataGridResult selectAllMember(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Member> list = memberMapper.selectAllMember();
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        PageInfo<Member> pageInfo = new PageInfo<Member>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public int deleteByMemberId(Integer memberId) {
        return memberMapper.deleteByMemberId(memberId);
    }

    public int selectMaxId() {
        return memberMapper.selectMaxId();
    }

    public int insertMember(Member member) {
        return memberMapper.insertMember(member);
    }

    public int updateMemberById(Member member) {
        return memberMapper.updateMemberById(member);
    }

    public EUDataGridResult dimSelectMember(int page, int rows, String memberName) {
        PageHelper.startPage(page,rows);
        List<Member> list = memberMapper.dimSelectMember(memberName);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        PageInfo<Member> pageInfo = new PageInfo<Member>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    public List searchMember() {
        return memberMapper.searchMember();
    }

    public List searchMemberOnLine() {
        return memberMapper.searchMemberOnLine();
    }

    public Member searchName(Integer memberId) {
        return memberMapper.searchName(memberId);
    }
}
