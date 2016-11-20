<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>盘点</title>
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

<table id="dg" title="商品信息" style="width:100%;height: 380px;" class="easyui-datagrid"
       data-options="rownumbers:true,singleSelect:true,pagination:true,url:'/card/dimSelect',method:'post'">
    <thead>
    <tr>
        <th data-options="field:'cartId',width:90">盘点编号</th>
        <th data-options="field:'goodsId',width:90">商品编号</th>
        <th data-options="field:'goodsName',width:120">商品名称</th>
        <th data-options="field:'goodsNumber',width:90,">数量</th>
        <th data-options="field:'price',width:100,">商品原价</th>
        <th data-options="field:'discount',width:100,">折扣</th>
        <th data-options="field:'remark',width:100,">折扣价</th>
        <th data-options="field:'mop',width:120,">交易方式</th>
        <th data-options="field:'menber',width:100,">会员</th>
        <th data-options="field:'payTime',width:115,">交易时间</th>
    </tr>
    </thead>
</table>

<div id="buttonDiv">
    <h2 style="float:left;margin-top: 25px;margin-left: 870px">总计：</h2>
    <input id="total" style="float:left;width: 100px;margin-top: 25px">
</div>


<script type="text/javascript">

    var url;

    function searchTime() {

        $('#searchDlg').dialog('open').dialog('setTitle', '搜索');
        $('#search').form('clear');
        url = '/card/dimSelect';
        console.log("123");
    }

    function saveUser() {
        //添加、编辑、查询
        var title = $('#searchDlg').parent().find('.panel-title').html().trim();//获取title
        if (title == '搜索') {
            $('#searchDlg').dialog('close');
            $('#dg').datagrid('load', {
                payTime: $('#search input[name="payTime"]').val().trim(),
                mop: $('#search input[name="mop"]').val().trim()
            });
            compute();
        }

    }


    function backHome() {
        $('#dg').datagrid('load', {
            url:"/card/dimSelect"
        });
    }

    $(function(){
        var pager = $('#dg').datagrid().datagrid('getPager');
        pager.pagination({
            buttons:[{
                iconCls:'icon-search',
                handler:function(){
                    searchTime();
                }
            },{
                iconCls:'icon-back',
                handler:function(){
                    backHome();
                }
            }]
        });
    });


    function compute() {//计算函数
        console.log("11111111");

        var data=$('#dg').datagrid('getData');

        var ptotal = 0;//计算listprice的总和
        for (var i = 0; i < data.rows.length; i++) {
            ptotal += parseInt(data.rows[i]['remark']);
        }
        console.log("ptotal :"+ptotal);
        document.getElementById("total").value = ptotal + "元";
    }

</script>


<%--搜索对话框--%>
<div id="searchDlg" class="easyui-dialog"
     style="width: 400px; height: 200px; padding: 10px 20px" closed="true"
     buttons="#dlg-buttonss">
    <div class="ftitle">搜索</div>
    <form id="search" method="post" novalidate>

        <div class="fitem">
            <label> 交易时间: </label> <input name="payTime" class="easyui-datebox">
        </div>

        <div class="fitem">
            <label> 交易方式: </label>
            <select name="mop" style=" width: 130px" class="easyui-combobox">
                <option value="支付宝">支付宝</option>
                <option value="微信">微信</option>
                <option value="翼支付">翼支付</option>
                <option value="现金">现金</option>
                <option value="刷卡">刷卡</option>
            </select>
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