<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 시작페이지 -->
<!-- 시작페이지 -->
<!-- 시작페이지 -->
<!-- 시작페이지 -->
<!-- 시작페이지 -->
<!-- 시작페이지 -->

박람웨이브
<hr>

	<c:forEach items="${surveyAll}" var="dto">
		<c:if test="${empty dto.mainimg or dto.mainimg eq 'noimg'}">
			<!-- 이미지가 없는 경우 대체 이미지 -->
			<img src="./resources/img/noimg.png" alt="이미지를 찾을 수 없습니다.22" width="100" height="100"> <br>
		</c:if>
		<c:if test="${dto.mainimg ne 'noimg'}">
			<!-- 이미지가 있는 경우 -->
			<img src="${dto.mainimg}" alt="이미지가 있는데 찾을 수 없습니다.11" width="100" height="100"><br>
		</c:if>
	</c:forEach>
	
	<c:forEach items="${mdpostall}" var="dto">
	post id = ${dto.MD_id}<br>
	<a href="MDDetail?id=${dto.MD_id}">post title = ${dto.MD_title}</a><br>
	post content = ${dto.content}<br>
	게시글 생성시간 = ${dto.MD_create}<br>
	<c:if test="${dto.MD_modifi ne '0000-00-00 00:00:00'}">
     게시글 수정시간 = ${dto.MD_modifi}<br>
	</c:if>
	<hr>
	</c:forEach>

	
<form action="MDPostMakePage.jsp">
	<button>게시글 추가하기</button>
</form>

</body>
</html>