<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<h1>OFFER管理</h1>

<table id="tableList" class="tablesorter" cellspacing="1">
	<thead>
		<tr>			
			<th>ID</th>
			<th>应用名称</th>
			<th>应用包名</th>
			<th>GP链接</th>
			<th>应用打开次数</th>
			<th>广告展示次数</th>
			<th>打开间隔</th>
			<th>展示间隔</th>
			<th>优先级</th>
			<th>渠道</th>
			<th>打开统计</th>
			<th>展示统计</th>
			<th>安装统计</th>
			<th>入口</th>
			<th>上线</th>
			<th>创建日期</th>								
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="list" var="val">
			<tr>				
				<td><s:property value="#val.id" /></td>
				<td><s:property value="#val.appName" /></td>
				<td><s:property value="#val.packageName" /></td>
				<td><s:property value="#val.gpUrl" /></td>
				<td><s:property value="#val.openNum" /></td>
				<td><s:property value="#val.showNum" /></td>
				<td><s:property value="#val.openTimeInterval" /> 小时</td>
				<td><s:property value="#val.showTimeInterval" /> 小时</td>				
				<td><s:property value="#val.priority" /></td>
				<td><s:property value="#val.channels" /></td>
				<td><s:property value="#val.staOpenNum" /></td>
				<td><s:property value="#val.staShowNum" /></td>
				<td><s:property value="#val.staInstallNum" /></td>
				<td><s:property value="#val.activityName" /></td>
				<td>				
				<s:if test="#val.online == true"> <img src="images/user-online.png" />	</s:if>
				<s:else>  <img src="images/user-offline.png" /> </s:else>		
				</td>
				<td align="center"><s:date name="#val.created" format="yyyy-MM-dd HH:mm:ss" /></td>				
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
		<th>新增OFFER：</th>
		<th> <input type="button" id="addOffer" value="新增" /></th>		
	</tr>		
</table>

<center id="d_addoffer" style="display: <s:if test="#request.addOffer == null">none</s:if>">
<h1>添加OFFER</h1>
	<form action="offer_addOffer" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>是否上线:</td>
				<td width="80%"><input type="radio" id="online_state1"
					name="online" value="1" checked="checked" /> 是 <input
					type="radio" id="online_state2" name="online" value="0" /> 否</td>
			</tr>
			
			<tr >
				<td>应用名字:</td>
				<td><input type="text" id="appName" name="appName"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>包名:</td>
				<td><input type="text" id="packageName" name="packageName"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr>
				<td>GP链接:</td>
				<td><input type="text" id="gpUrl" name="gpUrl" value="" style="width:180px;" /></td>
			</tr>
			<tr >
				<td>打开次数:</td>
				<td><input type="text" id="openNum" name="openNum"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>展示次数:</td>
				<td><input type="text" id="showNum" name="showNum"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>打开时间间隔:</td>
				<td><input type="text" id="openTimeInterval" name="openTimeInterval"
					value="" style="width:180px;" />小时
				</td>
			</tr>
			<tr >
				<td>展示时间间隔:</td>
				<td><input type="text" id="showTimeInterval" name="showTimeInterval"
					value="" style="width:180px;" />小时
				</td>
			</tr>
			<tr >
				<td>渠道:</td>
				<td><input type="text" id="channels" name="channels"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>优先级:</td>
				<td><input type="text" id="priority" name="priority"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>入口:</td>
				<td><input type="text" id="activityName" name="activityName"
					value="" style="width:180px;" />
				</td>
			</tr>
	
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="添加" />
				 ${requestScope.addOffer }</td>
			</tr>
		</table>
	</form>
</center>

<center id="f_update" style="display: <s:if test="#request.updateOffer == null">none</s:if>">
<h1>更改OFFER</h1>
	<form action="offer_updateOffer" method="post" class="g_from" style="margin-left: auto;margin-right: auto;">
		<table  cellpadding="4" cellspacing="0" border="0">
			
			<tr >
				<td>ID:</td>
				<td><input type="text" id="f_id" name="id" data-channel=""
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>是否上线:</td>
				<td width="80%"><input type="radio" id="up_online_state1"
					name="online" value="1" checked="checked" /> 是 <input
					type="radio" id="up_online_state2" name="online" value="0" /> 否</td>
			</tr>
			

			<tr >
				<td>应用名字:</td>
				<td><input type="text" id="update_appName" name="appName"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr >
				<td>包名:</td>
				<td><input type="text" id="update_packageName" name="packageName"
					value="" style="width:180px;" />
				</td>
			</tr>
			
			<tr>
				<td>GP链接:</td>
				<td><input type="text" id="update_gpUrl" name="gpUrl" value="" style="width:180px;" /></td>
			</tr>
			<tr >
				<td>打开次数:</td>
				<td><input type="text" id="update_openNum" name="openNum"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>展示次数:</td>
				<td><input type="text" id="update_showNum" name="showNum"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>打开时间间隔:</td>
				<td><input type="text" id="update_openTimeInterval" name="openTimeInterval"
					value="" style="width:180px;" />小时
				</td>
			</tr>
			<tr >
				<td>展示时间间隔:</td>
				<td><input type="text" id="update_showTimeInterval" name="showTimeInterval"
					value="" style="width:180px;" />小时
				</td>
			</tr>
			<tr >
				<td>渠道:</td>
				<td><input type="text" id="update_channels" name="channels"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>优先级:</td>
				<td><input type="text" id="update_priority" name="priority"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>入口:</td>
				<td><input type="text" id="update_activityName" name="activityName"
					value="" style="width:180px;" />
				</td>
			</tr>
			<tr >
				<td>安装次数:</td>
				<td><input type="text" id="update_staInstallNum" name="staInstallNum"
					value="" style="width:180px;" />
				</td>
			</tr>
						
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="更改" />
				  ${requestScope.updateOffer }</td>
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
	
	var urll = "<%out.print(basePath); %>offer_findOffer?data=";
	urll = urll + data;
	var res = $.ajax({url:urll,async:false});
	var obj = res.responseText;
	var jsonobj = eval("("+obj+")");
	
	$("#f_id").val(jsonobj.id);
		
	if (jsonobj.online) {
		$("#up_online_state1").attr("checked", "checked");
		$("#up_online_state2").attr("checked", "");
	} else {
		$("#up_online_state2").attr("checked", "checked");
		$("#up_online_state1").attr("checked", "");
	}
	
	$("#update_appName").val(jsonobj.appName);
	$("#update_packageName").val(jsonobj.packageName);
	$("#update_gpUrl").val(jsonobj.gpUrl);
	$("#update_channels").val(jsonobj.channels);
	$("#update_openNum").val(jsonobj.openNum);
	$("#update_showNum").val(jsonobj.showNum);
	$("#update_openTimeInterval").val(jsonobj.openTimeInterval);
	$("#update_showTimeInterval").val(jsonobj.showTimeInterval);
	$("#update_priority").val(jsonobj.priority);
	$("#update_activityName").val(jsonobj.activityName);
	$("#update_staInstallNum").val(jsonobj.staInstallNum);
	

	$("#d_addoffer").hide();
	$("#f_update").show();
	$("#div_update").hide();
});


$("#addOffer").click(function(){
	var d_addoffer = $("#d_addoffer");
	var f_update = $("#f_update");
	if(d_addoffer.css("display") == "none")
	{
		d_addoffer.css("display","");
		f_update.css("display","none");
	}
	else
	{
		d_addoffer.css("display","none");
	}
});

$(function() {
	$('#tableList').tablesorter();
	$('table tr:nth-child(even)').addClass('even');	 
});

$("#delete").click(function()
{
	var data = $("#div_update").attr("title");
	
	var urll = "<%out.print(basePath); %>offer_deleteOffer?data=";
	urll = urll + data;
	$.ajax({url:urll,async:false});
	$("#div_update").hide();
	location.reload();
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

a_1.href = "offer_list?index=" + (parseInt(index)-1);
a_2.href = "offer_list?index=" + (parseInt(index)+1);	
}

resf();
</script>
