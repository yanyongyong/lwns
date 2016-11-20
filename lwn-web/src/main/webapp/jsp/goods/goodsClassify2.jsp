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
<div style="margin:10px 0;">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="edit()">Edit</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()">Save</a>
    <a href="javascript:void(0)" class="easyui-linkbutton selectItemCat" onclick="e()">Cancel</a>
</div>
<table id="tg" class="easyui-treegrid" title="Editable TreeGrid" style="width:100%;height:250px"
       data-options="
                iconCls: 'icon-ok',
				rownumbers: true,
				animate: true,
				collapsible: true,
				url: '/goodsType/selectByType',
				method: 'get',
				treeField: 'text',
				showFooter: true,
			">
    <thead>
    <tr>
        <th data-options="field:'text',width:180,editor:'text'">Task Name</th>
        <th data-options="field:'parentId',width:60,align:'right',editor:'numberbox'">Persons</th>
        <th data-options="field:'begin',width:80,editor:'datebox'">Begin Date</th>
        <th data-options="field:'end',width:80,editor:'datebox'">End Date</th>
        <th data-options="field:'progress',width:120,formatter:formatProgress,editor:'numberbox'">Progress</th>
    </tr>
    </thead>
</table>

<script type="text/javascript">



     function e(data){
         console.log("竟来了")
         console.log("data: "+data)
        $(".selectItemCat").each(function(i,e){
            console.log("来了22")
            var _ele = $(e);
            if(data && data.cid){
                console.log("来了33")
                _ele.after("<span style='margin-left:10px;'>"+data.cid+"</span>");
            }else{
                _ele.after("<span style='margin-left:10px;'></span>");
            }
            _ele.unbind('click').click(function(){
                $("<div>").css({padding:"5px"}).html("<ul>")
                        .window({
                            width:'500',
                            height:"450",
                            modal:true,
                            closed:true,
                            iconCls:'icon-save',
                            title:'选择类目',
                            onOpen : function(){
                                var _win = this;
                                $("ul",_win).tree({
                                    url:'/goodsType/selectByType',
                                    animate:true,
                                    onClick : function(node){
                                        if($(this).tree("isLeaf",node.target)){
                                            // 填写到cid中
                                            _ele.parent().find("[name=cid]").val(node.id);
                                            _ele.next().text(node.text).attr("cid",node.id);
                                            $(_win).window('close');
                                            if(data && data.fun){
                                                data.fun.call(this,node);
                                            }
                                        }
                                    }
                                });
                            },
                            onClose : function(){
                                $(this).window("destroy");
                            }
                        }).window('open');
            });
        });
    }

    function myLoadFilter(data, parent){
        var state = $.data(this, 'tree');

        console.log("state"+state);
        console.log("data"+data.text);
        console.log("data"+data._parentId);
        console.log("data"+data.parentId);
        console.log("parent"+parent);

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

    function formatProgress(value){
        if (value){
            var s = '<div style="width:100%;border:1px solid #ccc">' +
                    '<div style="width:' + value + '%;background:#cc0000;color:#fff">' + value + '%' + '</div>'
            '</div>';
            return s;
        } else {
            return '';
        }
    }
    var editingId;
    function edit(){
        if (editingId != undefined){
            $('#tg').treegrid('select', editingId);
            return;
        }
        var row = $('#tg').treegrid('getSelected');
        if (row){
            editingId = row.id
            $('#tg').treegrid('beginEdit', editingId);
        }
    }
    function save(){
        if (editingId != undefined){
            var t = $('#tg');
            t.treegrid('endEdit', editingId);
            editingId = undefined;
            var persons = 0;
            var rows = t.treegrid('getChildren');
            for(var i=0; i<rows.length; i++){
                var p = parseInt(rows[i].persons);
                if (!isNaN(p)){
                    persons += p;
                }
            }
            var frow = t.treegrid('getFooterRows')[0];
            frow.persons = persons;
            t.treegrid('reloadFooter');
        }
    }
    function cancel(){
        console.log("cance:!! ")
        if (editingId != undefined){
        console.log("cance22:!! ")
            $('#tg').treegrid('cancelEdit', editingId);
            editingId = undefined;
        }
    }
</script>

</body>
</html>
