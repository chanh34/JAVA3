package filter;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class I18nFilter  implements Filter{
	
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response,
	                         FilterChain chain)
	            throws IOException, ServletException {

	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpSession session = req.getSession();

	        // Đổi ngôn ngữ
	        String lang = req.getParameter("lang");
	        if (lang != null) {
	            session.setAttribute("lang", lang);
	        }

	        String current = (String) session.getAttribute("lang");
	        if (current == null) current = "vi";

	        Locale locale = new Locale(current);
	        ResourceBundle global = ResourceBundle.getBundle("i18n.global", locale);
	        ResourceBundle home = ResourceBundle.getBundle("i18n.home", locale);

	        req.setAttribute("global", global);
	        req.setAttribute("home", home);

	        chain.doFilter(request, response);
	    }

}
