package com.nuesoft.lwn.controller.member;

import com.nuesoft.lwn.common.entity.member.Member;
import com.nuesoft.lwn.common.entity.member.Mmg;
import com.nuesoft.lwn.common.entity.returnMsg.ReturnMsg;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.enums.SysErrorEnum;
import com.nuesoft.lwn.common.enums.UserInputEnum;
import com.nuesoft.lwn.common.util.GetCurrentTimeUtil;
import com.nuesoft.lwn.common.util.SessionUtil;
import com.nuesoft.lwn.common.util.VerifyUtil;
import com.nuesoft.lwn.service.member.GradeService;
import com.nuesoft.lwn.service.member.MemberService;
import com.nuesoft.lwn.service.member.MmgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by tracy on 2016/10/31.
 * 会员业务流程控制
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    MemberService memberService;
    @Autowired
    GradeService gradeService;
    @Autowired
    MmgService mmgService;
    ReturnMsg returnMsg;
    EUDataGridResult euDataGridResult;
    Mmg mmg;
    List list;

    private int result;

    /**
     * 分页查询全部会员
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectAllMember")
    public EUDataGridResult selectAllMember(Integer page, Integer rows, @ModelAttribute("memberName")String memberName){
        try {
            if (memberName == null){
                euDataGridResult = memberService.selectAllMember(page,rows);
            }else {
                euDataGridResult = memberService.dimSelectMember(page,rows,memberName);
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("memberResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 添加会员
     * @param member
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertMember")
    public int insertMember(@ModelAttribute("member")Member member){
        try {
            if (VerifyUtil.verifyUserName(member.getMemberName())){
                if (VerifyUtil.isMobile(member.getMemberPhone())){
                    member.setRemark("在线");
                    memberService.insertMember(member);
                    mmg.setMemberId(memberService.selectMaxId());
                    mmg.setGradeId(1);
                    result = mmgService.insert(mmg);
                    returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
                }else {
                    returnMsg = new ReturnMsg(UserInputEnum.USER_PHONE.getErrorMsg());
                }
            }else {
                returnMsg = new ReturnMsg(UserInputEnum.USERNAME_NOT_LEGAL.getErrorMsg());
            }
        }catch(Exception e) {
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("memberResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 根据id删除会员
     * @param memberId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteMemberById")
    public int deleteMemberById(@ModelAttribute("memberId")Integer memberId){
        try {
            result = memberService.deleteByMemberId(memberId);
            returnMsg = new ReturnMsg(UserInputEnum.DELECT_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("memberResult", returnMsg.getReturnMsg());
        return result;

    }

    /**
     * 根据id更新会员信息
     * @param member
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateMemberById")
    public int updateMemberById(@ModelAttribute("member")Member member){
        try {
            result = memberService.updateMemberById(member);
            returnMsg = new ReturnMsg(UserInputEnum.UPDATE_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("memberResult", returnMsg.getReturnMsg());
        return  result;
    }

    /**
     * 根据会员id充值
     * @param member
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/MemberRecharge")
    public int MemberRecharge(@ModelAttribute("member")Member member){
        try {
            member.setMemberBalance((member.getRecharge()+member.getMemberBalance()));
            result = memberService.updateMemberById(member);
            returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
        }catch(Exception e) {
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("memberResult", returnMsg.getReturnMsg());
        return result;

    }

    /**
     * 非分页会员查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchMember")
    public List searchMember(){
        try {
            list =  memberService.searchMember();
            returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
        }catch(Exception e) {
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("memberResult", returnMsg.getReturnMsg());
        return list;
    }

    /**
     * 在线会员查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchMemberOnLine")
    public List searchMemberOnLine(){
        try {
            list = memberService.searchMemberOnLine();
            returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
        }catch(Exception e) {
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("memberResult", returnMsg.getReturnMsg());
        return list;
    }
}
