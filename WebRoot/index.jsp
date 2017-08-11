<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>光广告</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/console.css" />
<script type="text/javascript" src="<%=basePath%>scripts/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>styles/tablesorter/style.css" />
<script type="text/javascript" src="<%=basePath%>scripts/jquery.tablesorter.js"></script>
  </head>
  
  <body>
  <div id="page">
		<div id="header">
			<jsp:include page="/includes/header.jsp" />
		</div>
		<div id="div_session" style="position:absolute; left:90%; margin-top: -30px;">
			<s:if test="#session.user != null">
			<a style="color: red; font-size: 12px;">
			 <s:property value="#session.user.name" /></a>
			<a style="color: red; font-size: 12px;" href="<%=basePath%>user_loginout"> 退出</a>
			</s:if>
		</div>		
		<div id="content">
			<ul id="tabmenu">
				<li><a href="<%=basePath%>"
					class="<s:if test="#request.pages == null">current</s:if>" >主页</a></li>
				
				<s:if test="#session.user != null">
									
					<s:if test="#session.user.permission.model_user == true">
						<li><a href="user_list"
							class="<s:if test="#request.pages == 'user'">current</s:if>" >用户</a>
						</li>
					</s:if>
					
					<s:if test="#session.user.permission.model_user == true">
						<li><a href="update_list"
							class="<s:if test="#request.pages == 'update'">current</s:if>" >Update</a>
						</li>
					</s:if>
					
					<s:if test="#session.user.permission.model_user == true">
						<li><a href="sdk_list"
						class="<s:if test="#request.pages == 'sdk'">current</s:if>">SDK管理</a>
						</li>
					</s:if>
					
				</s:if>
			</ul>
			<div id="tabcontent">
			
				<s:if test="#request.pages == null">
				
					<s:if test="#session.user == null">
						<jsp:include page="/pages/login.jsp" />
					</s:if>
					<s:else>
						<jsp:include page="/pages/home.jsp" />
					</s:else>
					
				</s:if>
				
				<s:else>
				
					<s:if test="#session.user == null">
						<jsp:include page="/pages/login.jsp" />
					</s:if>
					
					<s:else>
					
						<s:if test="#request.pages == 'user'">
							<s:if test="#session.user.permission.model_user == true">
								<jsp:include page="/pages/${requestScope.pages }.jsp" />
							</s:if>
						</s:if>
						
						<s:else>
							<jsp:include page="/pages/${requestScope.pages }.jsp" />
						</s:else>
						
					</s:else>				
				</s:else>
			</div>
		</div>
		<div id="footer">
			<jsp:include page="/includes/footer.jsp" />
		</div>
	</div>
</body>
</html>
