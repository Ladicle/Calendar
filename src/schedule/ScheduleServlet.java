package schedule;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import calendar.PrintCalendar;

@SuppressWarnings("serial")
public class ScheduleServlet extends HttpServlet {
	PrintCalendar pc;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		int year;
		String param = req.getParameter("YEAR");
		if(param== null || param.length() == 0){
			year = -999;
		}else{
			try{
				year = Integer.parseInt(param);
			}catch(NumberFormatException e){
				year = -999;
			}
		}
		
		int month;
		param = req.getParameter("MONTH");
		if(param == null || param.zlength() == 0){
			month = -999;
		}else{
			try{
				month = Integer.parseInt(param);
			}catch(NumberFormatException e){
				month = -999;
			}
		}
		HttpSession sh = req.getSession( );
		String name = (String) sh.getAttribute("NAME");
		
		if(year == -999 || month == -999){
			pc = new PrintCalendar(name);
		}else{
			pc = new PrintCalendar(year, month, name);
		}
		
		out.println(new String(pc.make()));
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req, resp);
	}
}
