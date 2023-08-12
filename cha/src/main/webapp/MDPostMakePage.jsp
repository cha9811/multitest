<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
    function previewImage(input) {
        var preview = document.getElementById('preview');
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                preview.src = e.target.result;
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
<body>
	<form action="MDPostMake" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td class="MDTitle">게시글 제목 <input name="MD_title"><br>
				</td>
			</tr>
			<tr>
				<td class="content">게시글 내용 <input name="content"><br>
				</td>
			</tr>
			<tr>
				<td>
					<!-- 이미지 업로드를 위한 input 태그 -->
                    <input type="file" name="MD_post_Thumbnail" id="MD_post_Thumbnail" onchange="previewImage(this);">
                    <img id="preview" alt="Image Preview2" style="max-width: 600px; max-height: 600px;">
				</td>
			</tr>    
			<button type="submit">작성하기</button>
		</table>
	</form>
</body>
</html>