package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoFactory;
import dao.PurchaseDao;
import dao.PurchaseSaucesDao;
import dao.PurchaseVegetablesDao;
import dao.SauceDao;
import dao.VegetablesDao;
import domain.Purchase;
import domain.PurchaseSauces;
import domain.PurchaseVegetables;
import domain.Sauce;
import domain.Vegetables;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//再度商品情報、受け取り時間、購入者情報を表示
		HttpSession session = request.getSession();

		//ユーザー情報取得
		//Integer userId = (Integer) session.getAttribute("userId");
		String name = (String) session.getAttribute("name");
		String tel = (String) session.getAttribute("tel");
		String email = (String) session.getAttribute("email");

		// 商品IDと価格、肉ID
		Integer productPrice = (Integer) session.getAttribute("productPrice");//
		String productName = (String) session.getAttribute("productName");//
		String meatsName = (String) session.getAttribute("meatsName");//
		// リクエストパラメータから受け取り時間を取得し、なければセッションから取得
		String pickupTime = request.getParameter("pickup_time");
		if (pickupTime == null) {
			pickupTime = (String) session.getAttribute("pickupTime");
		}
		session.setAttribute("pickupTime", pickupTime);//受け取り時間をセッションに格納

		// 野菜IDとソースID
		String[] vegetableNames = (String[]) session.getAttribute("VegetableIds");//
		String[] sauceNames = (String[]) session.getAttribute("SauceIds");//

		// 野菜の名前を取得
		VegetablesDao vegeDao = DaoFactory.createVegetablesDao();
		List<String> vegetablesNames = new ArrayList<>();//
		if (vegetableNames != null) {//
			for (String vegetableId : vegetableNames) {
				Vegetables vegetable;
				try {
					vegetable = vegeDao.findById(Integer.valueOf(vegetableId));
					if (vegetable != null) {
						vegetablesNames.add(vegetable.getName());
					}
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
		SauceDao sauceDao = DaoFactory.createSauceDao();
		List<String> saucesNames = new ArrayList<>();//
		if (sauceNames != null) {//
			for (String sauceId : sauceNames) {
				Sauce sauce;
				try {
					sauce = sauceDao.findById(Integer.valueOf(sauceId));
					if (sauce != null) {
						saucesNames.add(sauce.getName());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("name : " + name);
		System.out.println("tel : " + tel);
		System.out.println("email : " + email);
		System.out.println("pickupTime : " + pickupTime);
		System.out.println("productPrice : " + productPrice);
		System.out.println("productName : " + productName);
		System.out.println("meatsName : " + meatsName);
		for (String vegetables1 : vegetablesNames) {
			System.out.println("vegetables : " + vegetables1);
		}
		for (String sauce1 : saucesNames) {
			System.out.println("sauce : " + sauce1);
		}
		// JSPに渡すデータを設定
		request.setAttribute("name", name);
		request.setAttribute("tel", tel);
		request.setAttribute("email", email);
		request.setAttribute("productPrice", productPrice);//
		request.setAttribute("productName", productName);//
		request.setAttribute("meatsName", meatsName);//
		request.setAttribute("vegetablesNames", vegetablesNames);//
		request.setAttribute("saucesNames", saucesNames);//
		request.setAttribute("pickupTime", pickupTime);//
		request
				.getRequestDispatcher("/WEB-INF/view/purchase.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//再度商品情報、受け取り時間、購入者情報を表示
		HttpSession session = request.getSession();

		//ユーザー情報取得,商品IDと価格、肉ID
		Integer userId = (Integer) session.getAttribute("userId");
		Integer selectedProductId = (Integer) session.getAttribute("productId");//10.3
		Integer selectedMeatId = (Integer) session.getAttribute("meatId");

		// リクエストパラメータから受け取り時間を取得し、なければセッションから取得
		String selectedPickupTime = request.getParameter("pickup_time");// 受け取り時間
		if (selectedPickupTime == null) {
			selectedPickupTime = (String) session.getAttribute("pickupTime");
		}
		String pickupDateTime = LocalDateTime.now()//現在の日時を取得、受け取り時間をフォーマット
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
				+ " " + selectedPickupTime + ":00";// ここで選択された時間を追加
		Timestamp pickupTimestamp = Timestamp.valueOf(pickupDateTime);

		// 野菜IDとソースID
		String[] vegetables = (String[]) session.getAttribute("VegetableIds");
		String[] sauce = (String[]) session.getAttribute("SauceIds");
		/*		System.out.println("userId" + userId);
				System.out.println("product" + selectedProductId);
				System.out.println("meat" + selectedMeatId);
				System.out.println("pickupDateTime" + pickupDateTime);
		
				for (String vegetables1 : vegetables) {
					System.out.println("vegetables" + vegetables1);
				}
				for (String sauce1 : sauce) {
					System.out.println("sauce" + sauce1);
				}
		*/
		//DBへデータ保存するもの(purchaseオブジェクトの作成)
		Purchase purchase = new Purchase();
		purchase.setUsersId(userId);
		purchase.setProductsId(selectedProductId); // Integerを設定
		purchase.setMeatsId(selectedMeatId); // Integerを設定
		purchase.setPickupTime(pickupTimestamp);// 現在時刻を取得し、purchaseオブジェクトに設定
		purchase.setPurchasedAt(Timestamp.valueOf(LocalDateTime.now())); // 現在時刻を設定

		//purchaseVegetablesオブジェクト作成
		PurchaseVegetables purchaseVege = new PurchaseVegetables();
		List<Integer> vegetableIds = new ArrayList<>();

		// 取得した野菜IDをIntegerに変換してListに追加
		for (String vegetableId : vegetables) {
			vegetableIds.add(Integer.parseInt(vegetableId));
		}
		purchaseVege.setVegetablesId(vegetableIds);// List<Integer>を設定

		//purchaseSauceオブジェクト作成
		PurchaseSauces purchaseSauce = new PurchaseSauces();
		List<Integer> sauceIds = new ArrayList<>();

		//取得したソースIDをIntegerに変換してListに追加
		for (String sauceId : sauce) {
			sauceIds.add(Integer.parseInt(sauceId));
		}
		purchaseSauce.setSauceId(sauceIds);

		try {
			// Purchaseのデータを保存
			PurchaseDao purchaseDao = DaoFactory.createPurchaseDao();
			purchaseDao.insert(purchase); // 挿入メソッドを呼び出す

			// PurchaseVegetablesのデータを保存
			PurchaseVegetables purchaseVegetables = new PurchaseVegetables();
			purchaseVegetables.setPurchaseId(purchase.getId());
			purchaseVegetables.setVegetablesId(new ArrayList<>());
			//取得した野菜IDをIntegerに変換、Listに追加
			for (String vegetableId : vegetables) {
				purchaseVegetables.getVegetablesId().add(Integer.parseInt(vegetableId));
			}
			PurchaseVegetablesDao purchaseVegeDao = DaoFactory.createPurchaseVegetablesDao();
			purchaseVegeDao.insert(purchaseVegetables);// PurchaseVegetablesを挿入

			// PurchaseSauceのデータを保存
			PurchaseSauces purchaseSauces = new PurchaseSauces();
			purchaseSauces.setPurchaseId(purchase.getId());
			;
			purchaseSauces.setSauceId(new ArrayList<>());
			//取得したソースIDをIntegerに変換、Listに追加
			for (String sauceId : sauce) {
				purchaseSauces.getSauceId().add(Integer.parseInt(sauceId));
			}
			PurchaseSaucesDao purchaseSaucesDao = DaoFactory.createPurchaseSauceDao();
			purchaseSaucesDao.insert(purchaseSauces);//purchaseSaucesを挿入

			request.setAttribute("successMessage", "購入が完了しました。");
			request
					.getRequestDispatcher("/WEB-INF/view/purchaseDone.jsp")
					.forward(request, response);
		} catch (Exception e) {
			// throw new ServletException(e);
			e.printStackTrace();
			request.setAttribute("errorMessage", "購入中にエラーが発生しました。");
			request
					.getRequestDispatcher("/WEB-INF/view/purchase.jsp")
					.forward(request, response);
		}

	}

}
