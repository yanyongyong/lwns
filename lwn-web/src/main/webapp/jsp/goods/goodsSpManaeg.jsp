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
       data-options="rownumbers:true,singleSelect:true,pagination:true,url:'/goodsSp/dimSelect',method:'get'">
    <thead>
    <tr>
        <th data-options="field:'spId',width:60">特价编号</th>
        <th data-options="field:'spName',width:100">商品编号</th>
        <th data-options="field:'spGoods',width:110">特价商品</th>
        <th data-options="field:'spCategory',width:170">特价分类</th>
        <th data-options="field:'spStore',width:200">特价门店</th>
       <%-- <th data-options="field:'spPrice',width:80">特价价格</th>--%>
        <th data-options="field:'spTime',width:150">特价开始期限</th>
        <th data-options="field:'remark',width:150">特价结束期限</th>
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
                    newSp();
                }
            },{
                iconCls:'icon-remove',
                handler:function(){
                    removeGoods();
                }
            },{
                iconCls:'icon-edit',
                handler:function(){
                   editSp();
                }
            }]
        });
    })

    var url;
    function newSp(){
        $('#addSp').dialog('open').dialog('setTitle', '添加特价商品');
        $('#sp').form('clear');
        url = '/goodsSp/insert';
    }

    function editSp() {
        var row = $('#goodsSp').datagrid('getSelected');
        if (row) {
            $('#sp').form('clear');
            $('#addSp').dialog('open').dialog('setTitle', '编辑特价商品');
            $('#sp').form('load', row);
            url = '/goodsSp/updateById?spId=' + row.spId;

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

    function removeGoods() {
        var row = $('#goodsSp').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Confirm',
                    '你确定要删除这个商品?', function(r) {
                        if (r) {
                            $.ajax({
                                type: "get",
                                url: "/goodsSp/deleteById",
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
    <div class="ftitle">添加特价商品</div>
    <form id="sp" method="post" novalidate>
        <div class="fitem">
            <label> 特价商品: </label>
            <select class="easyui-combogrid" name="spGoods" style="width:130px" data-options="
			panelWidth: 170,
			idField: 'goodsName',
			textField: 'goodsName',
			url: '/goods/select?page=1&rows=30',
			method: 'post',
			columns: [[
				{field:'goodsId',title:'商品编号',width:80},
				{field:'goodsName',title:'商品名称',width:80},
			]],
			fitColumns: true">
            </select>
        </div>
        <div class="fitem">
            <label> 特价分类: </label>
            <select class="easyui-combogrid" name="spCategory" style="width:130px" data-options="
			panelWidth: 170,
			idField: 'categoryName',
			textField: 'categoryName',
			url: '/spCategory/selectAll?page=1&rows=30',
			method: 'post',
			columns: [[
				{field:'categoryId',title:'商品编号',width:80},
				{field:'categoryName',title:'商品名称',width:80},
			]],
			fitColumns: true">
            </select>
        </div>
        <div class="fitem">
            <label> 特价门店: </label>
            <select class="easyui-combogrid" name="spStore" style="width:130px" data-options="
			panelWidth: 220,
			idField: 'storeName',
			textField: 'storeName',
			url: '/store/dimSelectStore?page=1&rows=30',
			method: 'post',
			multiple:true,
			columns: [[
				{field:'storeId',title:'门店编号',width:80},
				{field:'storeName',title:'门店名字',width:100},
				{field:'storeAddress',title:'门店地址',width:80},
			]],
			fitColumns: true">
            </select>
        </div>
        <div class="fitem">
            <label> 特价开始期限: </label> <input name="spTime" class="easyui-datebox"
                                        data-options="required:true"></div>
        <div class="fitem">
            <label> 特价截止期限: </label> <input name="remark" class="easyui-datebox"
                                     data-options="required:true"></div>
    </form>
</div>

<div id="edit-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveSp();>保存</a> <a href="#" class="easyui-linkbutton"
                                     iconCls="icon-cancel" onclick="javascript:$('#addSp').dialog('close');">取消</a>
</div>



</body>
</html>
