package com.nuesoft.lwn.controller.goods;

import com.nuesoft.lwn.common.entity.goods.Goods;
import com.nuesoft.lwn.common.entity.returnMsg.ReturnMsg;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.GoodsSp;
import com.nuesoft.lwn.common.enums.SysErrorEnum;
import com.nuesoft.lwn.common.enums.UserInputEnum;
import com.nuesoft.lwn.common.util.SessionUtil;
import com.nuesoft.lwn.service.goods.GoodsService;
import com.nuesoft.lwn.service.goods.GoodsSpService;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yanyong on 2016/10/17.
 * 商品特价管理
 */

@RequestMapping(value = "/goodsSp")
@Controller
public class GoodsSpController {

    @Autowired
    GoodsSpService goodsSpService;
    @Autowired
    GoodsService goodsService;
    ReturnMsg returnMsg;
    Goods goods;
    EUDataGridResult euDataGridResult;

    private int result;

    /**
     * 新增特价商品
     * @param goodsSp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert")
    public int insert(@ModelAttribute("goodsSp") GoodsSp goodsSp){
        try {
            Goods goods = goodsService.selectByName(goodsSp.getSpGoods());
            goodsSp.setSpName(Integer.toString(goods.getGoodsId()));
            goods.setRemark(goodsSp.getSpCategory());
            goodsService.updateBygoodId(goods);
            result =  goodsSpService.insert(goodsSp);
            returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
        }catch(Exception e) {
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("goodsSpResult", returnMsg.getReturnMsg());
        return result;
    }


    /**
     * 根据id删除特价商品
     * @param spId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteById")
    public int deleteById(Integer spId){
        try {
            result = goodsSpService.deleteById(spId);
            returnMsg = new ReturnMsg(UserInputEnum.DELECT_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("goodsSpResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 模糊查询
     * @param page
     * @param rows
     * @param goodsSp
     * @return
     */
    @RequestMapping(value = "/dimSelect")
    @ResponseBody
    public EUDataGridResult dimSelect(Integer page, Integer rows, @ModelAttribute("goodsSp") GoodsSp goodsSp) {
        try {
            if(goodsSp.getRemark() == null&&goodsSp.getSpCategory()== null&&
                    goodsSp.getSpGoods() == null&&goodsSp.getSpId()==null&&
                    goodsSp.getSpName()==null&&goodsSp.getSpPrice()==null&&
                    goodsSp.getSpTime()==null&&goodsSp.getSpStore()==null){
                euDataGridResult = goodsSpService.selectAll(page,rows);
            }else {
                euDataGridResult = goodsSpService.selectById(page, rows, goodsSp);
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("goodsSpResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 根据id更新特价商品
     * @param goodsSp
     * @return
     */
    @ResponseBody
    @ModelAttribute("goodsSp")
    @RequestMapping(value = "/updateById")
    public int updateById( GoodsSp goodsSp){
        try {
            result = goodsSpService.updateById(goodsSp);
            returnMsg = new ReturnMsg(UserInputEnum.UPDATE_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("goodsSpResult", returnMsg.getReturnMsg());
        return  result;
    }


}
