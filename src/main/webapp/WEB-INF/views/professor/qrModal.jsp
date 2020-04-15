<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title></title>
</head>

<script>
$(function(){

	$(".closeQRBtn").click(function() {

		var courId = new URLSearchParams(location.search).get('id');
		$('#qrModal').modal();
		var url = "/api/closeQR/course/" + courId;
		$("#qrModal").modal('hide');
		//data-dismiss="modal" 
		$.ajax({
			type : "GET",
			url : url,
			success : function(args) {},
			error : function(e) {}
		});
		
	})
});
</script>

<body>
	<!-- Modal -->
    <div class="modal fade" id="qrModal" data-backdrop="static" tabindex="-1" role="dialog"
        aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalCenterTitle">출석 QR코드</h5>
                    <button type="button" class="closeQRBtn close" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <img src="${R}image/checkQR/${ courId }.png" class="card-img-top" alt="...">
                </div>
                <div class="modal-footer">
                    <button type="button" class="closeQRBtn btn btn-secondary">Close</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>