<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户管理</title>
    <%@include file="/common/header.jsp" %>
    <script type="text/javascript">
    	var list_url = "${bathPath}user_listUI.action";
    	//搜索用户
     	function doSearch(){
     		//重置页号
     		$("#pageNo").val(1);
     		document.forms[0].action = list_url;
     		document.forms[0].submit();
     	}
     	
     	//增加用户
     	function doAdd(){
     		document.forms[0].action = "${bathPath}user_addUI.action";
     		document.forms[0].submit();
     	}
     	
     	//编辑用户
     	function doEdit(id){
     		document.forms[0].action = "${bathPath}user_editUI.action?user.id="+id;
     		document.forms[0].submit();
     	}
     	
     	//根据id删除用户
     	function doDelete(id){
     		document.forms[0].action = "${bathPath}user_delete.action?user.id="+id;
     		document.forms[0].submit();
     	}
     	
     	//删除选中用户
     	function doDeleteSelected(){
     		/* $(":checkbox[name='selectedRow']:checked").each(function(){
     			alert(this.value);
     		}); */
     		document.forms[0].action = "${bathPath}user_deleteSelected.action";
     		document.forms[0].submit();
     	}
     	
     	//导出所有用户,直接打开新窗口，下载数据即可，不需要向后台发送数据
     	function doExportExcel(){
     		window.open("${bathPath}user_exportExcel.action");
     		
     	}
     	
     	//用excel表格导入用户，把form中的userExcel文件传到后台，读取数据，调用add方法保存到数据库，再重定向到页面
     	function doImportExcel(){
     		document.forms[0].action = "${bathPath}user_importExcel.action";
     		document.forms[0].submit();
     	}
     	
     	//选中所有用户
     	function doSelectAll(){
     		$(":checkbox[name='selectedRow']").prop("checked",$("#selAll").is(":checked"));
     		
     	}
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>用户管理</strong></div> </div>
                <div class="search_art">
                
                <!-- ********用户搜索******** -->
                    <li>
                        用户名：<s:textfield name="keyword" cssClass="s_text" id="userName"  cssStyle="width:160px;"/>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteSelected()"/>&nbsp;
                        <input type="button" value="导出" class="s_button" onclick="doExportExcel()"/>&nbsp;                    	
                    	<input name="userExcel" type="file"/>
                        <input type="button" value="导入" class="s_button" onclick="doImportExcel()"/>&nbsp;

                    </li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td width="140" align="center">用户名</td>
                            <td width="140" align="center">帐号</td>
                            <td width="160" align="center">所属部门</td>
                            <td width="80" align="center">性别</td>
                            <td align="center">电子邮箱</td>
                            <td width="100" align="center">操作</td>
                        </tr>
                        <s:iterator value="pr.items" status="st">
                            <tr <s:if test="#st.odd">bgcolor="f8f8f8"</s:if> >
                                <td align="center"><input type="checkbox" name="selectedRow" value="<s:property value='id'/>" /></td>
                                <td align="center"><s:property value="name"/></td>
                                <td align="center"><s:property value="account"/></td>
                                <td align="center"><s:property value="dept"/></td>
                                <td align="center"><s:property value="gender?'男':'女'"/></td>
                                <td align="center"><s:property value="email"/></td>
                                <td align="center">
                                    <a href="javascript:doEdit('<s:property value='id'/>')">编辑</a>
                                    <a href="javascript:doDelete('<s:property value='id'/>')">删除</a>
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