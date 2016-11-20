<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员信息</title>
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
       data-options="rownumbers:true,singleSelect:true,pagination:true,url:'/member/selectAllMember',method:'post'">
    <thead>
    <tr>
        <th data-options="field:'memberId',width:60">会员编号</th>
        <th data-options="field:'memberName',width:110">会员名称</th>
        <th field="grade.gradeName" width="80" data-options="formatter:function(value,r){return r.grade['gradeName']}">等级</th>
        <th data-options="field:'memberPhone',width:100">电话号码</th>
        <th data-options="field:'memberAddress',width:190">地址</th>
        <th data-options="field:'createdStore',width:160,">办卡地点</th>
        <th data-options="field:'memberIntegral',width:80,">积分</th>
        <th data-options="field:'memberBalance',width:100,">余额</th>
        <th data-options="field:'remark',width:120,">备注</th>
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
                iconCls:'icon-add',
                handler:function(){
                    newMember();
                }
            },{
                iconCls:'icon-remove',
                handler:function(){
                    removepriv();
                }
            },{
                iconCls:'icon-edit',
                handler:function(){
                    editMember();
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
    function newMember() {
        $('#dlg').dialog('open').dialog('setTitle', '添加会员');
        $('#fm').form('clear');
        url = '/member/insertMember';
    }
    function editMember() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#editDlg').dialog('open').dialog('setTitle', '编辑会员');
            $('#deit').form('load', row);
            url = '/member/updateMemberById?memberId=' + row.memberId;

        }
    }
    function search() {
        $('#searchDlg').dialog('open').dialog('setTitle', '搜索');
        $('#fm').form('clear');
            url = '/member/selectAllMember';
    }
    function saveUser() {
        //添加、编辑、查询
        var title = $('#searchDlg').parent().find('.panel-title').html().trim();//获取title
        var editTitle = $('#editDlg').parent().find('.panel-title').html().trim();//获取title
        if (title == '搜索') {
            $('#searchDlg').dialog('close');
            $('#dg').datagrid('load', {
                memberName: $('#search input[name="memberName"]').val().trim(),
            });
        }
        if (editTitle == '编辑会员'){
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
                                    url: "/member/deleteMemberById",
                                    data: {memberId:row.memberId},
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
            url:"/member/selectAllMember"
        });
    }


</script>

<%--添加对话框--%>
<div id="dlg" class="easyui-dialog"
     style="width: 400px; height: 260px; padding: 10px 20px" closed="true"
     buttons="#dlg-buttons">
    <div class="ftitle">添加会员</div>
    <form id="fm" method="post" novalidate>

        <div class="fitem">
            <label> 会员名称: </label> <input name="memberName" class="easyui-validatebox textbox"
                                          data-options="required:true">
        </div>

        <div class="fitem">
            <label> 电话号码: </label> <input name="memberPhone" class="easyui-validatebox textbox"
                                          data-options="required:true">
        </div>

        <div class="fitem">
            <label> 地址: </label> <input name="memberAddress" class="easyui-validatebox textbox"
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
    <div class="ftitle">编辑会员</div>
    <form id="deit" method="post" novalidate>

        <div class="fitem">
            <label> 会员名称: </label> <input name="memberName" class="easyui-validatebox textbox"
                                        data-options="required:true">
        </div>

        <div class="fitem">
            <label> 电话号码: </label> <input name="memberPhone" class="easyui-validatebox textbox"
                                        data-options="required:true">
        </div>

        <div class="fitem">
            <label> 地址: </label> <input name="memberAddress"
                                        class="easyui-validatebox textbox">
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
            <label> 会员名称: </label> <input name="memberName" class="easyui-validatebox">
        </div>

    </form>
</div>

<div id="dlg-buttonss">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveUser();>搜索</a> <a href="#" class="easyui-linkbutton"
                                     iconCls="icon-cancel" onclick="javascript:$('#searchDlg').dialog('close');">取消</a>
</div>
</body>
</html>