<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>PurchaseHistories|BURRITO</title>
<link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
<link rel="stylesheet" href="css/purchaseHistories.css" />
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
<body>
	<div class="main">
		<div class="right">
			<ul>
				<li><a href="listProduct">商品選択へ</a></li>
			</ul>
		</div>

		<h2>購入履歴</h2>
		<table border="1">
			<thead>
				<tr>
					<th>購入者</th>
					<th>商品</th>
					<th>お肉</th>
					<th>購入日時</th>
					<th>野菜</th>
					<th>ソース</th>
					<th>履歴から注文</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty purchaseHistories}">
					<tr>
						<td colspan="8">購入履歴がありません。</td>
					</tr>
				</c:if>
				<c:forEach items="${purchaseHistories }" var="purchaseHistory">
					<tr>
						<td><c:out value="${purchaseHistory.userName }" /></td>
						<td><c:out value="${purchaseHistory.productName }" /></td>
						<td><c:out value="${purchaseHistory.meatName }" /></td>
						<td><c:out value="${purchaseHistory.purchasedAt }" /></td>
						<td><c:forEach items="${purchaseHistory.vegetableName }"
								var="vegetable">
								<c:out value="${vegetable}" />
								<c:if test="${!status.last}">, </c:if>
								<!-- 最後の要素でない場合、カンマを表示 -->
							</c:forEach></td>
						<td><c:forEach items="${purchaseHistory.sauceName }"
								var="sauce">
								<c:out value="${sauce}" />
								<c:if test="${!status.last}">, </c:if>
								<!-- 最後の要素でない場合、カンマを表示 -->
							</c:forEach></td>
						<td>
							<form action="purchaseHistories" method="post">
								<input type="hidden" name="productsId"
									value="${purchaseHistory.productsId}" /> <input type="hidden"
									name="meatsId" value="${purchaseHistory.meatsId}" /> <input
									type="hidden" name="userId" value="${purchaseHistory.usersId}" />
								<input type="hidden" name="product"
									value="${purchaseHistory.productName}" /> <input type="hidden"
									name="meat" value="${purchaseHistory.meatName}" />
								<c:forEach items="${purchaseHistory.vegetableIds}"
									var="vegetableId">
									<input type="hidden" name="vegetables" value="${vegetableId}" />
								</c:forEach>
								<c:forEach items="${purchaseHistory.sauceIds}" var="sauceId">
									<input type="hidden" name="sauce" value="${sauceId}" />
								</c:forEach>


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
								<div class="submit">
									<button type="submit">再注文</button>
								</div>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<footer>
			<div class="center-container">
				<p>Copyright © 2024 chipotoli</p>
			</div>
		</footer>
	</div>
</body>
<script></script>
</html>