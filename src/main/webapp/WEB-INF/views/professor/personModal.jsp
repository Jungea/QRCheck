<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title></title>
</head>

<body>
	<!-- Modal -->
	<div class="modal fade" id="personModal" data-backdrop="static"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div
			class="modal-dialog modal-dialog-scrollable modal-xl modal-dialog-centered"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title ml-2" id="exampleModalLabel">
						<b>출석 입력</b>
					</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close" data-url="dateList?courId=${ course.id }">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="row row-cols-1 row-cols-md-5" method="post"
						id="personForm">

						<c:forEach var="person" items="${ modalList }" varStatus="status">
							<div class="col mb-2 mt-2">

								<div class="card">
									<img
										src="${R}image/student/${ person.registration.student.id%11+1 }.jpg"
										class="card-img-top" alt="...">
									<div class="card-body">
										<h5 class="card-title text-center">${ person.registration.student.name }</h5>
										<p class="card-text text-center">
											${ person.registration.student.stuNum } <br>${ person.registration.student.deptName }</p>


										<input type="hidden" name="courId" value="${ courId }">
										<input type="hidden" name="attNum" value="${ person.num }">
										<select name="state" class="form-control">
											<option value="0" ${person.state == 0 ? "selected" : ""}>미입력
											</option>
											<option value="1" ${person.state == 1 ? "selected" : ""}>출석</option>
											<option value="2" ${person.state == 2 ? "selected" : ""}>지각</option>
											<option value="3" ${person.state == 3 ? "selected" : ""}>결석</option>
											<option value="4" ${person.state == 4 ? "selected" : ""}>인정</option>
											<option value="5" ${person.state == 5 ? "selected" : ""}>생공</option>
											<option value="6" ${person.state == 6 ? "selected" : ""}>조퇴</option>
										</select>


									</div>
								</div>

							</div>

						</c:forEach>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" data-url="dateList?courId=${ course.id }">Close</button>
					<button type="submit" class="btn btn-primary" form="personForm">저장</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>