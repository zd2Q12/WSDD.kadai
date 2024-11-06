<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>野菜選択|BURRITO</title>
<link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
<link rel="stylesheet" href="css/list.css" />
</head>
<body>
	<header>
		<div class="center-container">
			<h1>chipotoli</h1>
			<div class="gnav-wrap">
				<ul class="gnav-lists">
					<li class="gnav-list"><a href="listProduct">Products</a></li>
					<li class="gnav-list"><a href="listMeat">Meat</a></li>
					<li class="gnav-list"><a href="listVegetables">Vegetables</a></li>
					<li class="gnav-list"><a href="listSauce">Sauce</a></li>
					<li class="gnav-list"><a href="about">Shop</a></li>
					<li class="gnav-list"><a href="">Contact</a></li>
				</ul>
			</div>
		</div>
		<!-- /.center-container -->
	</header>

	<main class="main">
		<div class="right">
			<h2>
				ユーザー：
				<c:out value="${name }" />
			</h2>
			<ul>
				<li><a href="logout">ログアウト</a></li>
				<li><a href="purchaseHistories">購入履歴へ</a></li>
				<li><a href="listMeat">肉の選択画面へ戻る</a></li>
			</ul>
		</div>
		<h2>商品の選択</h2>
		<p>好きな具材を最大3つまでお選びください</p>
		<form action="" method="post">
			<table border="1">
				<tr>
					<th>品名</th>
					<th>詳細</th>
					<th>商品の選択</th>
				</tr>

				<c:forEach items="${veggiesList}" var="vegetables">
					<tr>
						<td><c:out value="${vegetables.name }" /></td>
						<td><c:out value="${vegetables.description }" /></td>
						<td><input type="checkbox" id="vegetables-${vegetables.id }"
							name="vegetables" value="${vegetables.id }"> <label
							for="vegetables-${vegetables.id }">追加</label></td>
					</tr>
				</c:forEach>

			</table>
			<div class="submit">
				<input type="submit" value="選択">
		</form>
		<c:if test="${not empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>

		</div>
	</main>
	<footer>
		<div class="center-container">
			<p>Copyright © 2024 chipotoli</p>
		</div>
	</footer>
</body>
</html>