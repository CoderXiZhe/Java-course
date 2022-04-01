<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!--
		jsp 九大内置对象
			pageContext page
			request response
			session
			application config
	`		out
			exception

		el表达式为我们提供了N多个隐含对象
		只有XXXScope系列的隐含对象可以省略
		其他所有的隐含对象一概不能省略
  		EL表达式没有请求内置对象
-->
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>


<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

<script type="text/javascript">
	$(function(){
		$("#isCreateTransaction").click(function(){
			if(this.checked){
				$("#create-transaction2").show(200);
			}else{
				$("#create-transaction2").hide(200);
			}
		});

		$("#openSearchModalBtn").click(function () {

			$("#searchActivityModal").modal("show")


		})
		$("#aname").keydown(function(event){
			if(event.keyCode==13){

				$.ajax({
					url:"workbench/clue/getActivityList.do",
					data:{
						"aname":$.trim($("#aname").val())
					},
					type:"get",
					dataType:"json",
					success:function(data){
						/*
								data:
									[{},{},{}]
						 */
						var html=""
						$.each(data,function (i,e) {
							html+='<tr>';
							html+='<td><input value='+e.id+' type="radio" name="xz"/></td>';
							html+='<td id="'+e.id+'">'+e.name+'</td>';
							html+='<td>'+e.startDate+'</td>';
							html+='<td>'+e.endDate+'</td>';
							html+='<td>'+e.owner+'</td>';
							html+='</tr>';
						})
						$("#showActivityList").html(html)
					}

				})


				return false;
			}
		})

		//为提交市场活动 按钮绑定事件  填充市场活动源(填写名称和id)
		$("#submitActivityBtn").click(function () {
			//获取市场活动id
			var $xz = $("input[name=xz]:checked");
			var id = $xz.val();
			//获取市场活动名称
			var name = $("#"+id).html()

			//进行赋值
			$("#activityName").val(name);
			$("#activityId").val(id)

			//关闭模态窗口
			$("#searchActivityModal").modal("hide")
		})

		//为转换按钮绑定事件
		$("#convertBtn").click(function () {
			/*
					提交请求到后台 执行线索转换的操作 应该发出传统请求
					请求结束后 最终响应回线索的列表页

					根据”为客户创建交易“的复选框有没有打✓ 来判断是否需要创建交易
			 */

			if($("#isCreateTransaction").prop("checked")){
				//alert("需要创建交易")
				//需要创建交易  不仅需要id  还需要传递表单中的信息 金额 预计成交信息 交易名称 阶段 市场活动源(id)
				//window.location.href="workbench/clue/convert.do?clueId=${param.id}"
				//上述传递参数的方法很麻烦 一单表单扩充 挂在的参数有可能超出地址栏的上限
				//我们想到提交表单的形式来提交请求
				//提交表单不需要手动挂在   而且可以post请求  安全

				$("#tranForm").submit();//提交表单    reset要用dom对象
			}else{
				//alert("不需要创建交易")
				//不需要创建交易  只需要传递线索的id
				window.location.href="workbench/clue/convert.do?clueId=${param.id}"
			}
		})
	});
</script>

</head>
<body>
	
	<!-- 搜索市场活动的模态窗口 -->
	<div class="modal fade" id="searchActivityModal" role="dialog" >
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">搜索市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">
						  <div class="form-group has-feedback">
						    <input type="text"  id="aname" class="form-control" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询">
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
								<td></td>
							</tr>
						</thead>
						<tbody id="showActivityList">


						</tbody>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="submitActivityBtn">提交</button>
				</div>
			</div>
		</div>
	</div>

	<div id="title" class="page-header" style="position: relative; left: 20px;">
		<h4>转换线索 <small>${param.fullname}${param.appellation}-${param.company}</small></h4>
	</div>
	<div id="create-customer" style="position: relative; left: 40px; height: 35px;">
		新建客户：${param.company}
	</div>
	<div id="create-contact" style="position: relative; left: 40px; height: 35px;">
		新建联系人：${param.fullname}${param.appellation}
	</div>
	<div id="create-transaction1" style="position: relative; left: 40px; height: 35px; top: 25px;">
		<input type="checkbox" id="isCreateTransaction"/>
		为客户创建交易
	</div>
	<div id="create-transaction2" style="position: relative; left: 40px; top: 20px; width: 80%; background-color: #F7F7F7; display: none;" >



		<form id="tranForm" action="workbench/clue/convert.do" method="post">
			<input type="hidden" name="flag" value="a">  <!--只有提交表单 这个值才会传递到后台-->
			<input type="hidden" name="clueId" value="${param.id}">
		  <div class="form-group" style="width: 400px; position: relative; left: 20px;">
		    <label for="amountOfMoney">金额</label>
		    <input type="text" class="form-control" id="amountOfMoney" name="money">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="tradeName">交易名称</label>
		    <input type="text" class="form-control" id="tradeName" value="动力节点-" name="name">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="expectedClosingDate">预计成交日期</label>
		    <input type="text" class="form-control" id="expectedClosingDate" name="expectedDate">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="stage">阶段</label>
		    <select id="stage"  class="form-control" name="stage">
		    	<option></option>
		    	<c:forEach items="${stage}" var="s">
					<option value="${s.value}">${s.text}</option>
				</c:forEach>
		    </select>
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="activityName">市场活动源&nbsp;&nbsp;<a href="javascript:void(0);" id="openSearchModalBtn" style="text-decoration: none;"><span class="glyphicon glyphicon-search"></span></a></label>
		    <input type="text" class="form-control" id="activityName" placeholder="点击上面搜索" readonly>
			  <input type="hidden" id="activityId" name="activityId">
		  </div>
		</form>
		
	</div>
	
	<div id="owner" style="position: relative; left: 40px; height: 35px; top: 50px;">
		记录的所有者：<br>
		<b>${param.owner}</b>
	</div>
	<div id="operation" style="position: relative; left: 40px; height: 35px; top: 100px;">
		<input class="btn btn-primary" id="convertBtn" type="button" value="转换">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="btn btn-default" type="button" value="取消">
	</div>
</body>
</html>