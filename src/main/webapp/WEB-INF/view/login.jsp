<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/login.css" />
<title>ログインページ</title>
</head>
<body>
	<header>
		<div class="center-container">
			<h1>chipotoli</h1>
		</div>
	</header>
	<div class="container">
		<h1 class="fsize">オンラインオーダー</h1>
		<h2>ログイン</h2>
		<c:if test="${not empty error }">
			<p>ログイン ID かパスワードが正しくありません。</p>
		</c:if>
		<form action="" method="post">
			<p>
				<input type="text" name="loginId" placeholder="ログインID">
			</p>
			<p>
				<input type="text" name="loginPass" placeholder="ログインパスワード">
			</p>
			<p>
				<input type="submit" value="ログイン">
			</p>

			<h2>新規登録</h2>
		</form>
		<p class="addUser">
			<a href="addUser">新規登録がまだの方はこちら</a>
		</p>
	</div>
	<footer>
		<div class="center-container">
			<p>Copyright © 2024 chipotoli</p>
		</div>
	</footer>
</body>
</html>