package schedule;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import calendar.Time;

import bigtable.PMF;

public class Plan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String name;
	private String title;
	private int year;
	private int month;
	private int day;
	private String place;
	private String memo;
	private String stime;
	private String etime;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String[] param = new String[4];
		HttpSession hs = req.getSession();
		name = (String) hs.getAttribute("NAME");
		boolean ck = true;

		// TITLE
		param[0] = req.getParameter("PLAN");
		if (param == null || param[0].length() == 0) {
			ck = false;
		} else {
			title = param[0];
		}

		// DATE
		param[0] = req.getParameter("YEAR");
		param[1] = req.getParameter("MONTH");
		param[2] = req.getParameter("DAY");
		year = Integer.parseInt(param[0]);
		month = Integer.parseInt(param[1]);
		day = Integer.parseInt(param[2]);

		// TIME
		Time t = new Time();
		param[0] = req.getParameter("SHOUR");
		param[1] = req.getParameter("SMINU");
		param[2] = req.getParameter("EHOUR");
		param[3] = req.getParameter("EMINU");
		if(param[0]==null || param[0].length()==0){
			stime = t.getHour() + ":" + param[1];
		}
		if(param[2]==null || param[2].length()==0){
			stime = t.getHour() + ":" + param[1];
		}
		stime = param[0] + ":" + param[1];
		etime = param[2] + ":" + param[3];

		// PLACE
		param[0] = req.getParameter("PLACE");
		place = param[0];

		// MEMO
		param[0] = req.getParameter("MEMO");
		memo = param[0];

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Schedule sh = Schedule.makeSchedule(name, title, year, month, day, stime, etime,
				place, memo);
		if (ck) {
			try {
				pm.makePersistent(sh);
			} finally {
				pm.close();
				resp.sendRedirect("/cd/calendar");
			}
		} else {
			pm.close();
			resp.sendRedirect("/cd/form");
		}

	}
}
