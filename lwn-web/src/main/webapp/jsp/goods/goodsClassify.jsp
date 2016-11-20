<%--
  Created by IntelliJ IDEA.
  User: yanyong
  Date: 2016/10/20
  Time: 下午4:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品分类</title>
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.5/demo/demo.css">
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../../js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
</head>

<body>
<h2>商品分类</h2>
<div style="margin:20px 0;"></div>
<div class="easyui-panel" style="padding:5px; width: 700px;">
    <ul class="easyui-tree" data-options="url:'/goodsType/selectByType',method:'get',loadFilter:myLoadFilter"></ul>
</div>
<script>
    function myLoadFilter(data, parent){
        var state = $.data(this, 'tree');

        function setData(){
            var serno = 1;
            var todo = [];
            for(var i=0; i<data.length; i++){
                todo.push(data[i]);
            }
            while(todo.length){
                var node = todo.shift();
                if (node.id == undefined){
                    node.id = '_node_' + (serno++);
                }
                if (node.children){
                    node.state = 'closed';
                    node.children1 = node.children;
                    node.children = undefined;
                    todo = todo.concat(node.children1);
                }
            }
            state.tdata = data;
        }
        function find(id){
            var data = state.tdata;
            var cc = [data];
            while(cc.length){
                var c = cc.shift();
                for(var i=0; i<c.length; i++){
                    var node = c[i];
                    if (node.id == id){
                        return node;
                    } else if (node.children1){
                        cc.push(node.children1);
                    }
                }
            }
            return null;
        }

        setData();

        var t = $(this);
        var opts = t.tree('options');
        opts.onBeforeExpand = function(node){
            var n = find(node.id);
            if (n.children && n.children.length){return}
            if (n.children1){
                var filter = opts.loadFilter;
                opts.loadFilter = function(data){return data;};
                t.tree('append',{
                    parent:node.target,
                    data:n.children1
                });
                opts.loadFilter = filter;
                n.children = n.children1;
            }
        };
        return data;
    }
</script>
</body>

</html>
