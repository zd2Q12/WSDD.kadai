
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/addUser.css" />
</head>
	<header>
		<div class="center-container">
			<h1>chipotoli</h1>
		</div>
		<!-- /.center-container -->
	</header>
<body>
	<h2 class="text-center">会員登録</h2>
	<form action="" method="post">
		<table class="table" border="1">
			<tr>
               <th>名前</th>
				<td><c:if test="${not empty nameError }">
						<p class="error">
							*
							<c:out value="${nameError }" />
						</p>
					</c:if> 
					<input type="text" name="name" value="<c:out value='${name}'/>" />
				    <p class="validation-message">*15字以内で入力してください。</p>
				
				</td>
			</tr>

			<tr>
				<th>電話番号</th>
				<td><c:if test="${not empty telError }">
						<p class="error">
							*
							<c:out value="${telError }" />
						</p>
					</c:if>
				<input type="text" name="tel" value="<c:out value='${tel}'/>" />
				<p class="validation-message">*携帯番号を入力してください。</p>
				</td>
			</tr>

			<tr>
				<th>メールアドレス</th>
				<td><c:if test="${not empty emailError }">
						<p class="error">
							*
							<c:out value="${emailError }" />
						</p>
					</c:if>
					<input type="text" name="email" value="<c:out value='${email}'/>" />
					<p class="validation-message">*有効なメールアドレスを入力してください。</p>
				</td>
			</tr>

			<tr>
				<th>ログインID</th>
				<td><c:if test="${not empty loginIdError}">
						<p class="error">
							*
							<c:out value="${loginIdError}" />
						</p>
					</c:if> 
					<input type="text" name="loginId" value="<c:out value='${loginId}'/>" />
					<p class="validation-message">*5文字以上10文字以内で、アルファベットと数字のみで構成してください。</p>
				</td>
			</tr>
			<tr>
				<th>ログインパスワード</th>
				<td><c:if test="${not empty loginPassError }">
						<p class="error">
							*
							<c:out value="${loginPassError}" />
						</p>
					</c:if> <input type="password" name="loginPass" value="<c:out value='${loginPass}'/>" />
					<p class="validation-message">*8文字以上で、大文字、小文字、数字、特殊文字を含めてください。</p>
					</td>
			</tr>
		</table>
		<p class="text-center">
			<input type="submit" class="btn btn-primary" value="登録" /> 
		</p>
		<p class="text-center"><a href="login.jsp">戻る</a></p>
	</form>
	<footer>
		<div class="center-container">
			<p>Copyright © 2024 chipotoli</p>
		</div>
	</footer>
<script>/* javaScript */
        function validateForm() {
            let isValid = true;
            let errors = [];

            // 名前のバリデーション
            const name = document.forms["userForm"]["name"].value;
            if (name.trim() === "") {
                errors.push("名前が未入力です。");
                isValid = false;
            } else if (name.length > 15) {
                errors.push("名前は15字以内で入力してください。");
                isValid = false;
            }

            // 電話番号のバリデーション
            const tel = document.forms["userForm"]["tel"].value;
            const telPattern = /^0[5789]\d{8,9}$/;
            if (!telPattern.test(tel)) {
                errors.push("携帯番号を入力してください。");
                isValid = false;
            }

            // メールアドレスのバリデーション
            const email = document.forms["userForm"]["email"].value;
            const emailPattern = /^[^@]+@[^@]+\.[^@]+$/;
            if (!emailPattern.test(email)) {
                errors.push("有効なメールアドレスを入力してください。");
                isValid = false;
            }

            // ログインIDのバリデーション
            const loginId = document.forms["userForm"]["loginId"].value;
            if (loginId.length < 5 || loginId.length > 10) {
                errors.push("ログインIDは5文字以上10文字以内でなければなりません。");
                isValid = false;
            } else if (!/^[a-zA-Z0-9]+$/.test(loginId)) {
                errors.push("ログインIDはアルファベットと数字のみで構成されなければなりません。");
                isValid = false;
            }

            // パスワードのバリデーション
            const loginPass = document.forms["userForm"]["loginPass"].value;
            if (loginPass.length < 8 || loginPass.length > 60) {
                errors.push("パスワードは8文字以上でなければなりません。");
                isValid = false;
            } else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':"\\\\|,.<>\\/?]).{8,}$/.test(loginPass)) {
                errors.push("パスワードは大文字、小文字、数字、特殊文字を含む必要があります。");
                isValid = false;
            }

            // エラーメッセージを表示
            if (!isValid) {
                alert(errors.join("\n"));
            }

            return isValid;
        }

        document.forms["userForm"].onsubmit = validateForm;
    </script>
</body>
</html>
