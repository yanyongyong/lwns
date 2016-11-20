<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/demo/demo.css">
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<div style="margin:0px;overflow: hidden"></div>
<div class="easyui-tabs" style="width:1050px;height:560px; overflow: hidden";>
    <div title="用户信息" style="padding:10px;overflow: hidden">
        <iframe id="roleSearch" name="roleSearch" style="width:100%; height:100%;border-width: 0px;" src="roleSearch.jsp"></iframe>
    </div>
    <div title="角色信息" style="padding:10px;overflow: hidden">
        <iframe id="roleManaeg" name="roleManaeg" style="width:100%; height:100%;border-width: 0px;" src="roleManaeg.jsp"></iframe>
    </div>
</div>





</body>
</html>
