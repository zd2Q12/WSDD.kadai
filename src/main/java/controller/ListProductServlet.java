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
import dao.ProductsDao;
import domain.Products;

@WebServlet("/listProduct")
public class ListProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			ProductsDao productDao = DaoFactory.createProductDao();
			List<Products> productList = productDao.findAll();
			request.setAttribute("productList", productList);
			request
					.getRequestDispatcher("/WEB-INF/view/listProduct.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ユーザーが選択した商品のIDとpriceを取得
		String product = request.getParameter("product");
    System.out.println("Selected Product ID: " + product);

		//選択された製品の数を確認
		if (product == null || product.isEmpty()) {
	    request.setAttribute("errorMessage", "*商品を1つ選択してください");
			//商品に戻す
			try {
				ProductsDao productDao = DaoFactory.createProductDao();
				List<Products> productList = productDao.findAll();
				request.setAttribute("productList", productList);
				request
						.getRequestDispatcher("/WEB-INF/view/listProduct.jsp")
						.forward(request, response);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		} else {
			//選択された商品の詳細情報を
			try {
				ProductsDao productDao = DaoFactory.createProductDao();
				 Integer productId = Integer.valueOf(product);
         Products product1 = productDao.findById(productId);
			
				//セッション取得
				HttpSession session = request.getSession();
				session.setAttribute("product", product);
				if(product1 != null) {
					session.setAttribute("productId", product1.getId());//
					session.setAttribute("productPrice", product1.getPrice());//
          session.setAttribute("productName", product1.getName()); // 商品名も保存//

				}
			}catch(Exception e) {
				throw new ServletException(e);
			}
			response.sendRedirect(request.getContextPath() + "/listMeat");
		}
	}
}
