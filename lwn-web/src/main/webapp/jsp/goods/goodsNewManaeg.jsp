<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品信息管理</title>
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
<table id="goodsNew" title="商品信息" style="width:100%;height:380px"
       data-options="rownumbers:true,singleSelect:true,pagination:true,url:'/goods/select',method:'get'">
    <thead>
    <tr>
        <th data-options="field:'goodsId',width:60">商品编号</th>
        <th data-options="field:'goodsType',width:110">商品类型</th>
        <th data-options="field:'goodsName',width:110">商品名称</th>
        <th field="image" style="width: 90px;height: 100px"
            data-options="formatter:function(value, rec){
                console.log(value);
                return '<img src='+value+'>';

            },">商品图片</th>
        <th data-options="field:'price',width:100">商品价格</th>
        <%--<th field="unit.unitName" width="60" data-options="formatter:function(value,r){return r.unit['unitName']}">单位</th>--%>
        <%--<th field="unit.invertory" width="100" data-options="formatter:function(value,r){return r.unit['invertory']}">库存</th>--%>
        <th data-options="field:'producer',width:120">生产商</th>
        <th data-options="field:'producels',width:170">制造商</th>
        <th data-options="field:'createTime',width:180">上架时间</th>
    </tr>
    </thead>
</table>
<script type="text/javascript">


    $(function(){
        var pager = $('#goodsNew').datagrid().datagrid('getPager');	// get the pager of datagrid
        pager.pagination({
            buttons:[{
                iconCls:'icon-search',
                handler:function(){
                    alert('search');
                }
            },{
                iconCls:'icon-add',
                handler:function(){
                    newGoods();
                }
            },{
                iconCls:'icon-remove',
                handler:function(){
                    removeGoods();
                }
            },{
                iconCls:'icon-edit',
                handler:function(){
                   editGoods();
                }
            }]
        });
    })

    function newGoods(){
        $('#addGoods').dialog('open').dialog('setTitle', '添加商品');
        $('#deit').form('clear');
        url = '/goods/insert';
    }

    function editGoods() {
        var row = $('#goodsNew').datagrid('getSelected');
        if (row) {
            $('#addGoods').dialog('open').dialog('setTitle', '编辑商品');
            $('#deit').form('load', row);
            url = '/goods/updateBygoodId?goodsId=' + row.goodsId;

        }
    }

    function saveGoods() {
        var title = $('#addGoods').parent().find('.panel-title').html().trim();
        if (title == "编辑商品"){
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

        $('#deit').form('submit', {
            url : url,
            onSubmit : function() {
                return $(this).form('validate');
            },
            success : function(result) {
                $('#addGoods').dialog('close');
                $('#goodsNew').datagrid('reload');
            }
        });

    }

    function removeGoods() {
        var row = $('#goodsNew').datagrid('getSelected');
        if (row) {
            $.messager.confirm('Confirm',
                    '你确定要删除这个商品?', function(r) {
                        if (r) {
                            $.ajax({
                                type: "get",
                                url: "/goods/deleteBygoodId",
                                data: {goodsId:row.goodsId},
                                          success: function (date) {
                                    $('#goodsNew').datagrid('reload');
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


<div id="addGoods" class="easyui-dialog"
     style="width: 400px; height: 400px; padding: 10px 20px" closed="true"
     buttons="#edit-buttons">
    <div class="ftitle">添加商品</div>
    <form id="deit" method="post" novalidate>
        <div class="fitem">
            <label> 商品类型: </label>
            <input name="goodsType" class="easyui-combotree"
                   data-options="
                       url:'/goodsType/selectByType',
                       method:'get',
                       idField:'text',
                       valueField:'text',
                       textField:'text',
                       required:true"
                   style="width:130px;">

        </div>

        <div class="fitem">
            <label> 商品名称: </label> <input name="goodsName" class="easyui-validatebox"
                                          data-options="required:true"></div>
        <div class="fitem">
            <label> 商品图片: </label> <input name="image" class="easyui-validatebox"></div>
        <div class="fitem">
            <label> 商品价格: </label> <input name="price" class="easyui-validatebox"
                                        data-options="required:true"></div>
        <%--<div class="fitem">--%>
            <%--<label> 库存: </label>--%>
            <%--<select class="easyui-combogrid" name="number" style="width:130px" data-options="--%>
			<%--panelWidth: 180,--%>
			<%--idField: 'remark',--%>
			<%--textField: 'remark',--%>
			<%--url: '/unit/dimSelect?page=1&rows=30',--%>
			<%--method: 'post',--%>
			<%--columns: [[--%>
				<%--{field:'goodsName',title:'商品名称',width:100},--%>
				<%--{field:'remark',title:'库存',width:80},--%>
			<%--]],--%>
			<%--fitColumns: true">--%>
            <%--</select>--%>
        <%--</div>--%>
        <%--<div class="fitem">--%>
            <%--<label> 单位: </label>--%>
            <%--<select class="easyui-combogrid" name="remark" style="width:130px" data-options="--%>
			<%--panelWidth: 220,--%>
			<%--idField: 'goodsName',--%>
			<%--textField: 'goodsName',--%>
			<%--url: '/unit/dimSelect?page=1&rows=30',--%>
			<%--method: 'post',--%>
			<%--columns: [[--%>
				<%--{field:'goodsId',title:'单位编号',width:80},--%>
				<%--{field:'goodsName',title:'商品单位',width:80},--%>
			<%--]],--%>
			<%--fitColumns: true">--%>
            <%--</select>--%>
        <%--</div>--%>
        <div class="fitem">
            <label> 生产商: </label> <input name="producer" class="easyui-validatebox"
                                        data-options="required:true"></div>
        <div class="fitem">
            <label> 制造商: </label> <input name="producels" class="easyui-validatebox"
                                     data-options="required:true"></div>
        <div class="fitem">
            <label> 上架时间: </label> <input name="createTime" class="easyui-datebox"
                                         data-options="required:true"></div>
    </form>
</div>

<div id="edit-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
       onclick=saveGoods();>保存</a> <a href="#" class="easyui-linkbutton"
                                     iconCls="icon-cancel" onclick="javascript:$('#addGoods').dialog('close');">取消</a>
</div>



</body>
</html>
