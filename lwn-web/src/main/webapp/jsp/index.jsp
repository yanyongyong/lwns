<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <title>罗维尼POS平台首页</title>
    <link href="/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="/js/themes/icon.css" />
    <script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="/js/jQuery.easyui.js"></script>

    <script type="text/javascript" src='/js/outlook2.js'> </script>

    <script type="text/javascript">
        var _menus = {"menus":[
            {"menuid":"1","icon":"icon-sys","menuname":"系统管理",
                "menus":[{"menuname":"店长管理","icon":"icon-nav","url":"/jsp/user/managerManaeg.jsp"},
                    {"menuname":"角色管理","icon":"icon-add","url":"/jsp/user/roleMessage.jsp"},
                    {"menuname":"权限管理","icon":"icon-users","url":"/jsp/user/privManaeg.jsp"},
                    {"menuname":"操作员管理","icon":"icon-role","url":"/jsp/user/operatorManaeg.jsp"},
                    {"menuname":"导购员管理","icon":"icon-set","url":"/jsp/user/shoppingGuideManaeg.jsp"},
                    {"menuname":"门店管理","icon":"icon-set","url":"/jsp/user/StoreManaeg.jsp"},
                    {"menuname":"商品管理","icon":"icon-set","url":"/jsp/goods/goodsManaeg.jsp"},
                    {"menuname":"节假日管理","icon":"icon-set","url":"/jsp/demo.jsp"},
                    {"menuname":"退货条件设置","icon":"icon-set","url":"/jsp/demo.jsp"},
                    {"menuname":"密码重置","icon":"icon-set","url":"/jsp/demo.jsp"},
                    {"menuname":"班次管理","icon":"icon-log","url":"/jsp/demo.jsp"}
                ]
            },{"menuid":"8","icon":"icon-sys","menuname":"商品销售",
                "menus":[{"menuname":"前台收银","icon":"icon-nav","url":"/jsp/goods/cashier.jsp"},
                    {"menuname":"退货","icon":"icon-nav","url":"/jsp/goods/salesReturn.jsp"},
                    {"menuname":"盘点","icon":"icon-nav","url":"/jsp/goods/check.jsp"}
                ]
            },{"menuid":"56","icon":"icon-sys","menuname":"促销管理",
                "menus":[{"menuname":"捆绑促销","icon":"icon-nav","url":"/jsp/demo.jsp"},
                    {"menuname":"单品促销","icon":"icon-nav","url":"/jsp/demo.jsp"},
                    {"menuname":"整单促销","icon":"icon-nav","url":"/jsp/demo.jsp"}
                ]
            },{"menuid":"28","icon":"icon-sys","menuname":"会员管理",
                "menus":[{"menuname":"会员等级设置","icon":"icon-nav","url":"/jsp/member/memberGrade.jsp"},
                    {"menuname":"会员折扣设置","icon":"icon-nav","url":"/jsp/member/memberDiscount.jsp"},
                    {"menuname":"会员消费查询","icon":"icon-nav","url":"/jsp/member/memberCheck.jsp"},
                    {"menuname":"会员信息管理","icon":"icon-nav","url":"/jsp/member/memberManage.jsp"},
                    {"menuname":"会员充值","icon":"icon-nav","url":"/jsp/member/memberRecharge.jsp"},
                    {"menuname":"会员积分查询","icon":"icon-nav","url":"/jsp/member/memberIntegral.jsp"},
                    {"menuname":"积分兑换管理","icon":"icon-nav","url":"/jsp/demo.jsp"},
                    {"menuname":"会员状态管理","icon":"icon-nav","url":"/jsp/member/memberState.jsp"},
                ]
            }
        ]};
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function close() {
            $('#w').window('close');
        }

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }
            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            else{
                msgShow('系统提示', '恭喜，密码修改成功</br>您的新密码为：'+ $newpass.val());
                $.ajax({
                    type: "get",//数据发送的方式（post 或者 get）
                    url: "/user/updatePasswordByUserName",//要发送的后台地址
                    data: {password:$newpass.val()},//要发送的数据（参数）格式为{'val1':"1","val2":"2"}
                    dataType: "json",//后台处理后返回的数据格式
                    success: function (data) {//ajax请求成功后触发的方法
                        //alert('请求成功');
                    },
                    error: function (msg) {//ajax请求失败后触发的方法
                        //alert(msg);//弹出错误信息
                    }
                });
                close();
            }
            /*$.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
             msgShow('系统提示', '恭喜，密码修改成功您的新密码为：' + msg, 'info');
             $newpass.val('');
             $rePass.val('');
             close();
             })*/
        }

        $(function() {
            openPwd();
            //
            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            });

            $('#btncolse').click(function() {
                $('#w').window('close');
            })

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                    if (r) {
                        location.href = '/ajax/loginout.ashx';
                    }
                });
            })
        });


    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
    <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
        <img src="/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
    </div></noscript>
<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
    <span style="float:right; padding-right:20px;" class="head">超级管理员 <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
    <span style="padding-left:10px; font-size: 16px; "><img src="/images/blocks.gif" width="20" height="20" align="absmiddle" /></span>
    <span style="float: right; margin-right: 20px">用户：${requestScope.userName}</span>
</div>
<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
    <div class="footer"></div>
</div>
<div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
    <div class="easyui-accordion" fit="true" border="false">
        <!--  导航内容 -->

    </div>

</div>
<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
    <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
        <div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">

            <h1>Welcome to 罗维尼POS管理平台</h1>

        </div>
    </div>
</div>


<!--修改密码窗口-->
<div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
     maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <table cellpadding=3>
                <tr>
                    <td>新密码：</td>
                    <td><input id="txtNewPass" type="Password" class="txt01" /></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input id="txtRePass" type="Password" class="txt01" /></td>
                </tr>
            </table>
        </div>
        <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
            <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a>
            <a id="btncolse" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
        </div>
    </div>
</div>

<div id="mm" class="easyui-menu" style="width:150px;">
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseall">全部关闭</div>
    <div id="mm-tabcloseother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">当前页右侧全部关闭</div>
    <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-exit">退出</div>
</div>


</body>
</html>
