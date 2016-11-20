package com.nuesoft.lwn.controller.member;

import com.nuesoft.lwn.common.entity.goods.Unit;
import com.nuesoft.lwn.common.entity.member.Grade;
import com.nuesoft.lwn.common.entity.member.Member;
import com.nuesoft.lwn.common.entity.member.Mmg;
import com.nuesoft.lwn.common.entity.returnMsg.ReturnMsg;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.enums.SysErrorEnum;
import com.nuesoft.lwn.common.enums.UserInputEnum;
import com.nuesoft.lwn.common.util.SessionUtil;
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
 * Created by yanyong on 2016/11/1.
 * 会员等级业务流程控制
 */

@Controller
@RequestMapping(value = "/grade")
public class GradeController {

    @Autowired
    GradeService gradeService;
    @Autowired
    MemberService memberService;
    @Autowired
    MmgService mmgService;
    ReturnMsg returnMsg;
    EUDataGridResult euDataGridResult;
    Mmg mmg;

    private int result;

    /**
     * 添加等级
     * @param grade
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert")
    public int insertMember(@ModelAttribute("grade")Grade grade){
        try {
            result = gradeService.insert(grade);
            returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
        }catch(Exception e) {
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("gradeResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 模糊查询
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/dimSelect")
    @ResponseBody
    public EUDataGridResult dimSelect(Integer page, Integer rows) {
        try {
            euDataGridResult = gradeService.select(page,rows);
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("gradeResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 根据id更新
     * @param grade
     * @return
     */
    @RequestMapping(value = "/updateById")
    @ResponseBody
    public int updateById(@ModelAttribute("grade")Grade grade){
        try {
            result = gradeService.updateById(grade);
            List<Grade> listGrade = gradeService.selectIntegra();
            List<Member> listMember = memberService.searchMember();
            System.out.println("listMember.size() :"+listMember.size());
            for (int i=0;i<listMember.size();i++){
                if(listMember.get(i).getMemberIntegral()>=listGrade.get(4).getIntegralNadir()){
                    mmg.setMemberId(listMember.get(i).getMemberId());
                    mmg.setGradeId(5);
                }else if (listMember.get(i).getMemberIntegral()>=listGrade.get(3).getIntegralNadir()){
                    mmg.setMemberId(listMember.get(i).getMemberId());
                    mmg.setGradeId(4);
                }else if (listMember.get(i).getMemberIntegral()>=listGrade.get(2).getIntegralNadir()){
                    mmg.setMemberId(listMember.get(i).getMemberId());
                    mmg.setGradeId(3);
                }else if (listMember.get(i).getMemberIntegral()>=listGrade.get(1).getIntegralNadir()){
                    mmg.setMemberId(listMember.get(i).getMemberId());
                    mmg.setGradeId(2);
                }else if (listMember.get(i).getMemberIntegral()>=listGrade.get(0).getIntegralNadir()){
                    mmg.setMemberId(listMember.get(i).getMemberId());
                    mmg.setGradeId(1);
                }
                mmgService.updateByMemberId(mmg);
                returnMsg = new ReturnMsg(UserInputEnum.UPDATE_SUCCESS.getErrorMsg());
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("gradeResult", returnMsg.getReturnMsg());
        return  result;
    }


}
