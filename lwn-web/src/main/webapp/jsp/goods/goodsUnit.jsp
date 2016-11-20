<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品特价管理</title>
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
        <th data-options="field:'unitId',width:100">库存编号</th>
        <th data-options="field:'goodsNumber',width:100">商品编号</th>
        <th data-options="field:'goodsName',width:150">商品名称</th>
        <th data-options="field:'unitName',width:110">单位</th>
        <th data-options="field:'invertory',width:110">库存</th>
        <th data-options="field:'store',width:200">仓库名称</th>
        <th data-options="field:'remark',width:160">备注</th>
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
                    search();
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

    function search() {
        $('#searchForm').form('clear');
        $('#searchDlg').dialog('open').dialog('setTitle', '选择仓库');
        url = '/unit/dimSelect';
    }

    function editUnit() {
        var row = $('#goodsSp').datagrid('getSelected');
        if (row) {
            $('#sp').form('clear');
            $('#addSp').dialog('open').dialog('setTitle', '编辑仓库');
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

    function saveSearch() {

        $('#searchForm').form('submit', {
            url : url,
            onSubmit : function() {
                return $(this).form('validate');
            },
            success : function(result) {
                $('#searchDlg').dialog('close');
//                $('#goodsSp').datagrid('reload');
                $('#goodsSp').datagrid('load', {
                    store: $('#searchForm input[name="store"]').val().trim(),
                });
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


<div id="searchDlg" class="easyui-dialog"
     style="width: 300px; height: 200px; left: 314px; top: 80px; padding: 10px 20px" closed="true"
     buttons="#edit-button">
    <div class="ftitle">选择仓库</div>
    <form id="searchForm" method="post" novalidate>
        <div class="fitem">
            <label> 仓库名称: </label>
            <select class="easyui-combogrid" name="store" style="width:130px" data-options="
			panelWidth: 220,
			idField: 'storeName',
			textField: 'storeName',
			url: '/store/dimSelectStore?page=1&rows=50',
			method: 'post',
			columns: [[
				{field:'storeId',title:'仓库编号',width:80},
				{field:'storeName',title:'仓库名称',width:80},
			]],
			fitColumns: true">
            </select>
        </div>
    </form>
</div>

<div id="edit-button">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveSearch();>保存</a> <a href="#" class="easyui-linkbutton"
                                   iconCls="icon-cancel" onclick="javascript:$('#searchDlg').dialog('close');">取消</a>
</div>





<div id="addSp" class="easyui-dialog"
     style="width: 400px; height: 400px; left: 314px; top: 80px; padding: 10px 20px" closed="true"
     buttons="#edit-buttons">
    <div class="ftitle">编辑</div>
    <form id="sp" method="post" novalidate>

        <div class="fitem">
            <label> 仓库名称: </label>
            <select class="easyui-combogrid" name="store" style="width:130px" data-options="
			panelWidth: 200,
			idField: 'storeName',
			textField: 'storeName',
			url: '/store/dimSelectStore?page=1&rows=50',
			method: 'post',
			columns: [[
				{field:'storeId',title:'仓库编号',width:80},
				{field:'storeName',title:'仓库名称',width:120},
			]],
			fitColumns: true">
            </select>
        </div>
        <div class="fitem">
            <label> 单位: </label> <input name="unitName" class="easyui-validatebox"
                                          data-options="required:true"></div>
        <div class="fitem">
            <label> 库存: </label> <input name="invertory"></div>

        <div class="fitem">
            <label> 备注: </label> <input name="remark"></div>
    </form>
</div>

<div id="edit-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveSp();>保存</a> <a href="#" class="easyui-linkbutton"
                                   iconCls="icon-cancel" onclick="javascript:$('#addSp').dialog('close');">取消</a>
</div>



</body>
</html>
