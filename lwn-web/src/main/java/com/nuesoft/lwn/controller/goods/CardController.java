package com.nuesoft.lwn.controller.goods;

import com.alibaba.fastjson.JSON;
import com.nuesoft.lwn.common.entity.goods.Unit;
import com.nuesoft.lwn.common.entity.member.Grade;
import com.nuesoft.lwn.common.entity.member.Member;
import com.nuesoft.lwn.common.entity.member.Mmg;
import com.nuesoft.lwn.common.entity.returnMsg.ReturnMsg;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.Card;
import com.nuesoft.lwn.common.enums.SysErrorEnum;
import com.nuesoft.lwn.common.enums.UserInputEnum;
import com.nuesoft.lwn.common.util.GetCurrentTimeUtil;
import com.nuesoft.lwn.common.util.SessionUtil;
import com.nuesoft.lwn.service.goods.CardService;
import com.nuesoft.lwn.service.goods.GoodsService;
import com.nuesoft.lwn.service.goods.UnitService;
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
 * Created by yanyong on 2016/10/20.
 * 购物车业务控制
 */

@Controller
@RequestMapping(value = "/card")
public class CardController {

    @Autowired
    CardService cardService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    UnitService unitService;
    @Autowired
    MemberService memberService;
    @Autowired
    MmgService mmgService;
    @Autowired
    GradeService gradeService;
    ReturnMsg returnMsg;
    Member member;
    EUDataGridResult euDataGridResult;

    private int result;
    public double  price;
    public int memberId;


    /**
     * 新增
     * @param arr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert")
    public int insert(String arr){
        try {
            List<Grade> grades = gradeService.selectIntegra();
            Mmg mmg = new Mmg();
            Unit unit = new Unit();
            Member member = new Member();
            List<Card> list = JSON.parseArray(arr,Card.class);
            int dom=(int)(Math.random()*1000);
            int i= 0;
            for (Card card:list){
                unit.setGoodsNumber(card.getGoodsId());
                unitService.updateBygoodsNumber(unit);
                card.setPayTime(GetCurrentTimeUtil.time());
                price += card.getPrice();
                if (i==0){
                    memberId = Integer.parseInt(card.getMenber());
                }
                if (card.getMop().equals("余额")){
                    double integral = memberService.searchName(Integer.parseInt(card.getMenber())).getMemberBalance();
                    member.setMemberBalance(integral-price);
                    member.setMemberId(Integer.parseInt(card.getMenber()));
                    memberService.updateMemberById(member);
                }
                card.setRemark2(Integer.toString(dom));
                cardService.insert(card);
                i++;
            }
            int integral = memberService.searchName(memberId).getMemberIntegral();
            int tatol = ((int)price+integral);
            member.setMemberId(memberId);
            if(tatol>=grades.get(4).getIntegralNadir()){
                mmg.setMemberId(memberId);
                mmg.setGradeId(5);
            }else if (tatol>=grades.get(3).getIntegralNadir()){
                mmg.setMemberId(memberId);
                mmg.setGradeId(4);
            }else if (tatol>=grades.get(2).getIntegralNadir()){
                mmg.setMemberId(memberId);
                mmg.setGradeId(3);
            }else if (tatol>=grades.get(1).getIntegralNadir()){
                mmg.setMemberId(memberId);
                mmg.setGradeId(2);
            }
            mmgService.updateByMemberId(mmg);
            member.setMemberIntegral(tatol);
            memberService.updateMemberById(member);
            result = i;
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("cardResult", returnMsg.getReturnMsg());
        return result;
    }


    /**
     * 根据id删除
     * @param card
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteById")
    public int deleteById(@ModelAttribute("card") Card card){
        try {
            double balance = memberService.searchName(Integer.parseInt(card.getMenber())).getMemberBalance();
            member.setMemberBalance(balance+Integer.parseInt(card.getRemark()));
            member.setMemberId(Integer.parseInt(card.getMenber()));
            memberService.updateMemberById(member);
            result = cardService.deleteById(card);
            returnMsg = new ReturnMsg(UserInputEnum.DELECT_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("cardResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 查询
     * @param page
     * @param rows
     * @param card
     * @return
     */
    @RequestMapping(value = "/dimSelect")
    @ResponseBody
    public EUDataGridResult dimSelect(Integer page, Integer rows, @ModelAttribute("card") Card card) {
        try {
            if (card.getPayTime()==null&&card.getMop()==null&&card.getMenber()==null){
                euDataGridResult = cardService.selectAll(page, rows);
            }
            if (card.getMenber()!= null){
                euDataGridResult = cardService.selectById(page, rows, card);
            }
            else {
                euDataGridResult = cardService.selectByTime(page, rows, card);
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("cardResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 根据会员名称查询
     * @param page
     * @param rows
     * @param card
     * @return
     */
    @RequestMapping(value = "/SelectById")
    @ResponseBody
    public EUDataGridResult SelectById(Integer page, Integer rows, @ModelAttribute("card") Card card) {
        try {
            euDataGridResult = cardService.selectById(page, rows, card);
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("cardResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 根据order查询
     * @param page
     * @param rows
     * @param card
     * @return
     */
    @RequestMapping(value = "/selectByOrder")
    @ResponseBody
    public EUDataGridResult selectByOrder(Integer page, Integer rows, @ModelAttribute("card") Card card) {
        try {
            euDataGridResult = cardService.selectByOrder(page, rows, card);
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("cardResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 根据id更新
     * @param card
     * @return
     */
    @ResponseBody
    @ModelAttribute("card")
    @RequestMapping(value = "/updateById")
    public int updateById( Card card){
        try {
            result = cardService.updateById(card);
            returnMsg = new ReturnMsg(UserInputEnum.UPDATE_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("cardResult", returnMsg.getReturnMsg());
        return  result;

    }
}
