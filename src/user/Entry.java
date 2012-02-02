package user;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bigtable.PMF;

public class Entry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		String mail = req.getParameter("mail");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Account ma = Account.makeAccount(name, pass, mail);

		if (name != null && !name.equals("") && pass != null && !pass.equals("") && mail != null && !mail.equals("")) {
			String query = "select from "+ Account.class.getName() +" where name == '" + name + "'";
			@SuppressWarnings("unchecked")
			List<Account> data = (List<Account>) pm.newQuery(query).execute();
			
			if(!data.isEmpty()){
				pm.close();
				resp.sendRedirect("/error");
			}
			
			try {
				pm.makePersistent(ma);
			} finally {
				pm.close();
				resp.sendRedirect("/success");
			}
		}else{
			pm.close();
			resp.sendRedirect("/error");
		}
	}
}
