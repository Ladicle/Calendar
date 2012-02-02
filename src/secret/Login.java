package secret;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.Account;

import bigtable.PMF;

public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Long id;
	
	/* CREATE SESSION */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws IOException, ServletException{
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		
		HttpSession hs = req.getSession();
		
		boolean ck = AccountCheck(name, pass);
		if(ck){
			hs.setAttribute("ID", id);
			hs.setAttribute("NAME", name);
			hs.setAttribute("PASS", pass);
			
			String tg = (String) hs.getAttribute("WHERE");
			if(tg != null){
				resp.sendRedirect(tg);
			}
			resp.sendRedirect("/cd/calendar");
		}else{
			resp.sendRedirect("/error");
		}
	}

	/* COMPARISON[INPUT==DB] */
	private boolean AccountCheck(String name, String pass) {
		if(name!=null && name.length()!=0 && pass!=null && pass.length()!=0){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			String query = "select from "+ Account.class.getName() +" where name == '" + name + "'";
			@SuppressWarnings("unchecked")
			List<Account> data = (List<Account>) pm.newQuery(query).execute();
			Account ac;
			
			if(!data.isEmpty()){
				ac = data.get(0);
				if(pass.equals(ac.getPass())){
					id = ac.getId();
					return true;
				}
			}
		}
		return false;
	}
}
