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
import dao.MeatsDao;
import domain.Meats;

@WebServlet("/listMeat")
public class ListMeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			MeatsDao meatsDao = DaoFactory.createMeatsDao();
			List<Meats> meatList = meatsDao.findAll();
			request.setAttribute("meatList", meatList);
			request
					.getRequestDispatcher("/WEB-INF/view/listMeat.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  // セッションから選択した肉のIDを取得
		String meat = request.getParameter("meat");
	   System.out.println("Selected Meat ID: " + meat);		
		
		//選択されたお肉の数を取得,	NULLまたは空か確認
		if(meat == null || meat.isEmpty()) {
			request.setAttribute("errorMassage", "*商品を1つ選択してください");
			//お肉選択に戻す
			try {
				MeatsDao meatsDao = DaoFactory.createMeatsDao();
				List<Meats> meatList = meatsDao.findAll();
				request.setAttribute("meatList", meatList);
				request
						.getRequestDispatcher("/WEB-INF/view/listMeat.jsp")
						.forward(request, response);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		} else {
			//選択された数が正しければsessionに格納し、vegetablesに遷移
      try {
      	MeatsDao meatsDao = DaoFactory.createMeatsDao();
      	Integer meatsId = Integer.valueOf(meat);
          Meats meat1 = meatsDao.findById(Integer.parseInt(meat));
          
          HttpSession session = request.getSession();
          session.setAttribute("meat", meat);
          if(meat1 != null) {
          	session.setAttribute("meatId", meat1.getId());//
          	session.setAttribute("meatsName", meat1.getName());
          }
      } catch (Exception e) {
        throw new ServletException(e);
      }
		response.sendRedirect(request.getContextPath() + "/listVegetables");
	}
	}
}
