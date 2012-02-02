package bigtable;

import java.io.IOException;
import java.util.Date;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@SuppressWarnings("serial")
public class empCrudServlet {
	PersistenceManager pm = PMF.get().getPersistenceManager();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		String out="";
		String empno = req.getParameter("empno");
		long start = System.currentTimeMillis();
		//Key empkey = KeyFactory.createKey(empMas.class.getSimpleName(), empno);
		try{
			empMas emas = pm.getObjectById(empMas.class, empno);
				out += emas.getEmpname()+"<i>";
				out += emas.getDepart()+"<i>";
				out += emas.getSection()+"<i>";
				out += emas.getDate()+"<i>";
				long stop = System.currentTimeMillis();
				long revtime = stop - start;
				out += "OK:社員番号="+empno+": "+revtime+"ミリ秒";
				resp.getWriter().println(out);
		}catch(Exception e){
			resp.getWriter().println("NO<i>:参照失敗 社員番号="+empno);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		
		String mode = req.getParameter("mode");
		String empno = req.getParameter("empno");
		String empname = req.getParameter("eempname");
		String depart = req.getParameter("depart");
		String section = req.getParameter("section");
		Date date = new Date();
		long start = System.currentTimeMillis();
		//Key empkey = KeyFactory.createKey(empMas.class.getSimpleName(), empno);
		
		if(mode.equals("add")){
			boolean uniq = false;
			try{
				empMas empdup = pm.getObjectById(empMas.class, empno);
				resp.getWriter().println("NO: キーが重複しています。");
			}catch(JDOObjectNotFoundException e){
				uniq = true;
			}
			if(uniq){
				empMas emas = new empMas(empno, empname, depart, section, date);
				try{
					pm.makePersistent(emas);
					long stop = System.currentTimeMillis();
					long addtime = stop - start;
					resp.getWriter().println("OK:社員番号="+empno+": "+addtime+"ミリ秒");
				}catch(Exception e2){
					resp.getWriter().println("NO:登録不成功 エラー:");
				}
			}
		}else if(mode.equals("upd")){
			try{
				empMas emas = pm.getObjectById(empMas.class, empno);
				emas.setEmpname(empname);
				emas.setDepart(depart);
				emas.setSection(section);
				emas.setDate(date);
			}catch(Exception e){
				resp.getWriter().println("NO:更新失敗 Error:"+e);
			}finally{
				pm.close();
				resp.getWriter().println("OK:更新成功 社員番号="+empno);
				pm = PMF.get().getPersistenceManager();
			}
		}else if(mode.equals("del")){
			try{
				empMas emas = pm.getObjectById(empMas.class, empno);
				pm.deletePersistent(emas);
				resp.getWriter().println("OK:削除成功 社員番号=");
			}catch(Exception e){
				resp.getWriter().println("NO:削除失敗 Error:"+e);
			}
		}
	}
}
