<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=path%>">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		* {
			        box-sizing: border-box;
		}
	</style>
	<link rel="stylesheet" type="text/css" href="<%=path %>/bower_components/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=path %>/bower_components/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="<%=path %>/bower_components/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/bower_components/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=path %>/bower_components/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<h2>Cell Editing in DataGrid</h2>
	<p>Click a cell to start editing.</p>
	<div style="margin:20px 0;"></div>
	
	<table id="dg" title="Cell Editing in DataGrid" style="width:700px;height:auto" class="easyui-datagrid" 
			data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				pagination:true,
				url: '<%=path %>/user.action?op=getUsers',
				method:'post'
			">
		<thead>
			<tr>
				<th data-options="field:'id',width:80">用户编号</th>
				<th data-options="field:'name',width:100,editor:'text'">用户姓名</th>
				<th data-options="field:'password',width:80,align:'right',editor:'text'">用户密码</th>
				<th data-options="field:'phone',width:80,align:'right',editor:'numberbox'">手机号码</th>
				<th data-options="field:'email',width:150,editor:'text'">邮箱</th>
				<th data-options="	field : '操作',
									width : 200,
									align : 'center',
									formatter : function(value, row, index) {
										return '<a href=\'javascript:alert('+row.id+')\' >删除</a>';
									}"
					>操作</th>
				
			</tr>
		</thead>
	</table>
		<script type="text/javascript">
		$.extend($.fn.datagrid.methods, {
			editCell: function(jq,param){
				return jq.each(function(){
					var opts = $(this).datagrid('options');
					var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor1 = col.editor;
						if (fields[i] != param.field){
							col.editor = null;
						}
					}
					$(this).datagrid('beginEdit', param.index);
                    var ed = $(this).datagrid('getEditor', param);
                    if (ed){
                        if ($(ed.target).hasClass('textbox-f')){
                            $(ed.target).textbox('textbox').focus();
                        } else {
                            $(ed.target).focus();
                        }
                    }
					for(var i=0; i<fields.length; i++){
						var col = $(this).datagrid('getColumnOption', fields[i]);
						col.editor = col.editor1;
					}
				});
			},
            enableCellEditing: function(jq){
                return jq.each(function(){
                    var dg = $(this);
                    var opts = dg.datagrid('options');
                    opts.oldOnClickCell = opts.onClickCell;
                    opts.onClickCell = function(index, field){
                        if (opts.editIndex != undefined){
                            if (dg.datagrid('validateRow', opts.editIndex)){
                                dg.datagrid('endEdit', opts.editIndex);
                                opts.editIndex = undefined;
                            } else {
                                return;
                            }
                        }
                        dg.datagrid('selectRow', index).datagrid('editCell', {
                            index: index,
                            field: field
                        });
                        opts.editIndex = index;
                        opts.oldOnClickCell.call(this, index, field);
                    }
                });
            }
		});

		$(function(){
			$('#dg').datagrid('enableCellEditing');
			var pager = $('#dg').datagrid().datagrid('getPager');	// get the pager of datagrid
			pager.pagination({
			});	
		})
	</script>
	
</body>
</html>