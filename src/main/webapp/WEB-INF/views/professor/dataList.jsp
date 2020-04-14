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
<!-- <link rel="stylesheet" href="${R}res/common.css"> -->


</head>
<body>

	<%@ include file="header.jsp"%>
	<%@ include file="qrModal.jsp"%>
	<%@ include file="personModal.jsp"%>

	<main role="main" class="container pb-4">
		<div class="jumbotro ">


			<h1>프론트엔드 출석체크</h1>
			<hr />

			<div class="card">
				<div class="card-body">
					<div class="mb-2">
						<button type="button" class="btn btn-primary btn-block btn-lg"
							data-toggle="modal" data-target="#qrModal">QR 코드 실행</button>
					</div>

					<div class="row row-cols-1 row-cols-md-3">

						<c:forEach var="card" items="${ list }">
							<div class="col mb-2 mt-2" data-toggle="modal" data-target="#personModal">

								<div class="card card_lecture">
									<div class="card-body">
										<h5 class="card-title">${ card.date }</h5>
										<h6 class="card-subtitle mb-2 text-muted">${ card.getStringWeek(card.date.getDayOfWeek().getValue()) }
											${ card.startTime } - ${ card.endTime }</h6>
										<p class="card-text">출석체크 인원 ${ card.checkNum }/${ card.totalNum }</p>
										<a href="#" class="card-link" >출석체크</a>
									</div>
								</div>

							</div>

						</c:forEach>



					</div>



				</div>
			</div>

		</div>
		<hr />
	</main>

</body>
</html>
