package com.nuesoft.lwn.controller.user;

import com.nuesoft.lwn.common.entity.returnMsg.ReturnMsg;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.Store;
import com.nuesoft.lwn.common.enums.SysErrorEnum;
import com.nuesoft.lwn.common.enums.UserInputEnum;
import com.nuesoft.lwn.common.util.SessionUtil;
import com.nuesoft.lwn.common.util.VerifyUtil;
import com.nuesoft.lwn.service.user.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yanyong on 2016/10/17.
 * 门店业务流程的控制
 */

@RequestMapping(value = "/store")
@Controller
public class StoreController
{

    @Autowired
    StoreService storeService;
    ReturnMsg returnMsg;
    EUDataGridResult euDataGridResult;

    private int result;

    /**
     * 新增店面
     *
     * @param store
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertStore")
    public int insertStore(@ModelAttribute("store") Store store) {
        try {
            //验证店名是否合法
            if(VerifyUtil.verifyUserName(store.getStoreName())){
                result = storeService.insertStore(store);
                returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
            }else {
                returnMsg = new ReturnMsg(UserInputEnum.USERNAME_NOT_LEGAL.getErrorMsg());
            }
        } catch(Exception e) {
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            //e.printStackTrace();
        }
        SessionUtil.session().setAttribute("StoreResult", returnMsg.getReturnMsg());
        return result;
    }


    /**
     * 根据id删除店面
     *
     * @param storeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteByStoreId", method = RequestMethod.GET)
    public int deleteByStoreId(Integer storeId) {
        try {
            result = storeService.deleteByStoreId(storeId);
            returnMsg = new ReturnMsg(UserInputEnum.DELECT_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("StoreResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 模糊查询
     *
     * @param page
     * @param rows
     * @param store
     * @return
     */
    @RequestMapping(value = "/dimSelectStore")
    @ResponseBody
    public EUDataGridResult dimSelectStore(Integer page, Integer rows, @ModelAttribute("store") Store store) {

        try {
            if(store.getRemark() == null && store.getShopManager() == null && store.getState() == null && store.getStoreAddress() == null && store.getStoreId() == null && store.getStoreName() == null) {
                euDataGridResult = storeService.selectAllStore(page, rows);
            }
            else {
                euDataGridResult = storeService.dimSelectStore(page, rows, store);
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("StoreResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 根据id更新店面
     *
     * @param store
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateStoreId")
    public int updateStoreId(@ModelAttribute("store")Store store) {
        try {
            result = storeService.updateStoreId(store);
            returnMsg = new ReturnMsg(UserInputEnum.UPDATE_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("StoreResult", returnMsg.getReturnMsg());
        return  result;
    }


}
