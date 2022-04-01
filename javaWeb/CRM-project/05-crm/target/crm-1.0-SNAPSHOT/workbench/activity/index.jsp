<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />


<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>

	<link rel="stylesheet" type="text/css" href="jquery/bs_pagination/jquery.bs_pagination.min.css">
	<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>

<script type="text/javascript">

	$(function(){

		$(".time").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "bottom-left"
		});

		//为创建按钮绑定事件 目的是打开添加操作的模态窗口
		$("#addBtn").click(function () {
			/*
				操作模态窗口的方式:
				需要操作的模态窗口的jquery对象，调用modal方法，为该方法传递参数show:打开模态窗口  hide:关闭模态窗口
			*/


			//打开模态窗口之前需要数据处理 所以要进行过后台
			//目的：取得用户信息列表 为下拉框添加用户
			$.ajax({
				url:"workbench/activity/getUserList.do",
				type:"get",  //添加 修改 删除 密码 用post  其他get
				dataType:"json",
				success:function(data){
					/*
							List<User> uList

							[{用户1},{用户2}....]
					 */
					var html=""
					$.each(data,function(i,e){
						//e是每一个user对象
						html+="<option value='"+e.id+"'>"+e.name+"</option>"
					})
					$("#create-owner").html(html);

					//下拉列表用户默认设置为当前登录用户
					//js中EL表达式必须套用在字符串中
					var id = "${user.id}"
					$("#create-owner").val(id);//设置下拉列表的默认值
					//处理完下拉窗口  打开模态窗口
					$("#createActivityModal").modal("show");
				}

			})

		})

		$("#saveBtn").click(function (){
			$.ajax({
				url:"workbench/activity/save.do",
				data:{
					"owner":$.trim($("#create-owner").val()),
					"name":$.trim($("#create-name").val()),
					"startDate":$.trim($("#create-startDate").val()),
					"endDate":$.trim($("#create-endDate").val()),
					"cost":$.trim($("#create-cost").val()),
					"description":$.trim($("#create-description").val())
				},
				type:"post",
				dataType:"json",
				success:function(data){

					/*
							data:{"success":true/false}
					 */
					//添加成功后 hide模态窗口 数据没有清空 所以需要我们进行清空数据
					if(data.success){
						//添加成功后 刷新列表
						pageList(1,2)
						/*
								拿到form表单的jquery对象 对于表单的jquery对象 提供了submit方法让我们提交表单
								但是表单的jquery对象 没有提供reset方法让我们重置表单 （坑:idea为我们提示由reset方法）
								虽然jquery对象没有 但是原生的js有
								dom对象与jquery对象之间的转换
						 */
						$("#activityAddForm")[0].reset();
						$("#createActivityModal").modal("hide");
					}else{
						alert("添加失败")
					}
				}

			})
		})
		//页面加载完毕 触发第一个方法  每页展示2条记录

		pageList(1,2)
		//为查询按钮绑定事件 触发pageList方法
		$("#searchBtn").click(function () {

			/*
        		BUG: 输入框里的内容未清空 点击下一页会搜索输入框的内容

        		应该先将搜索框中的信息 保存起来 保存到隐藏域中
			*/
			$("#hidden-name").val($.trim($("#search-name").val()))
			$("#hidden-owner").val($.trim($("#search-owner").val()))
			$("#hidden-startDate").val($.trim($("#search-startDate").val()))
			$("#hidden-endDate").val($.trim($("#search-endDate").val()))
			pageList(1,2)
		})




		/*
				对于所有的关系型数据库，做前端的分页相关操作的基础组件就是pageNo和pageSize
				pageNo:页码
				pageSize:每页展现的记录数
				pageList方法:就是发出ajax请求到后台，从后台取得最新的市场活动信息列表数据
				通过响应回来的数据，局部刷新市场活动信息列表
				我们都在哪些情况下，需要调用pageList方法（什么情况下需要刷新一下市场活动列表)
				(1）点击左侧菜单中的"市场活动"超链接，需要刷新市场活动列表，调用pageList方法
				(2）添加，修改，删除后，需要刷新市场活动列表，调用pageList方法
				(3）点击查询按钮的时候，需要刷新市场活动列表，调用pageList方法
				(4）点击分页组件的时候，调用pageList方法
				以上为pageList方法制定了六个入口，也就是说，在以上6个操作执行完毕后，我们必须要调用pageList方法，刷新市场活动信息列表

		 */

		function pageList(pageNo,pageSize){
			//将全选✓干掉
			$("#qx").prop("checked",false);
			//查询前 将隐藏域中信息取出 重新赋予到搜索框
			$("#search-name").val($.trim($("#hidden-name").val()))
			$("#search-owner").val($.trim($("#hidden-owner").val()))
			$("#search-startDate").val($.trim($("#hidden-startDate").val()))
			$("#search-endDate").val($.trim($("#hidden-endDate").val()))
			$.ajax({
				url:"workbench/activity/pageList.do",
				data:{
					"pageNo":pageNo,
					"pageSize":pageSize,
					"name":$.trim($("#search-name").val()),				//可能为空  【动态sql】
					"owner":$.trim($("#search-owner").val()),
					"startDate":$.trim($("#search-startDate").val()),
					"endDate":$.trim($("#search-endDate").val()),
				},
				type:"get",
				dataType:"json",
				success:function(data){
					/*

							data
							[{市场活动1},{2},{3}]

							{"total":100,"dataList":[{},{},{}]}
					 */
				var html="";
				$.each(data.dataList,function(i,e){
					html+='<tr class="active">'
					html+='<td><input type="checkbox" name="xz" value='+e.id+'></td>'
					html+='<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'workbench/activity/detail.do?id='+e.id+'\';">'+e.name+'</a></td>'
					html+='<td>'+e.owner+'</td>'
					html+='<td>'+e.startDate+'</td>'
					html+='<td>'+e.endDate+'</td>'
					html+='</tr>'
				})
				$("#activityBody").html(html)

				//计算总页数
				var totalPages = data.total%pageSize==0?data.total/pageSize:parseInt(data.total/pageSize)+1;
				//数据处理完毕后 结合分页插件 对前端展现分页信息
				$("#activityPage").bs_pagination({
						currentPage: pageNo, // 页码
						rowsPerPage: pageSize, // 每页显示的记录条数
						maxRowsPerPage: 20, // 每页最多显示的记录条数
						totalPages: totalPages, // 总页数
						totalRows: data.total, // 总记录条数

						visiblePageLinks: 3, // 显示几个卡片

						showGoToPage: true,
						showRowsPerPage: true,
						showRowsInfo: true,
						showRowsDefaultInfo: true,

						//该回调函数 在点击分页组建时候出发的
						onChangePage : function(event, data){
							pageList(data.currentPage , data.rowsPerPage);
						}
				});
				}

			})
		}
		//为全选的复选框绑定事件
		$("#qx").click(function () {
			$("input[name=xz]").prop("checked",this.checked);//prop是给dom已有属性设置值 attr给dom元素设置属性
		})

		// //这种方法不行
		// $("input[name=xz]").click(function () {
		// 	alert(123)
		// })

		/*
			因为动态生成的元素 不可以以普通事件的形式来进行操作  而是要以on来触发
				语法:
					$(需要绑定元素的有效的外层元素).on(绑定事件的方式,需要绑定元素的jquery对象，回调函数)
		*/
		$("#activityBody").on("click",function(){
			$("#qx").prop("checked",$("input[name=xz]").length == $("input[name=xz]:checked").length)
		})

		//为删除按钮绑定事件 执行市场活动删除操作
		$("#deleteBtn").click(function(){
			//找到复选框中√的对象
			var $xz = $("input[name=xz]:checked");
			if($xz.length==0){
				alert("请选择要删除的记录")
			}else{
				if(confirm("确定要删除选中的记录吗?")){
					//url:workbench/activity/delete.do?id=xxx&id=xxx
					var param=""
					for(var i=0;i<$xz.length;i++) {
						param += "id=" + $($xz[i]).val();
						//如果不是最后一个元素 需要追加一个&
						if(i<$xz.length-1){
							param +="&";
						}
					}
					$.ajax({
						url:"workbench/activity/delete.do",
						data:param,
						type:"post",
						dataType:"json",
						success:function(data){
							/*
                                {"success":true/false}
                             */
							if(data.success){
								//删除成功
								alert("删除成功")
								pageList(1,2);
							}else{
								alert("删除失败");
							}
						}

					})
				}

			}

		})
		//为修改按钮绑定事件 打开修改操作的模态窗口   过后台 得到这条记录的信息 在执行修改
		$("#editBtn").click(function () {
			var $xz = $("input[name=xz]:checked");
			if($xz.length==0){
				alert("请选择要修改的记录")
			}else if($xz.length>1){
				alert("请选择一条要修改的记录")
			}else {
				var id = $xz.val();
				$.ajax({
					url:"workbench/activity/getUserListAndActivity.do",
					data:{
						"id":id
					},
					type:"get",
					dataType:"json",
					success:function(data){
						/*
							data:
							["uList":[{}],"a":{}]

						 */
						var html="";
						$.each(data.uList,function(i,e){

							html+="<option value='"+e.id+"'>"+e.name+"</option>"
						})
						$("#edit-owner").html(html);

						$("#edit-id").val(data.a.id)
						$("#edit-name").val(data.a.name)
						$("#edit-owner").val(data.a.owner)
						$("#edit-startDate").val(data.a.startDate)
						$("#edit-endDate").val(data.a.endDate)
						$("#edit-cost").val(data.a.cost)
						$("#edit-description").val(data.a.description)

						//所有值都添加完毕  即可打开模态窗口
						$("#editActivityModal").modal("show");
					}

				})
			}
		})

		//为更新按钮绑定事件  执行修改操作
		//实际项目中 先添加 再修改  修改操作一般都是copy添加操作
		$("#updateBtn").click(function () {
			$.ajax({
				url:"workbench/activity/update.do",
				data:{
					"id":$.trim($("#edit-id").val()),
					"owner":$.trim($("#edit-owner").val()),
					"name":$.trim($("#edit-name").val()),
					"startDate":$.trim($("#edit-startDate").val()),
					"endDate":$.trim($("#edit-endDate").val()),
					"cost":$.trim($("#edit-cost").val()),
					"description":$.trim($("#edit-description").val())
				},
				type:"post",
				dataType:"json",
				success:function(data){


					if(data.success){
						//修改成功后 刷新市场活动信息列表
						pageList(1,2)

						$("#editActivityModal").modal("hide");
					}else{
						alert("修改失败")
					}
				}

			})
		})

	})

</script>
</head>
<body>
	<!--隐藏域-->
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
				
					<form id="activityAddForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-Owner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-owner">

								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-name">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startDate" readonly>
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
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
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<!--
							data-dismiss=‘model’ 表示关闭模态窗口
					-->
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
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
						<input type="hidden" id="edit-id">
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-owner">

								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-name" >
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-startDate" >
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-endDate" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" >
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<!--
										关于文本域：
												1.一定是要以标签对的形式来呈现 正常状态下标签对要紧紧挨着
												2.对其取值和赋值 统一使用val()方法  不是html()方法
								-->
								<textarea class="form-control" rows="3" id="edit-description"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateBtn">更新</button>
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
				      <input class="form-control" type="text" id="search-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="search-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control" type="text" id="search-startDate" />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control" type="text" id="search-endDate">
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="searchBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteBtn"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="qx" /></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="activityBody">
<%--						<tr class="active">--%>
<%--							<td><input type="checkbox" /></td>--%>
<%--							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/activity/detail.jsp';">发传单</a></td>--%>
<%--                            <td>zhangsan</td>--%>
<%--							<td>2020-10-10</td>--%>
<%--							<td>2020-10-20</td>--%>
<%--						</tr>--%>
<%--                        <tr class="active">--%>
<%--                            <td><input type="checkbox" /></td>--%>
<%--                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='workbench/activity/detail.jsp';">发传单</a></td>--%>
<%--                            <td>zhangsan</td>--%>
<%--                            <td>2020-10-10</td>--%>
<%--                            <td>2020-10-20</td>--%>
<%--                        </tr>--%>
					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 30px;">

				<div id="activityPage"></div>

			</div>
			
		</div>
		
	</div>
</body>
</html>