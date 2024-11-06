package controller;
//10.18に進捗状況確認があるので、それまでに装飾をある程度まで進める
//装飾　購入完了画面、　ログイン、アウト、
//css,jsなどもfilterがあると全てかかってしまい、反映しなくなるので除外->10.24済
//↓が終わったら、bootstrap,cssなどの装飾に入ること　->10.22~　
//購入規約も作りたい
//受け取り時間の指定を、現在時間から30分以上後の予約をできるようにしたい（30分間隔で、は変わらず）->難しいかもjavaScryptで出来る？

//ユーザー登録のバリデ-javaScript, 正規表現、  パスワードのハッシュ化-＞BCrypt =>10.17済
//選択した商品の項目（cart,purchase）は、名前、できれば写真で表示させる->時間があったら-＞名前で表示完了10.16済
//購入履歴からの購入と、一から注文、どちらからでも受け取り時間を選択できるようにする->10.16済
//野菜、ソースも名前で表示させる-＞10.16済
//ユーザー情報を、ログイン中常にすぐアクセスできるようにする（注文履歴、ユーザー情報の変更が見れる）-＞済
//↑作ったが、vegeとsauceが複数回同じ数字が表示されてしまう。その改善+idではなく、名前で表示させること->10.10済
//↑は、一度domainから作り、purchaseDaoにつなげるか、DAO,DaoImpl-Servletも作る必要がある。-＞10.10済
//商品、肉は一つ、野菜、ソースは3つまでしか選択できないように。-＞10/1済　　野菜、ソースができてない　-＞10/8済
//商品などを、やっぱり違うものに再選択しようとした場合、再選択に戻るタイミングで一度選択した商品情報を削除したほうがいい//10/8済
//2024.10.4　ログイン-商品選択-商品確認・受け取り時間の指定-商品確認・受け取り時間の確認-注文受付-ログアウトまで完成（DB入った！）
//10/3言ってた：purVege,purSauce DBにも、データが入るように。多分domainからつくらないとだめな気がする。10/4
//　　　　　　　　　：そのあと、purchaseServletでそれぞれinsertできればもうServletはinsertの為には作りたくない10/4
//購入を確定からpurchaseDoneに遷移させる　10/4済
//2024.09.27  purchaseServletのjspの表示までできたが、ユーザー情報の紐づけができてないのでする10/3済
//受け取り時間、合計金額を表示させる　　10/3済

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.UsersDao;
import domain.Users;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request
				.getRequestDispatcher("/WEB-INF/view/login.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String loginId = request.getParameter("loginId");
			String loginPass = request.getParameter("loginPass");
			
			UsersDao userDao = DaoFactory.createUsersDao();
			
			Users user = userDao.findByLoginIdAndLoginPass(loginId, loginPass);
			
			if (user != null) {
				request.getSession().setAttribute("loginId", user.getLoginId());	
				request.getSession().setAttribute("userId", user.getId());
      	request.getSession().setAttribute("name", user.getName());
				request.getSession().setAttribute("tel", user.getTel());
				request.getSession().setAttribute("email", user.getEmail());
        System.out.println("User ID set in session: " + user.getId());

				response.sendRedirect(request.getContextPath() + "/listProduct");
			} else {
				request.setAttribute("error", "無効なIDまたはパスワードです。");
				request
						.getRequestDispatcher("/WEB-INF/view/login.jsp")
						.forward(request, response);
			}
		} catch (Exception e) {
			 e.printStackTrace(); // エラーをコンソールに表示
       request.setAttribute("error", "ログイン中にエラーが発生しました。"); // エラーメッセージを設定
       request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
			//throw new ServletException(e);
		}
	}

}
