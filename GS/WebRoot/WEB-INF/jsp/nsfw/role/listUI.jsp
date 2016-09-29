<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>角色管理</title>
    <script type="text/javascript">
    	var list_url = "${bathPath}role_listUI.action";
    	//搜索用户
     	function doSearch(){
     		//重置页号
     		$("#pageNo").val(1);
     		document.forms[0].action = list_url;
     		document.forms[0].submit();
     	}
    	//新增角色
    	function doAdd(){
    		document.forms[0].action="${basePath}nsfw/role_addUI.action";
    		document.forms[0].submit();
    	}
    	//删除选中的角色
    	function doDeleteSelected(){
    		document.forms[0].action="${basePath}nsfw/role_deleteSelected.action";
    		document.forms[0].submit();
    	}
    	
    	//全选/反选角色
    	function doSelectAll(){
    		//获取全选/反选按钮是否选中的状态
    		var flag = $("#selAll").is(":checked");
    		//保持其他的角色选中按钮和全选按钮状态一致
    		$("[name='selectedRow']").prop("checked",flag);
    	}
    	
    	//编辑该角色
    	function doEdit(roleId){
    		document.forms[0].action="${basePath}nsfw/role_editUI.action?role.roleId="+roleId;
    		document.forms[0].submit();
    	}
    	
    	//删除该角色
    	function doDelete(roleId){
    		document.forms[0].action="${basePath}nsfw/role_delete.action?role.roleId="+roleId;
    		document.forms[0].submit();
    	}
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>角色管理 </strong></div> </div>
                <div class="search_art">
                    <li>
                        角色名称：<s:textfield name="keyword" cssClass="s_text" id="roleName"  cssStyle="width:160px;"/>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteSelected()"/>&nbsp;
                    </li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td width="120" align="center">角色名称</td>
                            <td align="center">权限</td>
                            <td width="80" align="center">状态</td>
                            <td width="120" align="center">操作</td>
                        </tr>
                       		<s:iterator value="pr.items" status="st">
                            <tr <s:if test="#st.odd">bgcolor="f8f8f8"</s:if> >
                                <td align="center"><input type="checkbox" name="selectedRow" value="<s:property value='roleId'/>"/></td>
                                <td align="center"><s:property value="name"/></td>
                                <td align="center">
                                	<s:iterator value="rolePrivileges">
                                		<s:property value="privilegeMap[rolePrivilegeId.code]"/>
                                	</s:iterator>	
                                </td>
                                <td align="center"><s:property value="state==1?'有效':'无效'"/></td>
                                <td align="center">
                                    <a href="javascript:doEdit('<s:property value='roleId'/>')">编辑</a>
                                    <a href="javascript:doDelete('<s:property value='roleId'/>')">删除</a>
                                </td>
                            </tr>
                           </s:iterator>
                    </table>
                </div>
            </div>
			<!-- 此处插入页码 -->
        	<%@include file="/common/page.jsp" %>
        </div>
    </div>
</form>

</body>
</html>