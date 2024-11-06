package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
//@WebFilter("/*")全部作り終わったら*を入れる
@WebFilter("/*")
public class AuthFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");//リクエストの文字化け防止
		response.setCharacterEncoding("UTF-8");//レスポンスの文字化け防止		
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    HttpSession session = req.getSession();
    
    String uri = req.getRequestURI();
    //css, javaScriptのリクエストを除外
    if(uri.endsWith(".css")|| uri.endsWith(".js")) {
  		chain.doFilter(request, response);
  		return;//フィルター処理をスキップ
    }
    
    // ログインしていない場合、/login以外のページにはアクセスできないようにする
    if(!uri.endsWith("/login") && !uri.endsWith("/addUser")) {
    	if(session.getAttribute("loginId") == null) {
    		res.sendRedirect(req.getContextPath() + "/login");
    		return;
    	}
    }
    
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
