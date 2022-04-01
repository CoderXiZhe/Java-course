<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet"/>

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>

<script type="text/javascript">


	/*
			正则表达式：
				1，语言，语法：定义字符串的匹配模式，可以用来判断指定的具体字符串是否符合匹配模式。
				2,语法通则：
				1)//:在js中定义一个正则表达式。 	var regExp=/...../;
				2）^：匹配字符串的开头位置  	$：匹配字符串的结尾
				3）[]：匹配指定字符集中的一位字符。
					var regExp=/^[abc]$/;
					var regExp=/^[a-z0-9]$/;
				4）{}:匹配次数.		var regExp=/^[abc]{5}$/;
					{m}:匹配m次
					{m,n}: 匹配m次到n次
					{m,}:匹配m次或更多次
				5)特殊符号
					\d:匹配一位数字，相当于[0-9]
					\D：匹配一位非数字
					\W：匹配所有字符，包括字母、数字、下划线。
					\W:匹配非字符，除了字母、数字、下划线之外的字符。
					*:匹配0次或者多次，相当于{0,}
					+:匹配1次或者多次，相当于{1,}
					?:匹配0次或者1次，相当于{O,1}
	 */
	//外键字段不为空 预先考虑内连接 效率高  空就是外连接
	$(function(){

		$(".time").datetimepicker({
			format: 'yyyy-mm-dd',
			minView: "month",//选择最小的视图
			language:  'zh-CN',
			autoclose: true,  //选完自动关闭
			todayBtn: true,
			clearBtn:true,
		})

		$("#createBtn").click(function () {
			//初始化操作
			$("#createActivityForm")[0].reset()//拿到表单的dom对象 进行重置
		})
		$("#createActivityBtn").click(function () {
			//获取参数
			var owner = $("#create-owner").val();
			var name = $.trim($("#create-name").val());
			var startDate = $("#create-startDate").val();
			var endDate = $("#create-endDate").val();
			var cost =$.trim($("#create-cost").val());
			var description = $.trim($("#create-description").val());
			if(name==""){
				alert("名称不能为空!")
				return;
			}
			if(startDate!="" && endDate!=""){
				if(startDate>endDate){
					alert("结束日期不能小于开始日期!")
					return;
				}
			}
			var regExp = /^(([1-9]\d*)|0)$/
			if(!regExp.test(cost)){
				alert("成本只能为非负整数")
			}
			$.ajax({
				url:"workbench/activity/saveCreateActivity.do",
				data:{
					owner:owner,
					name:name,
					startDate:startDate,
					endDate:endDate,
					cost:cost,
					description:description
				},
				type:"post",
				dataType:"json",
				success:function (data) {
					if(data.code=="1"){
						alert("创建成功！")
						$("#createActivityModal").modal("hide")
						//刷新市场活动列，显示第一页数据，保持每页显示条数不变
						pagination(1,10)
					}else{
						alert(data.message)
					}
				}
			})
		})

		//页面刷新后 展示记录
		pagination(1,10)
		//为查询按钮 绑定事件
		$("#queryBtn").click(function () {
			//将查询信息放入隐藏域
			$("#hidden-name").val($("#query-name").val())
			$("#hidden-owner").val($("#query-owner").val())
			$("#hidden-startDate").val($("#query-startDate").val())
			$("#hidden-endDate").val($("#query-endDate").val())
			pagination(1,10);
		})

		function pagination(pageNo,pageSize){
			$("#qx").prop("checked",false)
			//获取参数
			$("#query-name").val($("#hidden-name").val())
			$("#query-owner").val($("#hidden-owner").val())
			$("#query-startDate").val($("#hidden-startDate").val())
			$("#query-endDateDate").val($("#hidden-endDate").val())

			var name=$.trim($("#query-name").val())
			var owner=$.trim($("#query-owner").val())
			var startDate=$("#query-startDate").val()
			var endDate=$("#query-endDate").val()
			var htmlStr=""
			$.ajax({
				url:"workbench/activity/pagination.do",
				data:{
					name:name,
					owner:owner,
					startDate:startDate,
					endDate:endDate,
					pageNo:pageNo,
					pageSize:pageSize
				},
				type:"post",
				dataType:"json",
				success:function (data) {
					$.each(data.activityList,function (i,e) {
						htmlStr+='<tr class="active">'
						htmlStr+='<td><input type="checkbox" name="xz" value="'+e.id+'"/></td>'
						htmlStr+='<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'detail.html\';">'+e.name+'</a></td>'
						htmlStr+='<td>'+e.owner+'</td>'
						htmlStr+='<td>'+e.startDate+'</td>'
						htmlStr+='<td>'+e.endDate+'</td>'
						htmlStr+='</tr>'
					})
					$("#tBody").html(htmlStr)
					var totalPages = data.totalRows%pageSize==0?data.totalRows/pageSize:parseInt(data.totalRows/pageSize)+1;
					$("#activityPage").bs_pagination({
						currentPage: pageNo, // 页码
						rowsPerPage: pageSize, // 每页显示的记录条数
						maxRowsPerPage: 20, // 每页最多显示的记录条数
						totalPages: totalPages, // 总页数
						totalRows: data.totalRows, // 总记录条数

						visiblePageLinks: 3, // 显示几个卡片

						showGoToPage: true,
						showRowsPerPage: true,
						showRowsInfo: true,
						showRowsDefaultInfo: true,

						//该回调函数 在点击分页组建时候出发的
						onChangePage : function(event, data){
							pagination(data.currentPage , data.rowsPerPage);
						}
					});
				}
			})
		}
		//为全选复选框绑定事件
		$("#qx").click(function () {
			$("input[name=xz]").prop("checked",this.checked)
		})
		//动态生成的 不能直接click  用外部元素on
		$("#tBody").on("click",function(){
			$("#qx").prop("checked",$("input[name=xz]").length == $("input[name=xz]:checked").length)
		})

		//为删除按钮绑定事件
		$("#deleteBtn").click(function () {
			//获取需要删除的记录
			var $obj = $("input[name=xz]:checked")
			var ids=""
			if($obj.size()==0){
				alert("请选择要删除的记录")
				return;
			}
			if(confirm("确定要删除吗？")){
				$.each($obj,function (i,e) {
					ids+="id=" + this.value+"&"  //id=xxx&id=xxx&
				})
				ids=ids.substr(0,ids.length-1) //id=xxx&id=xxx
				$.ajax({
					url:"workbench/activity/deleteActivityByIds.do",
					data:ids,
					dataType:"json",
					type:"post",
					success:function (data) {
						if(data.code=="1"){
							alert("删除成功!")
							pagination(1,10)
						}else{
							alert(data.message)
						}
					}
				})
			}
		})
		//为修改按钮绑定事件
		$("#editBtn").click(function () {
			//获取要修改的记录
			var $obj = $("input[name=xz]:checked")
			if($obj.size()==0){
				alert("请选择要修改的记录")
				return;
			}else if($obj.size()>1){
				alert("仅支持修改一条记录")
				return;
			}else{
				$("#editActivityModal").modal("show")
				var id = $obj[0].value
				$.ajax({
					url:"workbench/activity/queryActivityById.do",
					data:{
						id:id
					},
					dataType:"json",
					type:"get",
					success:function (data) {
						$("#edit-owner").val(data.owner)
						$("#edit-name").val(data.name)
						$("#edit-startDate").val(data.startDate)
						$("#edit-endDate").val(data.endDate)
						$("#edit-cost").val(data.cost)
						$("#edit-description").val(data.description)
					}
				})
			}
		})
		//为更新按钮绑定事件
		$("#updateBtn").click(function () {
			var id = $("input[name=xz]:checked")[0].value
			var owner = $("#edit-owner").val();
			var name = $("#edit-name").val();
			var startDate = $("#edit-startDate").val();
			var endDate = $("#edit-endDate").val();
			var cost = $("#edit-cost").val();
			var description = $("#edit-description").val();

			$.ajax({
				url:"workbench/activity/updateActivityById.do",
				data:{
					id:id,
					owner:owner,
					name:name,
					startDate:startDate,
					endDate:endDate,
					cost:cost,
					description:description
				},
				dataType:"json",
				type:"post",
				success:function (data) {
					if(data.code=="1"){
						alert("修改成功")
						$("#editActivityModal").modal("hide")
						pagination(1,10)
					}else{
						alert(data.message)
					}
				}

			})
		})
	});
	
</script>
</head>
<body>

<input type="hidden" id="hidden-name">
<input type="hidden" id="hidden-owner">
<input type="hidden" id="hidden-startDate">
<input type="hidden" id="hidden-endDate">

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form id="createActivityForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">
								  <c:forEach items="${uList}" var="u">
									  <option value="${u.id}">${u.name}</option>
								  </c:forEach>
								</select>
							</div>
                            <label for="create-name" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startDate" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startDate" readonly>
							</div>
							<label for="create-endDate" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-endDate" readonly>
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="createActivityBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select id="edit-owner" class="form-control" id="edit-marketActivityOwner">
									<c:forEach items="${uList}" var="u">
										<option  value="${u.id}">${u.name}</option>
									</c:forEach>
								</select>
							</div>
                            <label for="edit-name" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-name" value="发传单">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startDate" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startDate" value="2020-10-10">
							</div>
							<label for="edit-endDate" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endDate" value="2020-10-20">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="5,000">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="updateBtn" class="btn btn-primary" >更新</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 导入市场活动的模态窗口 -->
    <div class="modal fade" id="importActivityModal" role="dialog">
        <div class="modal-dialog" role="document" style="width: 85%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
                </div>
                <div class="modal-body" style="height: 350px;">
                    <div style="position: relative;top: 20px; left: 50px;">
                        请选择要上传的文件：<small style="color: gray;">[仅支持.xls]</small>
                    </div>
                    <div style="position: relative;top: 40px; left: 50px;">
                        <input type="file" id="activityFile">
                    </div>
                    <div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;" >
                        <h3>重要提示</h3>
                        <ul>
                            <li>操作仅针对Excel，仅支持后缀名为XLS的文件。</li>
                            <li>给定文件的第一行将视为字段名。</li>
                            <li>请确认您的文件大小不超过5MB。</li>
                            <li>日期值以文本形式保存，必须符合yyyy-MM-dd格式。</li>
                            <li>日期时间以文本形式保存，必须符合yyyy-MM-dd HH:mm:ss的格式。</li>
                            <li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
                            <li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="importActivityBtn" type="button" class="btn btn-primary">导入</button>
                </div>
            </div>
        </div>
    </div>
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="query-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="query-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control time" type="text" id="query-startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control time" type="text" id="query-endDate">
				    </div>
				  </div>
				  
				  <button type="button" id="queryBtn" class="btn btn-default">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" id="createBtn" class="btn btn-primary" data-toggle="modal"  data-target="#createActivityModal"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" id="editBtn" class="btn btn-default"  data-target="#editActivityModal"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" id="deleteBtn" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#importActivityModal" ><span class="glyphicon glyphicon-import"></span> 上传列表数据（导入）</button>
                    <button id="exportActivityAllBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表数据（批量导出）</button>
                    <button id="exportActivityXzBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表数据（选择导出）</button>
                </div>
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="tBody">
<%--						<tr class="active">--%>
<%--							<td><input type="checkbox" /></td>--%>
<%--							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.html';">发传单</a></td>--%>
<%--                            <td>zhangsan</td>--%>
<%--							<td>2020-10-10</td>--%>
<%--							<td>2020-10-20</td>--%>
<%--						</tr>--%>
<%--                        <tr class="active">--%>
<%--                            <td><input type="checkbox" /></td>--%>
<%--                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.html';">发传单</a></td>--%>
<%--                            <td>zhangsan</td>--%>
<%--                            <td>2020-10-10</td>--%>
<%--                            <td>2020-10-20</td>--%>
<%--                        </tr>--%>
					</tbody>
				</table>
			</div>
			
			<div  style="height: 50px; position: relative;top: 30px;">
				<div id="activityPage"></div>
			</div>
			
		</div>
		
	</div>
</body>
</html>