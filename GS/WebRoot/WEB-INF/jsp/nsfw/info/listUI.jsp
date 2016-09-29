<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>信息发布管理</title>
    <script type="text/javascript">
    	var list_url = "${bathPath}info_listUI.action";
    	//搜索用户
     	function doSearch(){
     		//重置页号
     		$("#pageNo").val(1);
     		document.forms[0].action = list_url;
     		document.forms[0].submit();
     	}
        
        //新增
        function doAdd(){
        	document.forms[0].action = "${basePath}nsfw/info_addUI.action";
        	document.forms[0].submit();
        }
        //删除选中
        function doDeleteAll(){
        	document.forms[0].action = "${basePath}nsfw/info_deleteSelected.action";
        	document.forms[0].submit();
        }
        
        //全选/反选所有
        function doSelectAll(){
        	//定位该按钮，获取状态        
        	//定位所有行checkbox,使与该按钮状态一致
        	$(":checkbox[name='selectedRow']").prop("checked",$("#selAll").is(":checked"));
        }
        
        //发布/停用信息
        function doPublic(infoId,state){
        	$.ajax({
        		url:"${basePath}nsfw/info_publicInfo.action?time="+new Date().getTime(),
        		data:{"info.infoId":infoId,"info.state":state},
        		type:"post",
        		async:false,
        		success:function(backData){    			
        			if(backData.msg == "success"){
	        			//修改状态和操作的显示
	        			if(state == 1){
	        				$("#show_"+infoId).html("发布");
	        				$("#oper_"+infoId).html('<a href="javascript:doPublic(\''+infoId+'\',0)">停用</a>');
	        			}else{
	        				$("#show_"+infoId).html("停用");
	        				$("#oper_"+infoId).html('<a href="javascript:doPublic(\''+infoId+'\',1)">发布</a>');
	        			} 			
        			}
        		}
        	});
        }
        
        //跳转编辑页面
        function doEdit(infoId){
        	document.forms[0].action = "${basePath}nsfw/info_editUI.action?info.infoId="+infoId;
        	document.forms[0].submit();
        }
        
        //删除当前行
        function doDelete(infoId){
        	document.forms[0].action = "${basePath}nsfw/info_delete.action?info.infoId="+infoId;
        	document.forms[0].submit();
        }
    </script>
</head>
<body class="rightBody">
<form name="form1" action="" method="post">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs"><div><b></b><strong>信息发布管理</strong></div> </div>
                <div class="search_art">
                    <li>
                        信息标题：<s:textfield name="keyword" cssClass="s_text" id="infoTitle"  cssStyle="width:160px;"/>
                    </li>
                    <li><input type="button" class="s_button" value="搜 索" onclick="doSearch()"/></li>
                    <li style="float:right;">
                        <input type="button" value="新增" class="s_button" onclick="doAdd()"/>&nbsp;
                        <input type="button" value="删除" class="s_button" onclick="doDeleteAll()"/>&nbsp;
                    </li>
                </div>

                <div class="t_list" style="margin:0px; border:0px none;">
                    <table width="100%" border="0">
                        <tr class="t_tit">
                            <td width="30" align="center"><input type="checkbox" id="selAll" onclick="doSelectAll()" /></td>
                            <td align="center">信息标题</td>
                            <td width="120" align="center">信息分类</td>
                            <td width="120" align="center">创建人</td>
                            <td width="140" align="center">创建时间</td>
                            <td width="80" align="center">状态</td>
                            <td width="120" align="center">操作</td>
                        </tr>
                        <s:iterator value="pr.items" status="st">
                            <tr <s:if test="#st.odd"> bgcolor="f8f8f8" </s:if> >
                                <td align="center"><input type="checkbox" name="selectedRow" value="<s:property value='infoId'/>"/></td>
                                <td align="center"><s:property value="title"/></td>
                                <td align="center">
                                	<s:property value="infoTypeMap[type]"/>	
                                </td>
                                <td align="center"><s:property value="creater"/></td>
                                <td align="center"><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
                                <td align="center" id="show_<s:property value='infoId'/>"><s:property value="state==1?'发布':'停用'"/></td>
                                <td align="center">
                                	<span id="oper_<s:property value='infoId'/>">
                                		<s:if test="state==1">
	                                		<a href="javascript:doPublic('<s:property value='infoId'/>',0)">停用</a>                                		
                                		</s:if>
                                		<s:else>
                                			<a href="javascript:doPublic('<s:property value='infoId'/>',1)">发布</a>                                		
                                		</s:else>                                	
                                	</span>
                                    <a href="javascript:doEdit('<s:property value='infoId'/>')">编辑</a>
                                    <a href="javascript:doDelete('<s:property value='infoId'/>')">删除</a>
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