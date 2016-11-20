<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">

  <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/demo/demo.css">
  <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.min.js"></script>
  <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="../../js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>

</head>
<body>
<div style="margin:20px 0;"></div>
<table id="dg" class="easyui-datagrid" title="收银" style="width:1000px;height:400px"
       data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				toolbar: '#tb',
			    method: 'get',
				onClickRow: onClickRow,
			">
  <thead>
  <tr>
    <th data-options="field:'cartId',width:80">购物车编号</th>
    <th data-options="field:'goodsId',width:80,editor:{type:'numberbox'}">商品编号</th>
    <th data-options="field:'goodsName',width:150,
						formatter:function(value,row){
							return row.goodsName;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'goodsName',
								textField:'goodsName',
								method:'get',
								url:'/goods/selectAll',
								 onSelect:function (e) {
                                var editors = $('#dg').datagrid('getEditors', editIndex);//editIndex 表示当前选中的行号
                                $(editors[0].target).numberbox('setValue',e.goodsId);
                                $(editors[3].target).numberbox('setValue',e.price);
                                $(editors[2].target).numberbox('setValue',e.unit.invertory);
                                $(editors[4].target).numberbox('setValue','1');
                                $(editors[5].target).textbox('setValue',e.remark);
                           }
							}
						}">商品名称</th>
    <th data-options="field:'inventory',width:100,editor:'numberbox'">商品库存</th>
    <th data-options="field:'price',width:150,editor:{type:'numberbox',options:{precision:2}}">商品价格</th>
    <th data-options="field:'goodsNumber',width:100,editor:{type:'numberbox'}">数量</th>
    <th data-options="field:'remark',width:130,editor:'textbox'">商品促销</th>
   <%-- <th data-options="field:'memberName',width:120,
                         formatter:function(value,row){
							return row.memberName;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'memberName',
								textField:'memberName',
								method:'get',
								url:'/member/searchMember',
								 onSelect:function (e) {
                                var editors = $('#dg').datagrid('getEditors', editIndex);
                                $(editors[7].target).numberbox('setValue',e.grade.integralDiscount);
                           }
							}
                  }">会员</th>
    <th data-options="field:'discount',width:100,editor:{type:'numberbox',options:{precision:2}}">会员折扣</th>--%>
    <th data-options="field:'totalPrice',width:210,editor:{type:'numberbox'}">最后价格</th>
    <th data-options="field:'menber',width:210,editor:{type:'textbox'}">会员</th>
    <th data-options="field:'mop',width:210,editor:{type:'textbox'}">支付方式</th>
  </tr>
  </thead>
</table>

<div id="tb" style="height:auto">
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加商品</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">移除商品</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="ply()">结账</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">撤销</a>
</div>

<div id="buttonDiv">
  <h2 style="float:left;margin-top: 25px;margin-left: 840px">总计：</h2>
  <input id="total" style="float:left;width: 100px;margin-top: 25px">
</div>

<script type="text/javascript">

  function changNum() {

    var year=$('#memberName').combobox('getText');
    var year2=$('#memberName').combobox('getValue');
    memberMoney = document.getElementById("money").value * 0.8;
    document.getElementById("money").value = parseInt(memberMoney);

  }


  function addMember(mop) {
    var member=$('#memberName').combobox('getText');
    var row = $('#dg').datagrid('getRows');
    for (var i = 0; i < row.length; i++) {
      $('#dg').datagrid('beginEdit', i);
      var editors = $('#dg').datagrid('getEditors', i);
      console.log("I:"+i);
      $(editors[7].target).textbox('setValue',member);
      $(editors[8].target).textbox('setValue',mop);
      $('#dg').datagrid('endEdit',i);
    }
  }


  function compute() {//计算函数
    var row = $('#dg').datagrid('getRows');//获取当前的数据行
    var ptotal = 0;//计算listprice的总和
    for (var i = 0; i < row.length-1; i++) {
      ptotal += parseInt(row[i]['totalPrice']);
    }
    document.getElementById("total").value = ptotal + "元";
    document.getElementById("money").value = ptotal;
  }

  var editIndex = undefined;
  function endEditing(){
    if (editIndex == undefined){return true}
    if ($('#dg').datagrid('validateRow', editIndex)){
      var selectedRow = $('#dg').datagrid('getSelected');
      $('#dg').datagrid('getRows')[editIndex]['goodsNumber'];
      $('#dg').datagrid('endEdit', editIndex);
      //saveData();
      editIndex = undefined;
      return true;
    } else {
      return false;
    }
  }
  function saveData() {
    var rows = $('#dg').datagrid('getRows');
    var data = [];
    for(var i = 0, iL = rows.length; i < iL; i++) {
      var obj = rows[i];
      var tmp = {};
      tmp.goodsId=obj.goodsId;
      tmp.goodsName = obj.goodsName;
      tmp.price = obj.price;
      tmp.goodsNumber = obj.goodsNumber;
      tmp.inventory = obj.inventory;
      tmp.totalPrice = obj.totalPrice;
      data.push(tmp);
    }

    $.ajax({
      type: 'post',
      url: '/cart/insert',
      data: {
        arr: data

      },
      success: function(){

      }
    });
  }
  function onClickRow(index){
    compute();
    var rows = $("#dg").datagrid("getSelected");
    var cost = rows.goodsNumber * rows.price*rows.remark;
    rows.totalPrice=cost;

    if (editIndex != index){
      if (endEditing()){
        $('#dg').datagrid('selectRow', index)
                .datagrid('beginEdit', index);
        editIndex = index;
      } else {
        $('#dg').datagrid('selectRow', editIndex);
      }
    }
  }
  var url;
  /*新增*/
  function append(){
    if (endEditing()){
      $('#dg').datagrid('appendRow',{status:'P'});
      editIndex = $('#dg').datagrid('getRows').length-1;
      $('#dg').datagrid('selectRow', editIndex)
              .datagrid('beginEdit', editIndex);
    }

    compute();
  }
  /*删除*/
  function removeit(){
    if (editIndex == undefined){return}
    $('#dg').datagrid('cancelEdit', editIndex)
            .datagrid('deleteRow', editIndex);
    editIndex = undefined;
  }
  function accept(){
    if (endEditing()){
      var rows = $('#dg').datagrid('getRows');
      var data = [];
      for(var i = 0, iL = rows.length; i < iL; i++) {
        var obj = rows[i];
        var tmp = {};
        tmp.goodsName = obj.goodsName;
        tmp.goodsId=obj.goodsId;
        tmp.price = obj.price;
        tmp.goodsNumber=obj.goodsNumber;
        tmp.remark = obj.totalPrice;
        tmp.mop = obj.mop;
        tmp.menber = obj.menber;
        tmp.discount = obj.remark;
        data.push(tmp);
      }
      $.ajax({
        type: 'post',
        url: '/card/insert',
        data: {
          arr: JSON.stringify(data),
        },
        success: function(){
          $('#pay').dialog('close');
          alert("付款成功");
        }
      });

    }
  }
  function reject(){
    $('#dg').datagrid('rejectChanges');
    editIndex = undefined;
  }
  function getChanges(){
    var rows = $('#dg').datagrid('getChanges');
    alert(rows.length+' rows are changed!');
  }
  function ply() {
    $('#pay').dialog('open').dialog('setTitle', '结账');
  }
</script>


<div id="pay" class="easyui-dialog"
     style="width: 350px; height: 350px; left: 314px; top: 80px; padding: 10px 20px" closed="true"
     buttons="#buttons">
  <div class="ftitle"></div>
  <form id="payFrom" method="post" novalidate>

    <div class="fitem" style=" margin-top: 38px">
      <label> 会员账号: </label>
      <select id="memberName" class="easyui-combogrid" name="memberName"  style="width:130px" data-options="
			panelWidth: 260,
			idField: 'memberName',
			textField: 'memberId',
			url: '/member/searchMemberOnLine',
			method: 'post',
			onSelect:changNum,
			columns: [[
				{field:'memberId',title:'会员编号',width:60},
				{field:'memberName',title:'会员名称',width:60},
				{field:'memberBalance',title:'余额',width:80},
				{field:'grade.integralDiscount',formatter:function(value,r){return r.grade['integralDiscount']},title:'会员折扣',width:60},
			]],
			fitColumns: true">
      </select>
    </div>

    <div class="fitem" style="margin-top: 10px">
      <label> 金额总计: </label>
      <input id="money" name="">
    </div>

    <div class="fitem" style="margin-top: 10px">
      <label> 支付方式: </label>
      <select id="mop" onchange="addMember(this.value)" style=" width: 130px" name="mop">
        <option value="刷卡">刷卡</option>
        <option value="支付宝">支付宝</option>
        <option value="微信">微信</option>
        <option value="翼支付">翼支付</option>
        <option value="现金">现金</option>
        <option value="余额">余额</option>
      </select>
    </div>


  </form>
</div>
<div id="buttons">
  <a href="#" class="easyui-linkbutton" iconCls="icon-ok"
     onclick="accept()">提交</a> <a href="#" class="easyui-linkbutton"
                                 iconCls="icon-cancel" onclick="javascript:$('#pay').dialog('close');">取消</a>
</div>

</body>
</html>