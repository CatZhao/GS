<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
    <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
    	//定义变量result，如果通过帐号验证，则给result赋值为true
    	var result = false;
   		
    	//账户名改变时，局部刷新向服务器请求数据（账户是否可用，可用返回true,不可用返回false）
    	function checkAccount(){
    		var account = $("#account").val().trim();
    		if(account != ""){
	    		$.ajax({
		    		url:"${basePath }nsfw/user_checkAccount.action",
		    		data:{"user.account":account},
		    		type:"post",
		    		//开启同步模式，执行此函数时页面处于假死状态，必须执行完此函数才能继续执行下一行
		    		async:true,
		    		success:function(backData){
		    			//服务器返回的结果不是true，则account用户名不可用，禁止注册
		    			if(backData.msg != true){
		    				alert("帐号已存在，请重试");
		    				//为了用户体验，把光标定位在account输入框
		    				$("#account").blur();
		    				result = false;
		    			}else{
		    				result = true;
		    			}
		    		}
	    		});
    		}else{
    			alert("账户必须填写");
    		}
    	}
    	
   		//提交表单时，验证用户名、密码已经填写，账户名可用，才允许提交表单
    	function checkAll(){
    		var password = $("#password").val().trim();
    		
    		if(password == ""){
    			alert("密码必须填写");  
    			return false;  			
    		}
    		var name = $("#name").val().trim();
    		if(name ==""){
    			alert("用户名必须填写");
    			return false;
    		}
    		checkAccount();
    		if(result){
    			return true;	
    		}
    		return false;
    	}
    	
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" onsubmit="return checkAll()" action="${basePath }nsfw/user_add.action" method="post" 
		enctype="multipart/form-data"  >
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
    <div class="tableH2">新增用户</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
        <tr>
            <td class="tdBg" width="200px">所属部门：</td>
            <td><s:select name="user.dept" list="#{'部门A':'部门A','部门B':'部门B' }"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
                <input type="file" name="image"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><s:textfield name="user.name" id="name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td><s:textfield name="user.account" id="account" onchange="checkAccount()"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><s:textfield name="user.password" id="password"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td><s:radio list="#{'true':'男','false':'女'}" name="user.gender"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">角色：</td>
            <td>
            	<s:checkboxlist list="roleList" listKey="roleId" listValue="name" name="roleIds"></s:checkboxlist>
            </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><s:textfield name="user.email"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><s:textfield name="user.tel"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">生日：</td>
            <td><s:textfield id="birthday" name="user.birthday" readonly="true" onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd'});" /></td>
        </tr>
		<tr>
            <td class="tdBg" width="200px">状态：</td>
            <td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state" value="1"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">备注：</td>
            <td><s:textarea name="user.memo" cols="75" rows="3"/></td>
        </tr>
    </table>
    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>