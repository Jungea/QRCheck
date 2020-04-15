<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<script src="${R}res/common.js"></script>

</head>
<body>
	<div class="container">
		<h1>학생목록</h1>
		
		<hr>
		
		<table class="table table-bordered mt5">
			<thead>
				<tr>
					<th>regiId</th>
					<th>강의명</th>
					<th>강의코드</th>
					<th>강의시간</th>
					<th>강의실</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="registration" items="${ list }">
					<tr data-url="edit?id=${ registration.id }">
						<td>${ registration.id }</td>
						<td>${ registration.course.name }</td>
						<td>${ registration.division.code }</td>
						<td><c:forEach var="time"
								items="${ registration.course.times }">
							${ time.getStringWeek(time.dayOfWeek) } ${ time.startTime } ~ ${ time.endTime }
						</c:forEach></td>
						<td>${ registration.course.room.code }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>

