package com.nuesoft.lwn.controller.user;

import com.nuesoft.lwn.common.entity.returnMsg.ReturnMsg;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.Role;
import com.nuesoft.lwn.common.enums.SysErrorEnum;
import com.nuesoft.lwn.common.enums.UserInputEnum;
import com.nuesoft.lwn.common.util.GetCurrentTimeUtil;
import com.nuesoft.lwn.common.util.SessionUtil;
import com.nuesoft.lwn.common.util.VerifyUtil;
import com.nuesoft.lwn.service.user.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yanyong on 2016/10/17.
 * 角色业务流程控制
 */

@RequestMapping(value = "/role")
@Controller
public class RoleController {

    @Autowired
    RoleService roleService;
    ReturnMsg returnMsg;
    EUDataGridResult euDataGridResult;

    private int result;

    /**
     * 新增角色
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertRole")
    public int insertRole(@ModelAttribute("Role") Role role){
        try {
            if (VerifyUtil.verifyUserName(role.getRoleName())){
                role.setUpdateTime(GetCurrentTimeUtil.currentTime());
                role.setCreateTime(GetCurrentTimeUtil.currentTime());
                role.setUpdateBy("ziv");
                result = roleService.insertRole(role);
                returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
            }else {
                returnMsg = new ReturnMsg(UserInputEnum.USERNAME_NOT_LEGAL.getErrorMsg());
            }
        }catch(Exception e) {
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("RoleResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 根据id删除角色
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteByRoleId",method = RequestMethod.GET)
    public int deleteByRoleId(Integer roleId){
        try {
            result = roleService.deleteByRoleId(roleId);
            returnMsg = new ReturnMsg(UserInputEnum.DELECT_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("RoleResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 模糊查询
     * @param page
     * @param rows
     * @param role
     * @return
     */
    @RequestMapping(value = "/dimSelectRole")
    @ResponseBody
    public EUDataGridResult dimSelectRole(Integer page, Integer rows, @ModelAttribute("role") Role role) {
        try {
            if(role.getCreateBy() == null&&role.getCreateTime()== null&&
                    role.getRoleId() == null&&role.getRoleName()==null&&
                    role.getUpdateBy()==null&&role.getUpdateTime()==null&&
                    role.getRolePriv()==null){
                euDataGridResult = roleService.selectAllRole(page,rows);
            }else {
                euDataGridResult = roleService.dimSelectRole(page, rows, role);
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("RoleResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 根据id更新角色信息
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editByRoleId")
    public int editByRoleId(@ModelAttribute("role") Role role){
        try {
            role.setUpdateTime(GetCurrentTimeUtil.currentTime());
            result = roleService.editByRoleId(role);
            returnMsg = new ReturnMsg(UserInputEnum.UPDATE_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("RoleResult", returnMsg.getReturnMsg());
        return  result;

    }


}
