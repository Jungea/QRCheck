<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.css" />
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.js"></script>

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


	<div class="ui grid middle aligned center aligned">
		<div class="column">
			<h1 class="ui teal header" style="font-size: 40px;">TAGNOTE</h1>

			<form:form method="post" class="ui form" modelAttribute="user"
				id="loginForm">
				<div class="ui segment">
					<div class="field" style="text-align: left;">
						<label>아이디</label>
						<div class="ui left icon input">
							<i class="user icon"></i>
							<form:input path="userId" placeholder="아이디" />
						</div>
					</div>
					<div class="field" style="text-align: left;">
						<label>비밀번호</label>
						<div class="ui left icon input">
							<i class="lock icon"></i>
							<form:password path="userPass" placeholder="비밀번호" />
						</div>
					</div>
					<button class="ui button fluid teal" type="submit">로그인</button>
				</div>
			</form:form>

			<div class="ui divider"></div>
			<div class="ui right aligned container">
				<span><a href="findPassword">비밀번호 찾기</a></span> <span>|</span> <span><a
					href="membership">회원가입</a></span>
			</div>
		</div>

	</div>

</body>
</html>