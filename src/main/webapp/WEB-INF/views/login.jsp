<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

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

<meta charset="UTF-8">
<title>로그인</title>

<style type="text/css">
body>.grid {
	height: 100%;
}

.column {
	max-width: 500px;
}

a {
	color: black;
}
</style>

<script>
	$(function() {
		$("#loginForm").submit(function() {
			$('.error').each(function() {
				$(this).toggleClass('error');
			});

			var errorText = "";

			var userId = $('#userId').val();
			if (isEmptyOrWhiteSpace(userId)) {
				errorText += "아이디를 입력하세요. \n";
				$('#userId').parent().parent().addClass('error')
			}
			var userPass = $('#userPass').val();
			if (isEmptyOrWhiteSpace(userPass)) {
				errorText += "비밀번호를 입력하세요. \n";
				$('#userPass').parent().parent().addClass('error')
			}

			if (errorText.length != 0) {
				alert(errorText)
				return false;
			}

		})

		function isEmptyOrWhiteSpace(inputValue) {

			if (inputValue == null)
				return true;
			return inputValue.trim().length == 0;
		}

	})
</script>

</head>
<body>

	<nav class="navbar navbar-expand navbar-dark bg-primary mb-3">
		<a class="navbar-brand" href="#">성공회대 출석체크</a>

	</nav>

	<div class="container" style="margin-top: 100px;">
		<h1 class="text-center">LOGIN</h1>
		<hr />
		<form method="post">
			<div class="form-group">
				<label for="num" class="sr-only">아이디 :</label> <input
					type="text" id="num" name="num" value=""
					class="form-control" placeholder="아이디" required autofocus />
			</div>

			<div class="form-group">
				<label for="pass" class="sr-only">비밀번호</label> <input
					type="password" id="pass" name="pass" class="form-control"
					placeholder="비밀번호" required>
			</div>

			<hr />
			<button type="submit" class="btn btn-primary btn-block btn-lg">로그인</button>
		</form>

	</div>



</body>
</html>