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
					<h5 class="modal-title" id="exampleModalLabel">2020.04.04 (1)
						출석체크</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row row-cols-1 row-cols-md-5">


						<div class="col mb-2 mt-2">

							<div class="card">
								<img src="${R}image/student/7.jpg" class="card-img-top" alt="...">
								<div class="card-body">
									<h5 class="card-title">고북희</h5>
									<p class="card-text">체육과학과</p>
									<select class="form-control">
										<option>미확인</option>
										<option>출석</option>
										<option>지각</option>
										<option>결석</option>
									</select>
								</div>
							</div>

						</div>


					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save changes</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>