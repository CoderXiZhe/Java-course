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
		
		


		$("#searchBtn").click(function () {
			pageList(1,3);
		})
		function pageList(pageNo,pageSize) {
			$.ajax({
				url:"workbench/transaction/pageList.do",
				data:{
					"pageNo":pageNo,
					"pageSize":pageSize,
					"search-owner":$.trim($("#search-owner").val()),
					"search-name":$.trim($("#search-name").val()),
					"search-customer":$.trim($("#search-customer").val()),
					"search-stage":$.trim($("#search-stage").val()),
					"search-type":$.trim($("#search-type").val()),
					"search-source":$.trim($("#search-source").val()),
					"search-contacts":$.trim($("#search-contacts").val())
				},
				type:"get",
				dataType:"json",
				success:function(data){
					/*
						data:{"total":,"dataList":[{},{}]}
					 */

					var html=""
					$.each(data.dataList,function (i,e) {
						html+='<tr>'
						html+='<td><input type="checkbox" /></td>'
						html+='<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href=\'workbench/transaction/detail.do?id='+e.id+'\';">'+e.name+'</a></td>'
						html+='<td>'+e.customer+'</td>'
						html+='<td>'+e.stage+'</td>'
						html+='<td>'+e.type+'</td>'
						html+='<td>'+e.owner+'</td>'
						html+='<td>'+e.source+'</td>'
						html+='<td>'+e.contacts+'</td>'
						html+='</tr>'
					})
					$("#tbody").html(html)

					var totalPages = data.total%pageSize==0?data.total/pageSize:parseInt(data.total/pageSize)+1;
					$("#tranPage").bs_pagination({
						currentPage: pageNo, // ??????
						rowsPerPage: pageSize, // ???????????????????????????
						maxRowsPerPage: 20, // ?????????????????????????????????
						totalPages: totalPages, // ?????????
						totalRows: data.total, // ???????????????

						visiblePageLinks: 3, // ??????????????????

						showGoToPage: true,
						showRowsPerPage: true,
						showRowsInfo: true,
						showRowsDefaultInfo: true,

						//??????????????? ????????????????????????????????????
						onChangePage : function(event, data){
							pageList(data.currentPage , data.rowsPerPage);
						}
					});
				}

			})




		}
		pageList(1,2);
	});
	
</script>
</head>
<body>

	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>????????????</h3>
			</div>
		</div>
	</div>
	
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
	
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">?????????</div>
				      <input class="form-control" type="text" id="search-owner">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">??????</div>
				      <input class="form-control" type="text" id="search-name" >
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">????????????</div>
				      <input class="form-control" type="text" id="search-customer">
				    </div>
				  </div>
				  
				  <br>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">??????</div>
					  <select class="form-control" id="search-stage">
					  	<option></option>
					  	<option>????????????</option>
					  	<option>????????????</option>
					  	<option>????????????</option>
					  	<option>???????????????</option>
					  	<option>??????/??????</option>
					  	<option>??????/??????</option>
					  	<option>??????</option>
					  	<option>???????????????</option>
					  	<option>?????????????????????</option>
					  </select>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">??????</div>
					  <select class="form-control" id="search-type">
					  	<option></option>
					  	<option>????????????</option>
					  	<option>?????????</option>
					  </select>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">??????</div>
				      <select class="form-control" id="search-source">
						  <option></option>
						  <option>??????</option>
						  <option>????????????</option>
						  <option>????????????</option>
						  <option>????????????</option>
						  <option>????????????</option>
						  <option>????????????</option>
						  <option>????????????</option>
						  <option>????????????</option>
						  <option>?????????????????????</option>
						  <option>???????????????</option>
						  <option>?????????</option>
						  <option>web??????</option>
						  <option>web??????</option>
						  <option>??????</option>
						</select>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">???????????????</div>
				      <input class="form-control" type="text" id="search-contacts">
				    </div>
				  </div>
				  
				  <button  class="btn btn-default" id="searchBtn">??????</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 10px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" onclick="window.location.href='workbench/transaction/getUserList.do';"><span class="glyphicon glyphicon-plus"></span> ??????</button>
				  <button type="button" class="btn btn-default" onclick="window.location.href='workbench/transaction/edit.html';"><span class="glyphicon glyphicon-pencil"></span> ??????</button>
				  <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> ??????</button>
				</div>
				
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" /></td>
							<td>??????</td>
							<td>????????????</td>
							<td>??????</td>
							<td>??????</td>
							<td>?????????</td>
							<td>??????</td>
							<td>???????????????</td>
						</tr>
					</thead>
					<tbody id="tbody">

					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 20px;">

					<div id="tranPage">

					</div>
			</div>
			
		</div>
		
	</div>
</body>
</html>