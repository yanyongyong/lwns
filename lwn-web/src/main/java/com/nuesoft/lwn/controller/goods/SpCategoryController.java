package com.nuesoft.lwn.controller.goods;

import com.nuesoft.lwn.common.entity.returnMsg.ReturnMsg;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.enums.SysErrorEnum;
import com.nuesoft.lwn.common.util.SessionUtil;
import com.nuesoft.lwn.service.goods.SpCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yanyong on 2016/10/20.
 *商品类型流程控制
 */

@Controller
@RequestMapping(value = "/spCategory")
public class SpCategoryController {
    @Autowired
    SpCategoryService spCategoryService;
    EUDataGridResult euDataGridResult;
    ReturnMsg returnMsg;

    /**
     * 分页查询商品特价类型信息
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectAll")
    public EUDataGridResult selectAll(Integer page, Integer rows){
        try {
            euDataGridResult = spCategoryService.selectAll(page,rows);
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("spResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }


}
