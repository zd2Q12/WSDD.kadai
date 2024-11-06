<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>内容確認</title>
<link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
<link rel="stylesheet" href="css/purchase.css" />
</head>
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
<body>
	<div class="main">
		<div class="right">
			<ul>
				<li><a href="logout">ログアウト</a></li>
				<li><a href="listSauce">ソースへ戻る</a></li>
			</ul>
		</div>
		<h2>カートの内容</h2>
		<p>選択した商品</p>
		<div class="product">
			<ul>
				<c:if test="${not empty productName}">
					<li>商品: ${productName}</li>
				</c:if>
				<c:if test="${empty productName}">
					<li>商品: なし</li>
				</c:if>
				<c:if test="${not empty meatsName}">
					<li>お肉: ${meatsName}</li>
				</c:if>
				<c:if test="${empty meatsName}">
					<li>肉: なし</li>
				</c:if>

				<c:if test="${not empty vegetableNames}">
					<c:forEach items="${vegetableNames }" var="vegetable">
						<li>野菜: ${vegetable}</li>
					</c:forEach>
				</c:if>
				<c:if test="${empty vegetableNames }">
					<li>野菜: なし</li>
				</c:if>
				<c:if test="${not empty sauceNames}">
					<c:forEach items="${sauceNames }" var="sauce">
						<li>ソース: ${sauce}</li>
					</c:forEach>
				</c:if>
				<c:if test="${empty sauceNames }">
					<li>ソース: なし</li>
				</c:if>
			</ul>
		</div>
		<h2>受け取り時間を選択</h2>
		<form action="purchase" method="get">
			<label for="pickup_time">受け取り時間:</label>
			<div class="time">
				<select id="pickup_time" name="pickup_time">
					<c:forEach var="hour" begin="10" end="20">
						<c:forEach var="minute" begin="0" end="30" step="30">
							<c:set var="time"
								value="${hour}:${minute < 10 ? '0' : ''}${minute}" />
							<option value="${time}">${time}</option>
						</c:forEach>
					</c:forEach>
				</select>
			</div>
			<p>金額：${productPrice}円</p>
			<input type="hidden" name="productPrice" value="${productPrice}" />

			<div class="submit">
				<input type="submit" value="お会計">
			</div>
		</form>
	</div>

	<footer>
		<div class="center-container">
			<p>Copyright © 2024 chipotoli</p>
		</div>
	</footer>


</body>
</html>