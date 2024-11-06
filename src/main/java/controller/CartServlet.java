package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.SauceDao;
import dao.VegetablesDao;
import domain.Sauce;
import domain.Vegetables;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String productName = (String) session.getAttribute("productName");//
		Integer productPrice = (Integer) session.getAttribute("productPrice");//
		
		String meatsName = (String) session.getAttribute("meatsName");//
		String[] VegetableIds = (String[]) session.getAttribute("VegetableIds");//
		String[] SauceIds = (String[]) session.getAttribute("SauceIds");//

		VegetablesDao vegeDao = DaoFactory.createVegetablesDao();
		List<String> vegetableNames = new ArrayList<>();
		if(VegetableIds != null) {//
			for(String vegetableId : VegetableIds) {//
				Vegetables vegetable;
				try {
					vegetable = vegeDao.findById(Integer.valueOf(vegetableId));
					if(vegetable != null) {
						vegetableNames.add(vegetable.getName());
					}
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
		SauceDao sauceDao = DaoFactory.createSauceDao();
		List<String> sauceNames = new ArrayList<>();
		if(SauceIds != null) {//
			for(String sauceId : SauceIds) {//
				Sauce sauce;
				try {
					sauce = sauceDao.findById(Integer.valueOf(sauceId));
					if(sauce != null) {
						sauceNames.add(sauce.getName());
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		// JSPに渡す
		request.setAttribute("productPrice", productPrice);//
		request.setAttribute("productName", productName);//
		request.setAttribute("meatsName", meatsName);//
		request.setAttribute("vegetableNames",vegetableNames);//
		request.setAttribute("sauceNames", sauceNames);//

		request.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
