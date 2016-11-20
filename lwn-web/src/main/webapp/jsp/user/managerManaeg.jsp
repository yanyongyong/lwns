<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>店长信息</title>
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/demo/demo.css">
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>

    <style type="text/css">
        #fm {
            margin: 0;
            padding: 10px 30px;
        }

        #search {
            margin: 0;
            padding: 10px 30px;
        }

        .ftitle {
            font-size: 14px;
            font-weight: bold;
            color: #666;
            padding: 5px 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #ccc;
        }

        .fitem {
            margin-bottom: 5px;
        }

        .fitem label {
            display: inline-block;
            width: 80px;
        }
    </style>

</head>
<body>

<table id="dg" title="店长信息" style="width:100%;height: 520px;" class="easyui-datagrid"
       data-options="rownumbers:true,singleSelect:true,pagination:true,url:'/user/list',method:'post'">
    <thead>
    <tr>
        <th data-options="field:'userId',width:150">编号</th>
        <th data-options="field:'userName',width:150">姓名</th>
        <th data-options="field:'password',width:150">密码</th>
        <th data-options="field:'phoneNum',width:160">电话号码</th>
        <th data-options="field:'lastUpdate',width:200">注册时间</th>
        <th data-options="field:'remark',width:220,">备注</th>
    </tr>
    </thead>
</table>

<script type="text/javascript">

    //扩展easyui表单的验证
    $.extend($.fn.validatebox.defaults.rules, {
        //验证汉字
        CHS: {
            validator: function (value) {
                return /^[\u0391-\uFFE5]+$/.test(value);
            },
            message: 'The input Chinese characters only.'
        },
        //移动手机号码验证
        Mobile: {//value值为文本框中的值
            validator: function (value) {
                var reg = /^1[3|4|5|8|9]\d{9}$/;
                return reg.test(value);
            },
            message: '请输入手机号'
        },
        //国内邮编验证
        ZipCode: {
            validator: function (value) {
                var reg = /^[0-9]\d{5}$/;
                return reg.test(value);
            },
            message: 'The zip code must be 6 digits and 0 began.'
        },
        //数字
        Number: {
            validator: function (value) {
                var reg =/^[0-9]*$/;
                return reg.test(value);
            },
            message: '请输入数字'
        },
        //验证用户名是否存在
        userName_isExist: {
            validator: function (value) {
                var v = checkName_Exist(value);
                if (v==true) {
                    return true;
                }
                else  {
                    return false;
                }
            },
            message: '用户名已存在，请更换其它名称！'
        }
    })

    //验证用户名是否存在
    function checkName_Exist(valueName) {
        console.log("userName:"+$('#dlg input[name="userName"]').val().trim());
        var b;
        $.ajax({
            tyep: "get",
            dataType: "json",
            async: false,//是否异步执行
            url: "/user/userNameIsExist?userName=" +$('#dlg input[name="userName"]').val().trim(),
            success: function (msg) {
                b = msg;
            },
            error: function (errorMSG) {
                b = false;
            }
        });
        return b;
    }

    $(function(){
        var pager = $('#dg').datagrid().datagrid('getPager');	// get the pager of datagrid
        pager.pagination({
            buttons:[{
                iconCls:'icon-search',
                handler:function(){
                   search();
                }
            },{
                iconCls:'icon-add',
                handler:function(){
                     newUser();
                }
            },{
                iconCls:'icon-remove',
                handler:function(){
                    removeUser();
                }
            },{
                iconCls:'icon-edit',
                handler:function(){
                    editUser();
                }
            },{
                iconCls:'icon-back',
                handler:function(){
                    backHome();
                }
            }]
        });
    })
    var url;
    function newUser() {
        $('#dlg').dialog('open').dialog('setTitle', '添加用户');
        $('#fm').form('clear');

        url = '/user/insert'; //保存用户的url
    }
    function editUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#editDlg').dialog('open').dialog('setTitle', '编辑用户');
            $('#deit').form('load', row);
            url = '/user/updatePasswordByUserId?userId=' + row.userId;

        }
    }
    function search() {
        $('#searchDlg').dialog('open').dialog('setTitle', '搜索');
        $('#search').form('clear');
            url = '/user/list';
    }

    function saveAdd() {
            //添加
            $('#fm').form('submit', {
                url : url,
                onSubmit : function() {
                    return $(this).form('validate');
                },
                success : function(result) {
                    $('#dlg').dialog('close'); // close the dialog
                    $('#dg').datagrid('reload'); // reload the user data
                }
            });
    }
    function saveUser() {
        //添加、编辑、查询
        var title = $('#searchDlg').parent().find('.panel-title').html().trim();
        var editTitle = $('#editDlg').parent().find('.panel-title').html().trim();
        if (title == '搜索') {
            $('#searchDlg').dialog('close'); // close the dialog
            $('#dg').datagrid('load', {
                userName: $('#search input[name="userName"]').val().trim(),
                phoneNum: $('#search input[name="phoneNum"]').val().trim(),
            });
        }

    }

    function saveEdie() {
            $('#deit').form('submit', {
                url : url,
                onSubmit : function() {
                    return $(this).form('validate');
                },
                success : function(result) {
                    $('#editDlg').dialog('close');
                    $('#dg').datagrid('reload');

                }
            });
    }

    function removeUser() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Confirm',
                    '你确定要删除这个用户?', function(r) {
                        if (r) {
                            $.ajax({
                                    type: "get",
                                    url: "/user/deleteByUserId",
                                    data: {userId:row.userId},
                                    dataType: "json",
                                    success: function (data) {
                                        console.log("0000");
                                        $('#dg').datagrid('reload');
                                    },
                                    error: function (msg) {//ajax请求失败后触发的方法
                                        $.messager.show({ // show error message
                                            title : 'Error',
                                            msg : result.msg
                                        });
                                    }
                                });
                            }
                    });
        }
    }
    function backHome() {
        $('#dg').datagrid('load', {
            url:"/user/list"
        });
    }


</script>

<%--添加对话框--%>
<div id="dlg" class="easyui-dialog"
     style="width: 400px; height: 380px; padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="ftitle">添加用户</div>
    <form id="fm" method="post" novalidate>

        <div class="fitem">
            <label> 姓名: </label> <input name="userName" class="easyui-validatebox textbox"
                                        data-options="required:true,validType:'userName_isExist'">
        </div>
        <div class="fitem">
            <label> 密码: </label> <input name="password" class="easyui-validatebox textbox"
                                        data-options="required:true,validType:'length[6,9]'"
                                        ><%--注意格式--%>
        </div>

        <div class="fitem">
            <label> 电话号码: </label> <input name="phoneNum"
                                          class="easyui-validatebox textbox"
                                          data-options="required:true,validType:'Mobile'">
        </div>

        <div class="fitem">
            <label> 备注: </label> <input name="remark"
                                          class="easyui-validatebox textbox">
        </div>

    </form>
</div>

<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveAdd();>保存</a> <a href="#" class="easyui-linkbutton"
                                     iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close');">取消</a>
</div>

<%--编辑对话框--%>
<div id="editDlg" class="easyui-dialog"
     style="width: 400px; height: 380px; padding: 10px 20px" closed="true"
     buttons="#deitDlg-buttons">
    <div class="ftitle">编辑用户</div>
    <form id="deit" method="post" novalidate>

        <div class="fitem">
            <label> 姓名: </label> <input name="userName" class="easyui-validatebox textbox"
                                        data-options="required:true">
        </div>
        <div class="fitem">
            <label> 密码: </label> <input name="password" class="easyui-validatebox textbox"
                                        data-options="required:true,validType:'length[6,9]'">
        </div>

        <div class="fitem">
            <label> 电话号码: </label> <input name="phoneNum"
                                          class="easyui-validatebox textbox"
                                          data-options="required:true,validType:'Mobile'">
        </div>

        <div class="fitem">
            <label> 备注: </label> <input name="remark"
                                        class="easyui-validatebox textbox">
        </div>

    </form>
</div>

<div id="deitDlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveEdie();>保存</a> <a href="#" class="easyui-linkbutton"
                                     iconCls="icon-cancel" onclick="javascript:$('#editDlg').dialog('close');">取消</a>
</div>

<%--搜索对话框--%>
<div id="searchDlg" class="easyui-dialog"
     style="width: 400px; height: 200px; padding: 10px 20px" closed="true"
     buttons="#dlg-buttonss">
    <div class="ftitle">搜索</div>
    <form id="search" method="post" novalidate>

        <div class="fitem">
            <label> 姓名: </label> <input name="userName" class="easyui-validatebox">
        </div>

        <div class="fitem">
            <label> 电话号码: </label> <input name="phoneNum"
                                          class="easyui-validatebox">
        </div>

    </form>
</div>

<div style="background:red; display: none;width:200px; height: 25px; line-height: 25px; text-align: center; position: absolute; left: 50%; top:0; margin-left: -100px;">
    ${sessionScope.managerResult}
</div>

<div id="dlg-buttonss">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveUser();>搜索</a> <a href="#" class="easyui-linkbutton"
                                     iconCls="icon-cancel" onclick="javascript:$('#searchDlg').dialog('close');">取消</a>
</div>
</body>
</html>