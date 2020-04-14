<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title></title>
</head>

<body>
	<header class="mb-5">
        <nav class="navbar navbar-expand navbar-dark bg-primary ">
            <a class="navbar-brand" href="list">성공회대 출석체크</a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="list">Home <span class="sr-only">(current)</span></a>
                    </li>
                </ul>

                <span class="navbar-text mr-sm-3">
                   ${ user.profNum }(${ user.name }) 님
                </span>
                <button class="btn btn-outline-light my-2 my-sm-0 mr-2" type="button" data-url="logout">로그아웃</button>
            </div>
        </nav>

    </header>
</body>
</html>