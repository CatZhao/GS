<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	
	//当前页尾第一页或最后一页时，不提交请求
	function getPage(pageNo){
		//获取最大页数
		var maxPage = $("#maxPage").val();
		pageNo = (pageNo > maxPage) ? maxPage : pageNo;
		pageNo = (pageNo < 1) ? 1 : pageNo;
		document.getElementById("pageNo").value = pageNo;
		document.forms[0].action = list_url;
		document.forms[0].submit();
		
	}
</script>
	<div class="c_pate" style="margin-top: 5px;">
		<table width="100%" class="pageDown" border="0" cellspacing="0"	cellpadding="0">
			<tr>
				<td align="right">
                 	总共<s:property value="pr.totalCount"/>条记录，
                 	当前第 <s:property value="pr.pageNo"/> 页，
                 	共  <s:property value="pr.totalPage"/> 页 &nbsp;&nbsp;
                 	<s:hidden id="maxPage" value="%{pr.totalPage}"></s:hidden>
                    <a href="javascript:getPage(<s:property value="pr.pageNo-1"/>)">上一页</a>&nbsp;&nbsp;
                    <a href="javascript:getPage(<s:property value="pr.pageNo+1"/>)">下一页</a>
					到&nbsp;
					<input type="text" id="pageNo" name="pageNo" style="width: 30px;" onkeypress="if(event.keyCode == 13){getPage(this.value);}" 
						value="<s:property value='pr.pageNo'/>"/> &nbsp;&nbsp;
			    </td>
			</tr>
		</table>	
      </div>
