<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-ng-app="employee">
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<!-- AngularJS -->
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script type="text/javascript">
var app = angular.module('employee', []);

app.controller('listController', function($scope, $http) {
// 	alert("listController...");
	var ajax = $http.get('/Employee/emp/list');
	ajax.then(function(res) {
		//성공시
		console.dir(res);
		$scope.response = res.data;
		$scope.emps = res.data.data;	//emps는 table에서 정보를 뿌려주기 위해 별도로 만든 변수
// 		alert(res.data.message);
	}, function(res) {
		//실패시
		console.dir(res);
	});
});
</script>
</head>
<body data-ng-controller="listController" class="container">
<h1> 직원 리스트 </h1>

<a href="append.jsp" class="btn btn-primary">직원추가</a>

<!-- <div class="row"> -->
<!-- 	<div class="col-md-1">{{response.success}}</div> -->
<!-- 	<div class="col-md-10"><pre>{{response.data}}</pre></div> -->
<!-- 	<div class="col-md-1">{{response.message}}</div> -->
<!-- </div> -->
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>empno</th>
			<th>ename</th>
			<th>job</th>
			<th>mgr</th>
			<th>hiredate</th>
			<th>sal</th>
			<th>comm</th>
			<th>deptno</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
	</thead>
	<tfoot></tfoot>
	<tbody>
		<tr data-ng-repeat="e in emps">
			<td>{{e.empno}}</td>
			<td><a href="detail.jsp?empno={{e.empno}}">{{e.ename}}</a></td>
			<td>{{e.job}}</td>
			<td>{{e.mgr}}</td>
			<td>{{e.hiredate| date : 'yyyy년 MM월 dd일'}}</td>
			<td>{{e.sal}}</td>
			<td>{{e.comm}}</td>
			<td>{{e.deptno}}</td>
			<td><a href="update.jsp?empno={{e.empno}}"><i class="glyphicon glyphicon-edit"></i></a></td>
			<td><a href="delete.jsp?empno={{e.empno}}"><i class="glyphicon glyphicon-remove"></i></a></td>
		</tr>
	</tbody>
</table>
</body>
</html>