package com.nuesoft.lwn.controller.goods;

import com.nuesoft.lwn.common.entity.returnMsg.ReturnMsg;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.Goods;
import com.nuesoft.lwn.common.entity.goods.Mgu;
import com.nuesoft.lwn.common.entity.goods.Unit;
import com.nuesoft.lwn.common.enums.SysErrorEnum;
import com.nuesoft.lwn.common.enums.UserInputEnum;
import com.nuesoft.lwn.common.util.SessionUtil;
import com.nuesoft.lwn.service.goods.GoodsService;
import com.nuesoft.lwn.service.goods.MguService;
import com.nuesoft.lwn.service.goods.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by yanyong on 2016/10/21.
 * 商品业务管理控制
 */

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;
    @Autowired
    UnitService unitService;
    @Autowired
    MguService mguService;
    ReturnMsg returnMsg;
    EUDataGridResult euDataGridResult;
    List list;

    private int result;

    /**
     * 新增商品
     * @param goods
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert")
    public int insert(@ModelAttribute("goods") Goods goods,@ModelAttribute("mgu") Mgu mgu, @ModelAttribute("unit")Unit unit){
        try {
            goodsService.insert(goods);
            unit.setGoodsName(goods.getGoodsName());
            unit.setGoodsNumber(goodsService.selectMaxId());
            unitService.insert(unit);
            mgu.setGoodsId(goodsService.selectMaxId());
            mgu.setUnitId(unitService.selectMaxId());
            result = mguService.insert(mgu);
            returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
        }catch(Exception e) {
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("goodsResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 根据id删除商品
     * @param goodsId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteBygoodId")
    public int deleteBygoodId(Integer goodsId){
        try {
            result = goodsService.deleteBygoodId(goodsId);
            returnMsg = new ReturnMsg(UserInputEnum.DELECT_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("goodsResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 模糊查询
     * @param page
     * @param rows
     * @param goods
     * @return
     */
    @RequestMapping(value = "/select")
    @ResponseBody
    public EUDataGridResult select(Integer page, Integer rows, @ModelAttribute("goods") Goods goods) {
        try {
            if(goods.getRemark() == null&&goods.getCreateTime()== null&&
                    goods.getGoodsId() == null&&goods.getCreateTime()==null&&
                    goods.getGoodsName()==null&&goods.getGoodsType()==null&&
                    goods.getImage()==null&&goods.getNumber()==null&&
                    goods.getPrice()==null&&goods.getProducels()==null&&
                    goods.getProducer()==null){
                EUDataGridResult result = goodsService.select(page,rows);
                return result;
            }else {
                EUDataGridResult result = goodsService.selectById(page, rows,goods);
                return result;
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("goodsResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 查询
     * @return
     */
    @RequestMapping(value = "/selectAll")
    @ResponseBody
    public List selectAll() {
        try {
            list = goodsService.selectAll();
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("goodsResult", returnMsg.getReturnMsg());
        }
        return list;
    }

    /**
     * 根据id更新商品
     * @param goods
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateBygoodId")
    public int updateBygoodId(@ModelAttribute("goods") Goods goods){
        try {
            result = goodsService.updateBygoodId(goods);
            returnMsg = new ReturnMsg(UserInputEnum.UPDATE_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("goodsResult", returnMsg.getReturnMsg());
        return  result;
    }
}
