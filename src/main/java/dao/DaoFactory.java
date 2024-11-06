package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoFactory {

	public static ProductsDao createProductDao() {
		return new ProductsDaoImpl(getDataSource());
	}

	public static MeatsDao createMeatsDao() {
		return new MeatsDaoImpl(getDataSource());
	}

	public static VegetablesDao createVegetablesDao() {
		return new VegetablesDaoImpl(getDataSource());
	}

	public static SauceDao createSauceDao() {
		return new SauceDaoImpl(getDataSource());
	}

	public static UsersDao createUsersDao() throws Exception {
		return new UsersDaoImpl(getDataSource());
	}
	
	public static PurchaseDao createPurchaseDao()throws Exception{
		return new PurchaseDaoImpl(getDataSource());
	}

	
	public static PurchaseVegetablesDao createPurchaseVegetablesDao() throws Exception{
		return new PurchaseVegetablesDaoImpl(getDataSource());
	}
	
	public static PurchaseSaucesDao createPurchaseSauceDao() throws Exception{
		return new PurchaseSaucesDaoImpl(getDataSource());
	}
	
	public static PurchaseHistoryDao createPurchaseHistoryDao() throws Exception{
		return new PurchaseHistoryDaoImpl(getDataSource());
	}
	
	private static DataSource getDataSource() {
		InitialContext ctx = null;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/kadai1_db");
		} catch (NamingException e) {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e1) {
					throw new RuntimeException(e1);
				}
			}
			throw new RuntimeException(e);
		}
		return ds;
	}

}
