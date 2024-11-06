package controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import dao.DaoFactory;
import dao.UsersDao;
import domain.Users;

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request
				.getRequestDispatcher("/WEB-INF/view/addUser.jsp")
				.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	boolean isError = false;//バリで用フラグ
	
		//パラメーターの取得、バリデーション
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String loginId = request.getParameter("loginId");
		String loginPass = request.getParameter("loginPass");
		
		if(name.isBlank()) {//名前
			request.setAttribute("nameError", "名前が未入力です。");
			}else if(name.length() > 15) {
				 request.setAttribute("nameError", "15字以内で入力してください。");
			 isError = true; 
		}

		Pattern pattern = Pattern.compile("^0[5789]\\d{8,9}$");//電話

		Matcher matcher = pattern.matcher(tel);
		if (!matcher.matches()) {
			 request.setAttribute("telError","携帯番号を入力してください");
			isError = true; 
		}
		pattern = Pattern.compile("[^@]+@[^@]+\\.[^@]+");//メール

		matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			 request.setAttribute("emailError","メールアドレスを入力してください");
			isError = true; 
		}
		
		if(loginId == null || loginId.length()< 5 || loginId.length() > 10) {
			 request.setAttribute("loginIdError","ログインIDは5文字以上10文字以内でなければなりません");
		}else if(!loginId.matches("^[a-zA-Z0-9]+$")) {
			 request.setAttribute("loginIdError","ログインIDはアルファベットと数字のみで構成されなければなりません。");
			isError = true; 
		}
		if (loginPass == null || loginPass.length() < 8 || loginPass.length() > 60) {
			 request.setAttribute("loginPassError","パスワードは8文字以上60文字以内でなければなりません。");
  } else if (!loginPass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$")) {
  	 request.setAttribute("loginPassError","パスワードは大文字、小文字、数字、特殊文字を含む必要があります。");  
    isError = true; 
  }
		if(isError) {
	     request.setAttribute("name", name);
       request.setAttribute("tel", tel);
       request.setAttribute("email", email);
       request.setAttribute("loginId", loginId);
       request.setAttribute("loginPass", loginPass);
			request
					.getRequestDispatcher("/WEB-INF/view/addUser.jsp")
					.forward(request, response);
			return;
		}
		
		// コンソールに入力された値を表示
		System.out.println("Name: " + name);
		System.out.println("Tel: " + tel);
		System.out.println("Email: " + email);
		System.out.println("Login ID: " + loginId);
		System.out.println("Login Password: " + loginPass);
		
	//パスワードハッシュ化BCrypt
		String hashedPassword = BCrypt.hashpw(loginPass, BCrypt.gensalt());
		
		//データの追加,ユーザーオブジェクト作成
		Users user = new Users();
		user.setName(name);
		user.setTel(tel);
		user.setEmail(email);
		user.setLoginId(loginId);
		user.setLoginPass(hashedPassword);
		
		try {
			UsersDao userDao = DaoFactory.createUsersDao();
			userDao.insert(user);
			request
					.getRequestDispatcher("/WEB-INF/view/addUserDone.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
