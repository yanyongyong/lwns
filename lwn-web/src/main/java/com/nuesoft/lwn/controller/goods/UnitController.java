package com.nuesoft.lwn.controller.goods;

import com.nuesoft.lwn.common.entity.returnMsg.ReturnMsg;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.goods.Unit;
import com.nuesoft.lwn.common.enums.SysErrorEnum;
import com.nuesoft.lwn.common.enums.UserInputEnum;
import com.nuesoft.lwn.common.util.SessionUtil;
import com.nuesoft.lwn.service.goods.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yanyong on 2016/10/20.
 * 商品库存、单位业务流程控制
 */

@Controller
@RequestMapping(value = "/unit")
public class UnitController {
    @Autowired
    UnitService unitService;
    ReturnMsg returnMsg;
    EUDataGridResult euDataGridResult;

    private int result;

    /**
     * 新增
     * @param unit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert")
    public int insert(@ModelAttribute("unit") Unit unit){
        try {
            result = unitService.insert(unit);
            returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
        }catch(Exception e) {
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("unitResult", returnMsg.getReturnMsg());
        return result;

    }

    /**
     * 根据id删除
     * @param unitId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteById")
    public int deleteById(Integer unitId){
        try {
            result = unitService.deleteById(unitId);
            returnMsg = new ReturnMsg(UserInputEnum.DELECT_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("unitResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 模糊查询
     * @param page
     * @param rows
     * @param unit
     * @return
     */
    @RequestMapping(value = "/dimSelect")
    @ResponseBody
    public EUDataGridResult dimSelect(Integer page, Integer rows, @ModelAttribute("unit") Unit unit) {
        try {
            if(unit.getRemark() == null&&unit.getGoodsName()== null&&
                    unit.getUnitName() == null&&unit.getUnitId()==null&&
                    unit.getInvertory()==null&&unit.getGoodsNumber()==null&&
                    unit.getStore()==null){
                euDataGridResult = unitService.selectAll(page,rows);
            }else {
                euDataGridResult = unitService.selectById(page, rows, unit);
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("unitResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 根据id更新
     * @param unit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateById")
    public int updateById(@ModelAttribute("unit") Unit unit){
        try {
            result = unitService.updateById(unit);
            returnMsg = new ReturnMsg(UserInputEnum.UPDATE_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("unitResult", returnMsg.getReturnMsg());
        return  result;
    }

    /**
     * 根据商品id更新
     * @param unit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateBygoodsNumber")
    public int updateBygoodsNumber(@ModelAttribute("unit") Unit unit){
        try {
            result = unitService.updateBygoodsNumber(unit);
            returnMsg = new ReturnMsg(UserInputEnum.UPDATE_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("unitResult", returnMsg.getReturnMsg());
        return  result;
    }


}
