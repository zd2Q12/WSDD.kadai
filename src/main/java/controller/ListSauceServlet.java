package controller;

//2024.9.30選択数の制限
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.SauceDao;
import domain.Sauce;

@WebServlet("/listSauce")
public class ListSauceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SauceDao sauceDao = DaoFactory.createSauceDao();
			List<Sauce> sauceList = sauceDao.findAll();
			request.setAttribute("sauceList", sauceList);
			request.getRequestDispatcher("/WEB-INF/view/listSauce.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] sauce = request.getParameterValues("sauce");
		//選択されたソースIDをコンソールに表示
	if (sauce != null) {
			System.out.println("selectedSauceIds");
			for (String id : sauce) {
				System.out.println(id);
			}
		} else {
			System.out.println("選択されたソースがありません");
		}
		//選択されたソースの数を確認
		if(sauce == null) {
			request.setAttribute("errorMessage", "*ソースが選択されていません。");
		}else if (sauce != null && sauce.length > 3) {
			request.setAttribute("errorMessage", "*選択できるソースは最大3つまでです。");
		} else {
			//選択されたソースの数を確認し、3つ以内だったらsessionに保存、リダイレクト
			HttpSession session = request.getSession();
			session.setAttribute("SauceIds", sauce); // 注意: "selectedSauceIds" ではなく "SauceIds" にする			
			response.sendRedirect(request.getContextPath() + "/cart");
			return;
		}
		//ソースに戻す
		try {
			SauceDao sauceDao = DaoFactory.createSauceDao();
			List<Sauce> sauceList = sauceDao.findAll();
			request.setAttribute("sauceList", sauceList);
			request.getRequestDispatcher("/WEB-INF/view/listSauce.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
