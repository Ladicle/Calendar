package bigtable;

import java.io.IOException;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sign extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException{
		String author = req.getParameter("author");
		String content = req.getParameter("content");
		Date date = new Date();
		Greeting greeting = Greeting.makeInstance(author, content, date);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try{
			pm.makePersistent(greeting);
		}finally{
			pm.close();
		}
		resp.sendRedirect("/show");
	}
}
