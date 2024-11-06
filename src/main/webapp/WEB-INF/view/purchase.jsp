<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>購入確認</title>
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
			<li><a href="purchaseHistories">購入履歴</a></li>
				<li><a href="cart">カートへ戻る</a></li>
			</ul>
		</div>

		<h2>購入内容の確認</h2>
		<p>選択した商品</p>
		<div class="product">
			<ul>
				<li>商品 : ${productName}</li>
				<li>お肉 : ${meatsName}</li>

				<c:if test="${not empty vegetablesNames}">
					<c:forEach items="${vegetablesNames }" var="vegetable">
						<li>野菜 : ${vegetable}</li>
					</c:forEach>
				</c:if>
				<c:if test="${empty vegetablesNames }">
					<li>野菜 : なし</li>
				</c:if>
				<c:if test="${not empty saucesNames}">
					<c:forEach items="${saucesNames }" var="sauce">
						<li>ソース : ${sauce}</li>
					</c:forEach>
				</c:if>
				<c:if test="${empty saucesNames }">
					<li>ソース : なし</li>
				</c:if>
			</ul>
		</div>

		<p>受け取り時間 :${pickupTime}</p>
		<p>金額 : ${productPrice}円</p>
		<!-- totalPriceをセッションやリクエストにセットしておく -->


		<h2 class="user">購入者情報</h2>
		<p>名前：${sessionScope.name != null ? sessionScope.name : 'なし'}</p>
		<p>電話番号：${sessionScope.tel != null ? sessionScope.tel : 'なし'}</p>
		<p>メールアドレス：${sessionScope.email != null ? sessionScope.email : 'なし'}</p>
		
		<div class="submit">
			<form action="" method="post">
				<input type="submit" value="購入を確定する">
			</form>
		</div>
		<c:if test="${not empty errorMessage}">
			<p style="color: red;">${errorMessage}</p>
		</c:if>
		<c:if test="${not empty successMessage}">
			<p style="color: green;">${successMessage}</p>
		</c:if>
	</div>
	<footer>
		<div class="center-container">
			<p>Copyright © 2024 chipotoli</p>
		</div>
	</footer>
</body>
</html>
