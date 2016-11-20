<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>权限信息</title>
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

<table id="dg" title="权限信息" style="width:100%;height: 520px;" class="easyui-datagrid"
       data-options="rownumbers:true,singleSelect:true,pagination:true,url:'/priv/dimSelectPriv',method:'post'">
    <thead>
    <tr>
        <th data-options="field:'rolePrivId',width:120">权限编号</th>
        <th data-options="field:'privName',width:230">权限名称</th>
        <th data-options="field:'roleName',width:160">角色名称</th>
        <th data-options="field:'parentId',width:150">上级</th>
        <th data-options="field:'updateBy',width:130">更新人</th>
        <th data-options="field:'updateTime',width:230,">更新时间</th>
    </tr>
    </thead>
</table>

<script type="text/javascript">


    $(function(){
        var pager = $('#dg').datagrid().datagrid('getPager');
        pager.pagination({
            buttons:[{
                iconCls:'icon-search',
                handler:function(){
                   search();
                }
            },{
                iconCls:'icon-edit',
                handler:function(){
                    editPriv();
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
    function newPriv() {
        $('#dlg').dialog('open').dialog('setTitle', '添加权限');
        $('#fm').form('clear');
        url = '/priv/insertPriv';
    }
    function editPriv() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#editDlg').dialog('open').dialog('setTitle', '编辑权限');
            $('#deit').form('load', row);
            url = '/priv/editByprivId?rolePrivId=' + row.rolePrivId;

        }
    }
    function search() {
        $('#searchDlg').dialog('open').dialog('setTitle', '搜索');
        $('#fm').form('clear');
            url = '/priv/dimSelectPriv';
    }
    function saveUser() {
        //添加、编辑、查询
        var title = $('#searchDlg').parent().find('.panel-title').html().trim();//获取title
        var editTitle = $('#editDlg').parent().find('.panel-title').html().trim();//获取title
        if (title == '搜索') {
            $('#searchDlg').dialog('close'); // close the dialog
            $('#dg').datagrid('load', {
                privName: $('#search input[name="privName"]').val().trim(),
            });
        }
        if (editTitle == '编辑权限'){
            //编辑
            $('#deit').form('submit', {
                url : url,
                onSubmit : function() {
                    return $(this).form('validate');
                },
                success : function(result) {
                    $('#editDlg').dialog('close'); // close the dialog
                    $('#dg').datagrid('reload'); // reload the user data
                }
            });
        }
        else{
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
    }
    function removepriv() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Confirm',
                    '你确定要删除这个权限?', function(r) {
                        if (r) {
                            $.ajax({
                                    type: "get",
                                    url: "/priv/deleteByPrivId",
                                    data: {rolePrivId:row.rolePrivId},
                                    dataType: "json",
                                    success: function (date) {
                                        $('#dg').datagrid('reload');
                                    },
                                    error: function (msg) {//ajax请求失败后触发的方法
                                        $.messager.show({ // show error message
                                            title : 'Error',
                                            msg : data.msg
                                        });
                                    }
                                });
                            }
                    });
        }
    }
    function backHome() {
        $('#dg').datagrid('load', {
            url:"/priv/dimSelectPriv"
        });
    }


</script>

<%--添加对话框--%>
<div id="dlg" class="easyui-dialog"
     style="width: 400px; height: 260px; padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="ftitle">添加权限</div>
    <form id="fm" method="post" novalidate>

        <div class="fitem">
            <label> 权限名称: </label> <input name="privName" class="easyui-validatebox textbox"
                                        data-options="required:true">
        </div>

        <div class="fitem">
            <label> 角色名称: </label> <input name="roleName" class="easyui-validatebox textbox"
                                          data-options="required:true">
        </div>

        <div class="fitem">
            <label> 上级: </label> <input name="parentId" class="easyui-validatebox textbox"
                                          data-options="required:true">
        </div>

    </form>
</div>

<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveUser();>保存</a> <a href="#" class="easyui-linkbutton"
                                     iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close');">取消</a>
</div>

<%--编辑对话框--%>
<div id="editDlg" class="easyui-dialog"
     style="width: 400px; height: 260px; padding: 10px 20px" closed="true"
     buttons="#deitDlg-buttons">
    <div class="ftitle">编辑权限</div>
    <form id="deit" method="post" novalidate>

        <div class="fitem">
            <label> 权限名称: </label> <input name="privName" class="easyui-validatebox textbox"
                                        data-options="required:true">
        </div>

        <div class="fitem">
            <label> 上级: </label> <input name="parentId"
                                        class="easyui-validatebox textbox">
        </div>

        <div class="fitem" style="display: none">
            <label> 角色名称: </label> <input name="roleName" class="easyui-validatebox textbox"
                                          data-options="required:true">
        </div>

    </form>
</div>

<div id="deitDlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveUser();>保存</a> <a href="#" class="easyui-linkbutton"
                                     iconCls="icon-cancel" onclick="javascript:$('#editDlg').dialog('close');">取消</a>
</div>

<%--搜索对话框--%>
<div id="searchDlg" class="easyui-dialog"
     style="width: 400px; height: 200px; padding: 10px 20px" closed="true"
     buttons="#dlg-buttonss">
    <div class="ftitle">搜索</div>
    <form id="search" method="post" novalidate>

        <div class="fitem">
            <label> 权限名称: </label> <input name="privName" class="easyui-validatebox">
        </div>

    </form>
</div>

<div style="background:red; display: none;width:200px; height: 25px; line-height: 25px; text-align: center; position: absolute; left: 50%; top:0; margin-left: -100px;">
    ${sessionScope.privResult}
</div>


<div id="dlg-buttonss">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveUser();>搜索</a> <a href="#" class="easyui-linkbutton"
                                     iconCls="icon-cancel" onclick="javascript:$('#searchDlg').dialog('close');">取消</a>
</div>
</body>
</html>