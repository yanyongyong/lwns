<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品管理</title>
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/demo/demo.css">
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<div style="margin:0px;"></div>
<div class="easyui-tabs" style="width:1050px;height:560px">
    <div title="商品信息管理" style="padding:10px">
        <iframe id="goodsNewManaeg" name="goodsNewManaeg" style="width:100%; height:100%;border-width: 0px;" src="goodsNewManaeg.jsp"></iframe>
    </div>
    <div title="商品特价管理" style="padding:10px">
        <iframe id="goodsSpManaeg" name="goodsClassify" style="width:100%; height:100%;border-width: 0px;" src="goodsSpManaeg.jsp"></iframe>
    </div>
    <div title="商品分类管理" style="padding:10px">
        <iframe id="goodsClassify" name="goodsClassify" style="width: 800px;; height: 700px;;border-width: 0px;" src="goodsClassify.jsp"></iframe>

    </div>
    <div title="仓库管理" style="padding:10px">
        <iframe id="goodsInventory" name="goodsInventory" style="width:100%; height:100%;border-width: 0px;" src="goodsUnit.jsp"></iframe>
    </div>
</div>





</body>
</html>
