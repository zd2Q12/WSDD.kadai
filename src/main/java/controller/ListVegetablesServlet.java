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
import dao.VegetablesDao;
import domain.Vegetables;

@WebServlet("/listVegetables")
public class ListVegetablesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			VegetablesDao vegetablesDao = DaoFactory.createVegetablesDao();
			List<Vegetables> veggiesList = vegetablesDao.findAll();
			request.setAttribute("veggiesList", veggiesList);
			request
					.getRequestDispatcher("/WEB-INF/view/listVegetables.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] vegetables = request.getParameterValues("vegetables");
		//選択された野菜IDをコンソールに表示
		if (vegetables != null) {
			System.out.println("selected vegetable Ids:");
			for (String id : vegetables) {
				System.out.println(id);
			}
		} else {
			System.out.println("選択された野菜はありません");
		}

		//選択された野菜の数を確認
		if (vegetables == null) {
			request.setAttribute("errorMessage", "*野菜が選択されていません。");
		} else if (vegetables.length > 3) {
			//3を超えたらエラー！
			request.setAttribute("errorMessage", "*選択できる野菜は最大3つまでです。");
		} else {
			//3以内だったらsessionに保存、リダイレクト
			HttpSession session = request.getSession();
			session.setAttribute("VegetableIds", vegetables);
			response.sendRedirect(request.getContextPath() + "/listSauce");
			return;
		}
		//野菜に戻す
		try {
			VegetablesDao vegetablesDao = DaoFactory.createVegetablesDao();
			List<Vegetables> veggiesList = vegetablesDao.findAll();
			request.setAttribute("veggiesList", veggiesList);
			request
					.getRequestDispatcher("/WEB-INF/view/listVegetables.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
