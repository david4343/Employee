<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-ng-app="employee"><!-- ng-app은 AngularJS가 관할하는 View 영역을 명시 -->
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- JQuery가 AngularJS보다 순서상 먼저 와야한다. AngularJS가 내부적으로 JQuery를 사용하기 때문이다. -->
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<!-- AngularJS -->
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script type="text/javascript">
var app = angular.module('employee', []);	//Angular의 모듈을 만든다.

//OnLoad시에 호출되는 함수
//function(){}영역이 적용될 범위를 지정(여기선 body에 적용)
app.controller('listController', function($scope, $http) {	//$로 시작하는 변수는 Angular에서 기본적으로 제공하는 객체
	alert("listController...");
	
	//Javascript의 객체는 거의 Map방식으로 이뤄져있다.
	$scope.left = "왼쪽";
	$scope.right = "오른쪽";
	
	$scope.change = function(button) {	//AngularJS는 직접적으로 view를 바꾸는 것이 아닌 model을 변경함으로써 dynamic하게 반응하도록 한다.
		switch(button) {
		case "left":
			$scope.left = "#" + $scope.left;
			break;
		case "center":
			$scope.left="";
			$scope.right="";
			$scope.depts = [
                {deptno: 100, dname: "총무부", loc: "서울"},
                {deptno: 200, dname: "개발부", loc: "제주"}
            ];
			break;
		case "right":
			$scope.right += "@";
			break;
		default:
			break;
		}
		alert("change clicked..." + button);
	};
	
	var ajax = $http.get('/Employee/dept/list/json');
	ajax.then(function(response) {
		console.dir(response);
		console.dir(response.data);
		$scope.depts = response.data;
	});
});
</script>
</head>
<body data-ng-controller="listController" class="container">
<div class="row">
	<div class="col-md-4"><button class="btn btn-danger" data-ng-click="change('left')">change</button></div>
	<div class="col-md-4"><button class="btn btn-success" data-ng-click="change('center')">change</button></div>
	<div class="col-md-4"><button class="btn btn-primary" data-ng-click="change('right')">change</button></div>
</div>
<div class="row">
	<div class="col-sm-4" style="background-color:red">{{left}}</div><!-- {{}}는 view에 binding하는 방식 -->
	<div class="col-sm-4" style="background-color:green"><pre>{{depts}}</pre></div>
	<div class="col-sm-4" style="background-color:blue">{{right}}</div>
</div>
<hr>
<ul>
	<li ng-repeat="dept in depts"><!-- dept in depts는 for (dept : depts){}와 비슷한 역할을 담당한다. -->
		{{dept.deptno}}, {{dept.dname}}, {{dept.loc}}
	</li>
</ul>
<hr>
<table class="table table-striped table-hover">
	<thead>
		<tr>
			<th>Deptno</th><th>Dname</th><th>Loc</th>
		</tr>
	</thead>
	<tfoot></tfoot>
	<tbody>
		<tr ng-repeat="d in depts">
			<td>{{d.deptno}}</td>
			<td>{{d.dname}}</td>
			<td>{{d.loc}}</td>
		</tr>
	</tbody>
</table>
</body>
</html>