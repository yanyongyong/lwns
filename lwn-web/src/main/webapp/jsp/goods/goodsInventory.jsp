<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品库存管理</title>
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/demo/demo.css">
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
</head>

<style>
    #deit{
        margin: 0;
        padding: 10px 30px;
    }
    .fitem {
        margin-bottom: 5px;
    }
    .fitem label {
        display: inline-block;
        width: 80px;
    }


</style>

<body>
<div style="margin:10px 0;"></div>
<table id="goodsSp" title="商品特价" style="width:100%;height:400px"
       data-options="rownumbers:true,singleSelect:true,pagination:true,url:'/unit/dimSelect',method:'get'">
    <thead>
    <tr>
        <th data-options="field:'unitId',width:60">单位编号</th>
        <th data-options="field:'goodsName',width:100">商品名称</th>
        <th data-options="field:'unitName',width:110">单位</th>
        <th data-options="field:'remark',width:100">库存</th>
    </tr>
    </thead>
</table>
<script type="text/javascript">


    $(function(){
        var pager = $('#goodsSp').datagrid().datagrid('getPager');	// get the pager of datagrid
        pager.pagination({
            buttons:[{
                iconCls:'icon-search',
                handler:function(){
                    alert('search');
                }
            },{
                iconCls:'icon-add',
                handler:function(){
                    newUnit();
                }
            },{
                iconCls:'icon-remove',
                handler:function(){
                    removeUnit();
                }
            },{
                iconCls:'icon-edit',
                handler:function(){
                    editUnit();
                }
            }]
        });
    })

    var url;
    function newUnit(){
        $('#addSp').dialog('open').dialog('setTitle', '添加商品单位');
        $('#sp').form('clear');
        url = '/unit/insert';
    }

    function editUnit() {
        var row = $('#goodsSp').datagrid('getSelected');
        if (row) {
            $('#sp').form('clear');
            $('#addSp').dialog('open').dialog('setTitle', '编辑特价商品');
            $('#sp').form('load', row);
            url = '/unit/updateById?unitId=' + row.unitId;

        }
    }

    function saveSp() {

        $('#sp').form('submit', {
            url : url,
            onSubmit : function() {
                return $(this).form('validate');
            },
            success : function(result) {
                $('#addSp').dialog('close');
                $('#goodsSp').datagrid('reload');
            }});



    }

    function removeUnit() {
        var row = $('#goodsSp').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Confirm',
                    '你确定要删除这个商品?', function(r) {
                        if (r) {
                            $.ajax({
                                type: "get",
                                url: "/unit/deleteById",
                                data: {spId:row.spId},
                                success: function (date) {
                                    $('#goodsSp').datagrid('reload');
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



</script>

<div id="addSp" class="easyui-dialog"
     style="width: 400px; height: 400px; left: 314px; top: 80px; padding: 10px 20px" closed="true"
     buttons="#edit-buttons">
    <div class="ftitle">添加商品单位</div>
    <form id="sp" method="post" novalidate>
        <div class="fitem">
            <label> 商品名称: </label>
            <select class="easyui-combogrid" name="goodsName" style="width:130px" data-options="
			panelWidth: 220,
			idField: 'goodsName',
			textField: 'goodsName',
			url: '/goods/select?page=1&rows=30',
			method: 'post',
			columns: [[
				{field:'goodsId',title:'商品编号',width:80},
				{field:'goodsName',title:'商品名称',width:80},
				{field:'number',title:'库存',width:60},
			]],
			fitColumns: true">
            </select>
        </div>
        <div class="fitem">
            <label> 单位: </label> <input name="unitName" class="easyui-validatebox"
                                          data-options="required:true"></div>
        <div class="fitem">
            <label> 库存: </label> <input name="remark"></div>
    </form>
</div>

<div id="edit-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveSp();>保存</a> <a href="#" class="easyui-linkbutton"
                                   iconCls="icon-cancel" onclick="javascript:$('#addSp').dialog('close');">取消</a>
</div>



</body>
</html>
