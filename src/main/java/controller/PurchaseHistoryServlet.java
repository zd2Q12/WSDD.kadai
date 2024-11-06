package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.PurchaseHistoryDao;
import domain.PurchaseHistory;

@WebServlet("/purchaseHistories")
public class PurchaseHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");

		try {
			Integer userId = (Integer) request.getSession().getAttribute("userId"); // セッションからユーザーIDを取得
			if (userId == null) {
				response.sendRedirect(request.getContextPath() + "/login");
				return;
			}
			PurchaseHistoryDao purchaseHistoryDao = DaoFactory.createPurchaseHistoryDao();
			List<PurchaseHistory> purchaseHistories = purchaseHistoryDao.findById(userId);
			request.setAttribute("purchaseHistories", purchaseHistories);
			if(!purchaseHistories.isEmpty()) {
				Integer productPrice = purchaseHistories.get(0).getProductPrice();
				request.getSession().setAttribute("productPrice", productPrice);
			}
			request
					.getRequestDispatcher("/WEB-INF/view/purchaseHistories.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();


		Integer productPrice = (Integer) session.getAttribute("productPrice");//
		// フォームからの値を取得
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		Integer productsId = Integer.valueOf(request.getParameter("productsId"));
		Integer meatsId = Integer.valueOf(request.getParameter("meatsId"));

		String productName = request.getParameter("product");
		String meatsName = request.getParameter("meat");
		String[] vegetableIds = request.getParameterValues("vegetables");
		String[] sauceIds = request.getParameterValues("sauce");
		String pickupTime = request.getParameter("pickup_time");
		

		
		System.out.println("受け取り時間: " + pickupTime);
		System.out.println("productPrice : "+productPrice);
		System.out.println("商品 : "+productsId);
		System.out.println("お肉 : "+meatsId);
		if (vegetableIds != null) {
			for (String vegetableId : vegetableIds) {
				System.out.println("野菜 : " + vegetableId);
			}
		}
		if (sauceIds != null) {
			for (String sauceId : sauceIds) {
				System.out.println("ソース : " + sauceId);
			}
		}
		// セッションにデータを格納
		session.setAttribute("userId", userId);
		session.setAttribute("productPrice", productPrice);
		session.setAttribute("productName", productName);
		session.setAttribute("productId", productsId);
		session.setAttribute("meatsName", meatsName);
		session.setAttribute("meatId", meatsId);
		session.setAttribute("VegetableIds", vegetableIds);
		session.setAttribute("SauceIds", sauceIds);
		session.setAttribute("pickupTime", pickupTime);

		// 次の処理にリダイレクトまたはフォワード
		response.sendRedirect(request.getContextPath() + "/purchase");
	}
}
