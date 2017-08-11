<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<h1>Update管理</h1>

<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>ID</th>
			<th>包名</th>
			<th>版本名</th>
			<th>版本号</th>
			<th>下载路径</th>			
			<th>更新次数</th>
			<th>渠道</th>
			<th>上线</th>
			<th>更新日期</th>								
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.packageName" /></td>
				<td><s:property value="#val.versionName" /></td>
				<td><s:property value="#val.versionCode" /></td>
				<td><s:property value="#val.downloadPath" /></td>
				<td><s:property value="#val.updateNum" /></td>
				<td><s:property value="#val.channel" /></td>
				<td align="center">				
				<s:if test="#val.online == true"><img src="images/user-online.png" /></s:if>
				<s:else><img src="images/user-offline.png" /></s:else>			
				</td>
				
				<td align="center"><s:date name="#val.updatedDate" format="yyyy-MM-dd HH:mm:ss" /></td>				
				<td class="thUpdate"><input type="button" value="操作"/></td>
			</tr>
		</s:iterator>
	</tbody>
</table>

<div id="my_div" title="${maxNum}">

<a id="a_1" href="#" > 上一页    </a>
<a id="a_2" href="#"> 下一页</a>

<a  herf="#">总记录数：${maxNum}</a>
</div>

<table  cellspacing="1">
	<tr>			
		<th>新增：</th>
		<th> <input type="button" id="addUpdate" value="新增" /></th>		
	</tr>		
</table>

<center id="d_addUpdate" style="display: <s:if test="#request.addUpdate == null">none</s:if>">
<h1>添加</h1>
	<form action="update_addUpdate" method="post" class="g_from" enctype="multipart/form-data" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr>
				<td>apk路径:</td>
				<td><input type="file" id="apk" name="apk" value="浏览" style="width:180px;" /></td>
			</tr>
			<tr >
				<td>渠道:</td>
				<td><input type="text" id="channel" name="channel"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>是否上线:</td>
				<td width="80%"><input type="radio" id="online_state1"
					name="online_state" value="1" checked="checked" /> 是 <input
					type="radio" id="online_state2" name="online_state" value="0" /> 否</td>
			</tr>
		
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="添加" />
				 ${requestScope.addUpdate }</td>
			</tr>
		</table>
	</form>
</center>

<center id="f_update" style="display: <s:if test="#request.updateUpdate == null">none</s:if>">
<h1>更改</h1>
	<form action="update_updateUpdate" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>ID:</td>
				<td><input type="text" id="f_id" name="id"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>是否上线:</td>
				<td width="80%"><input type="radio" id="up_online_state1"
					name="online_state" value="1" checked="checked" /> 是 <input
					type="radio" id="up_online_state2" name="online_state" value="0" /> 否</td>
			</tr>
			
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="更改" />
				  ${requestScope.updateUpdate }</td>
			</tr>
		</table>
	</form>
</center>

<div id="div_update" style="display:none;position:absolute;width:100px;">
<table  class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>操作</th>
		</tr>
	</thead>		
	<tr><td><input type="button" value="更改" id="find"/></td></tr>
	<tr><td><input type="button" value="删除" id="delete"/></td></tr>
</table>
</div>

<script type="text/javascript">

$("#find").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>update_findUpdate?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	
	$("#f_id").val(jsonobj.id);
	$("#f_whiteList").val(jsonobj.whiteList);
	$("#f_blackList").val(jsonobj.blackList);
	$("#f_grayList").val(jsonobj.grayList);
	if (jsonobj.online) {
		$("#up_online_state1").attr("checked", "checked");
		$("#up_online_state2").attr("checked", "");
	} else {
		$("#up_online_state2").attr("checked", "checked");
		$("#up_online_state1").attr("checked", "");
	}
	if (jsonobj.stopRun) {
		$("#up_stopRun_state1").attr("checked", "checked");
		$("#up_stopRun_state2").attr("checked", "");
	} else {
		$("#up_stopRun_state2").attr("checked", "checked");
		$("#up_stopRun_state1").attr("checked", "");
	}
	
	
	$("#d_addUpdate").hide();
	$("#f_update").show();
	$("#div_update").hide();
});


$("#addUpdate").click(function(){
	var d_addUpdate = $("#d_addUpdate");
	var f_update = $("#f_update");
	if(d_addUpdate.css("display") == "none")
	{
		d_addUpdate.css("display","");
		f_update.css("display","none");
	}
	else
	{
		d_addUpdate.css("display","none");
	}
});

$(function() {
	$('#tableList').tablesorter();
	$('table tr:nth-child(even)').addClass('even');	 
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>update_deleteUpdate?data=";
	urll = urll + data;
	$.ajax({url:urll,async:false});
	$("#div_update").hide();
	location.href = "<%out.print(basePath); %>update_list";
	
});

$(".thUpdate").click(function(){	
	var x = $(this).offset().top; 
	var y = $(this).offset().left - 100; 
	var div = $("#div_update");
	div.css("left",y + "px"); 
	div.css("top",x + "px");
	var preall = $(this).prevAll();
	var id = preall[preall.length-1].innerHTML;
	
	div.attr("title",id);
	div.show();
});

$("html").mousedown(function(e){
	var div = $("#div_update");
	
	if(div.css('display') != "none")
	{
		var w = div.width();
		var h = div.height();
		
		var left =  div.offset().left;
		var top = div.offset().top;
		if(e.pageX <= left+w && e.pageX >= left && e.pageY >= top && e.pageY <= top + h)
		{
			return;			
		}
		else
		{
			div.hide();
		}
	}
});

var div = document.getElementById("my_div");
var a_1 = document.getElementById("a_1");
var a_2 = document.getElementById("a_2");

var resf = function()
{
var maxNum = div.title;
var maxIndex = Math.ceil(maxNum / 100)-1;
var index = location.href.split("=")[1];

if(!index || index > maxIndex)
index = 0;

if(index == 0)
{
	a_1.style.display = "none";
}
else
{
	a_1.style.display = "";
}
if(index >= maxIndex )
{
	a_2.style.display = "none";
}
else
{
	a_2.style.display = "";
}

a_1.href = "update_list?index=" + (parseInt(index)-1);
a_2.href = "update_list?index=" + (parseInt(index)+1);	
}

resf();
</script>
