package secret;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	/* SESSION CHECK */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain fc) throws IOException, ServletException {

		try {
		      String target = ((HttpServletRequest)req).getRequestURI();
		      HttpSession hs = ((HttpServletRequest)req).getSession();
		      
			if (hs == null) {
				hs = ((HttpServletRequest)req).getSession(true);
				hs.setAttribute("WHERE", target);
				((HttpServletResponse)resp).sendRedirect("/Login");
			} else {
				Object ck = hs.getAttribute("ID");
				if(ck==null){
					((HttpServletResponse) resp).sendRedirect("/Login");
				}
			}
			fc.doFilter(req, resp);
		} catch (ServletException se) {
		} catch (IOException e) {
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
	}

}
