package com.nuesoft.lwn.controller.user;

import com.nuesoft.lwn.common.entity.returnMsg.ReturnMsg;
import com.nuesoft.lwn.common.entity.user.EUDataGridResult;
import com.nuesoft.lwn.common.entity.user.User;
import com.nuesoft.lwn.common.enums.SysErrorEnum;
import com.nuesoft.lwn.common.enums.UserInputEnum;
import com.nuesoft.lwn.common.util.GetCurrentTimeUtil;
import com.nuesoft.lwn.common.util.SessionUtil;
import com.nuesoft.lwn.common.util.VerifyUtil;
import com.nuesoft.lwn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 严勇 on 2016/10/7.
 * 店长业务流程的控制
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    ReturnMsg returnMsg;
    EUDataGridResult euDataGridResult;

    private int result;

    /**
     * 新增店长
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insert")
    public int insert(@ModelAttribute("user") User user){
        try {
            if(VerifyUtil.verifyUserName(user.getUserName())){
                if (VerifyUtil.verifyPassword(user.getPassword())){
                    user.setLastUpdate(GetCurrentTimeUtil.currentTime());
                    user.setUserRole("店长");
                    result = userService.insert(user);
                    returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
                }else {
                    returnMsg = new ReturnMsg(UserInputEnum.PASSWORD_ERROR.getErrorMsg());
                }
            }else {
                returnMsg = new ReturnMsg(UserInputEnum.USERNAME_NOT_LEGAL.getErrorMsg());
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());

        }
        SessionUtil.session().setAttribute("managerResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 新增操作员
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertOperator")
    public int insertOperator(@ModelAttribute("user") User user){
        try {
            if(VerifyUtil.verifyUserName(user.getUserName())){
                if (VerifyUtil.verifyPassword(user.getPassword())){
                    user.setLastUpdate(GetCurrentTimeUtil.currentTime());
                    user.setUserRole("操作员");
                    result = userService.insert(user);
                    returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
                }else {
                    returnMsg = new ReturnMsg(UserInputEnum.PASSWORD_ERROR.getErrorMsg());
                }
            }else {
                returnMsg = new ReturnMsg(UserInputEnum.USERNAME_NOT_LEGAL.getErrorMsg());
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());

        }
        SessionUtil.session().setAttribute("operatorResult", returnMsg.getReturnMsg());
        return result;

    }

    /**
     * 新增导购员
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertShoppingGuide")
    public int insertShoppingGuide(@ModelAttribute("user") User user){
        try {
            if(VerifyUtil.verifyUserName(user.getUserName())){
                if (VerifyUtil.verifyPassword(user.getPassword())){
                    user.setLastUpdate(GetCurrentTimeUtil.currentTime());
                    user.setUserRole("导购员");
                    result = userService.insert(user);
                    returnMsg = new ReturnMsg(UserInputEnum.REGISTER_SUCCESS.getErrorMsg());
                }else {
                    returnMsg = new ReturnMsg(UserInputEnum.PASSWORD_ERROR.getErrorMsg());
                }
            }else {
                returnMsg = new ReturnMsg(UserInputEnum.USERNAME_NOT_LEGAL.getErrorMsg());
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());

        }
        SessionUtil.session().setAttribute("shoppingGuideResult", returnMsg.getReturnMsg());
        return result;

    }

    /**
     * 检查用户是否存在
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userNameIsExist")
    public Boolean userNameIsExist(java.lang.String userName){
        if(userService.selectUser(userName)==null){
            return true;
        }
        return false;
    }


    /**
     * 登录检测
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView loginCheck(String userName,String password){
        ModelAndView modelAndView = new ModelAndView();
        if (userService.loginCheck(userName,password)){
            modelAndView.setViewName("index");
            modelAndView.addObject("userName",userName);
        }else {
            modelAndView.setViewName("login");
            modelAndView.addObject("userName","用户名或者密码错误");
        }
            return modelAndView;
    }


    /**
     * 修改密码
     * @param user
     */
    @RequestMapping(value = "/updatePasswordByUserName",method = RequestMethod.GET)
    public int updatePasswordByUserName(@ModelAttribute("user") User user){
        try {
            user.setUserName("ziv");
            result = userService.updatePasswordByUserName(user);
            returnMsg = new ReturnMsg(UserInputEnum.UPDATE_PASSWORD_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("shoppingGuideResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 根据id删除用户
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteByUserId",method = RequestMethod.GET)
    public int deleteByUserId(Integer userId){
        try {
            result = userService.deleteByUserId(userId);
            returnMsg = new ReturnMsg(UserInputEnum.DELECT_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("userResult", returnMsg.getReturnMsg());
        return result;
    }

    /**
     * 根据id更新用户
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePasswordByUserId")
    public int updatePasswordByUserId(@ModelAttribute("user") User user){
        try {
            result = userService.updatePasswordByUserId(user);
            returnMsg = new ReturnMsg(UserInputEnum.UPDATE_SUCCESS.getErrorMsg());
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
        }
        SessionUtil.session().setAttribute("UserResult", returnMsg.getReturnMsg());
        return  result;

    }

    /**
     * 店长模糊查询
     * @param page
     * @param rows
     * @param user
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public EUDataGridResult dimSelect(Integer page, Integer rows, @ModelAttribute("user") User user) {
        try {
            if(user.getUserName() == null&&user.getUserId()== null&&
                    user.getPassword() == null&&user.getLastUpdate()==null&&
                    user.getRemark()==null){
                euDataGridResult = userService.selectAllUser(page,rows);
            }else {
                user.setUserRole("店长");
                euDataGridResult = userService.dimSelect(page,rows,user);
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("StoreResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 模糊查询
     * @param page
     * @param rows
     * @param user
     * @return
     */
    @RequestMapping(value = "/dimSelectAll")
    @ResponseBody
    public EUDataGridResult dimSelectAll(Integer page, Integer rows, @ModelAttribute("user") User user) {
        try {
            if(user.getUserName() == null&&user.getUserId()== null&&
                    user.getPassword() == null&&user.getLastUpdate()==null&&
                    user.getRemark()==null){
                euDataGridResult = userService.selectAll(page,rows);
            }else {
                euDataGridResult= userService.dimSelectAll(page,rows,user);
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("StoreResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 操作员模糊查询
     * @param page
     * @param rows
     * @param user
     * @return
     */
    @RequestMapping(value = "/dimselectOperator")
    @ResponseBody
    public EUDataGridResult dimselectOperator(Integer page, Integer rows, @ModelAttribute("user") User user) {
        try {
            if(user.getUserName() == null&&user.getUserId()== null&&
                    user.getPassword() == null&&user.getLastUpdate()==null&&
                    user.getRemark()==null){
                euDataGridResult = userService.selectAllOperator(page,rows);
            }else {
                user.setUserRole("操作员");
                euDataGridResult = userService.dimSelect(page,rows,user);
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("operatorResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }

    /**
     * 导购员模糊查询
     * @param page
     * @param rows
     * @param user
     * @return
     */
    @RequestMapping(value = "/dimselectGuide")
    @ResponseBody
    public EUDataGridResult dimselectGuide(Integer page, Integer rows, @ModelAttribute("user") User user) {
        try {
            if(user.getUserName() == null&&user.getUserId()== null&&
                    user.getPassword() == null&&user.getLastUpdate()==null&&
                    user.getRemark()==null){
                euDataGridResult = userService.selectAllGuide(page,rows);
            }else {
                user.setUserRole("导购员");
                euDataGridResult = userService.dimSelect(page,rows,user);
            }
        }catch (Exception e){
            returnMsg = new ReturnMsg(SysErrorEnum.SYS_EXCEPTION.getErrorMsg());
            SessionUtil.session().setAttribute("StoreResult", returnMsg.getReturnMsg());
        }
        return euDataGridResult;
    }


}
