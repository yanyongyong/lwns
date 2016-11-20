package com.nuesoft.lwn.controller.user;

import com.nuesoft.lwn.common.entity.returnMsg.ReturnMsg;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.Priv;
import com.nuesoft.lwn.common.entity.user.Role;
import com.nuesoft.lwn.common.enums.SysErrorEnum;
import com.nuesoft.lwn.common.enums.UserInputEnum;
import com.nuesoft.lwn.common.util.GetCurrentTimeUtil;
import com.nuesoft.lwn.common.util.SessionUtil;
import com.nuesoft.lwn.service.user.PrivService;
import com.nuesoft.lwn.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yanyong on 2016/10/17.
 * 权限业务流程控制
 */

@RequestMapping(value = "/priv")
@Controller
public class PrivController {

    @Autowired
    PrivService privService;
    ReturnMsg returnMsg;
    EUDataGridResult euDataGridResult;
    @Autowired
    RoleService roleService;

    private int result;

    /**
     * 新增权限
     * @param priv
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertPriv")
    public int insertpriv(@ModelAttribute("priv") Priv priv){
        try {
            result = privService.insertPriv(priv);
            returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
        }catch(Exception e) {
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("privResult", returnMsg.getReturnMsg());
        return result;
    }


    /**
     * 根据id删除权限
     * @param rolePrivId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteByPrivId",method = RequestMethod.GET)
    public int deleteByPrivId(Integer rolePrivId){
        try {
            result = privService.deleteByPrivId(rolePrivId);
            returnMsg = new ReturnMsg(UserInputEnum.DELECT_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("privResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 模糊查询
     * @param page
     * @param rows
     * @param priv
     * @return
     */
    @RequestMapping(value = "/dimSelectPriv")
    @ResponseBody
    public EUDataGridResult dimSelectPriv(Integer page, Integer rows, @ModelAttribute("priv") Priv priv) {
        try {
            if(priv.getUpdateBy() == null&&priv.getUpdateTime()== null&&
                    priv.getParentId() == null&&priv.getPrivName()==null&&
                    priv.getRolePrivId()==null&&priv.getRoleName()==null){
                euDataGridResult = privService.selectAllPriv(page,rows);
            }else {
                euDataGridResult = privService.dimSelectPriv(page, rows, priv);
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("privResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 根据id修改权限
     * @param priv
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editByprivId")
    public int editByprivId(@ModelAttribute("priv") Priv priv, @ModelAttribute("role")Role role){
        try {
            priv.setUpdateTime(GetCurrentTimeUtil.currentTime());
            priv.setUpdateBy("ziv");
            role.setRoleName(priv.getRoleName());
            role.setRolePriv(priv.getPrivName());
            roleService.UpdateByName(role);
            result = privService.editPrivByrolePrivId(priv);
            returnMsg = new ReturnMsg(UserInputEnum.UPDATE_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("privResult", returnMsg.getReturnMsg());
        return  result;
    }


}
