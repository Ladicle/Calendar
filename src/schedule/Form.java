package schedule;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import calendar.PrintCalendar;

@SuppressWarnings("serial")
public class Form extends HttpServlet {
	private PrintCalendar pc;
	private int year, month, day;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		HttpSession sh = req.getSession( );
		String name = (String) sh.getAttribute("NAME");
		pc = new PrintCalendar(name);
		StringBuffer sb = new StringBuffer();
		
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
		
		param = req.getParameter("MONTH");
		if(param == null || param.length() == 0){
			month = -999;
		}else{
			try{
				month = Integer.parseInt(param);
			}catch(NumberFormatException e){
				month = -999;
			}
		}
		
		param = req.getParameter("DAY");
		if(param == null || param.length() == 0){
			day = -999;
		}else{
			try{
				day = Integer.parseInt(param);
			}catch(NumberFormatException e){
				day = -999;
			}
		}
		
		if(year == -999 || month == -999 || day == -999){
			year = pc.getToYear();
			month = pc.getToDate() + 1;
			day = pc.getToDate();
		}
		
		sb.append(pc.head());
		sb.append("<link rel=\"stylesheet\" href=\"/css/plan.css\" />");
		sb.append("<script type=\"text/javascript\" src=\"/js/form.js\"></script>");
		sb.append(pc.wrap());
		sb.append(printForm());
		
		out.println(new String(sb));
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doGet(req, resp);
	}
	
	/** 登録フォーム(POST)
	 * 
	 *	日付：YEAR,MONTH,DAY
	 *  時間：SHOUR,SMINU, EHOUR, EMINU
	 *  主題；PLAN
	 *  場所：PLACE
	 *  詳細：MEMO
	 *  
	 */
	private String printForm(){
		StringBuffer sb = new StringBuffer();
		sb.append("<div id=\"back\" class=\"decorate\"><a href=\"/cd/calendar\">カレンダへ戻る</a></div><div id=\"planform\" class=\"decorate\">");
		sb.append("<form method=\"post\" action=\"/cd/Plan\">");
		sb.append("<h2>スケジュール登録</h2>");
		sb.append("<table>");
        sb.append("<tr><td nowrap>タイトル</td>");
        sb.append("<td><input type=\"text\" name=\"PLAN\" value=\"\" size=\"30\" maxlength=\"100\"></td></tr>");
		sb.append("<tr><td nowrap>日付</td><td><select name=\"YEAR\">");
		for(int i = (year-1); i <= (year+6); i++){
			sb.append("<option value=\"");
			sb.append(i);
			sb.append("\"");
			if(i == year){
				sb.append(" selected");
			}
			sb.append(">"+i+"年");
		}
		sb.append("</select><select name=\"MONTH\">");
		for(int i = 1; i<= 12; i++){
			sb.append("<option value=\""+i+"\"");
			if(i==month){
				sb.append(" selected");
			}
			sb.append(">"+i+"月");
		}
		sb.append("</select>");
		sb.append("<select name=\"DAY\">");
		for(int i=1; i<=pc.getEndDay(); i++){
			sb.append("<option value=\""+i+"\"");
			if(i==day){
				sb.append(" selected");
			}
			sb.append(">"+i+"日");
		}
		sb.append("</select></td></tr><tr><td nowrap>時刻</td>");
		sb.append("<td><select name=\"SHOURE\">");
		sb.append("<option value=\"\" selected>--時");
		for(int i=0; i<=23; i++){
			sb.append("<option value=\""+i+"\">"+i+"時");
		}
		sb.append("</select><select name=\"SMINU\"><option value=\"00\">00分");
		sb.append("<option value=\"30\">30分</select> -- ");
        sb.append("<select name=\"EHOUR\">");
        sb.append("<option value=\"\" selected>--時");
        for (int i = 0 ; i <= 23 ; i++){
            sb.append("<option value=\""+i+"\">"+i+"時");
        }
        sb.append("</select><select name=\"EMINU\">");
        sb.append("<option value=\"00\">00分");
        sb.append("<option value=\"30\">30分");
        sb.append("</select></td></tr>");
        sb.append("<tr><td nowrap>場所</td>");
        sb.append("<td><input type=\"text\" name=\"PLACE\" value=\"\" size=\"30\" maxlength=\"100\"></td></tr>");
        sb.append("<tr><td valign=\"top\" nowrap>詳細</td>");
        sb.append("<td><textarea name=\"MEMO\" cols=\"30\" rows=\"10\" wrap=\"virtual\"></textarea></td>");
        sb.append("</tr></table><p>");
        sb.append("<input type=\"submit\" name=\"Register\" value=\"登録\">  ");
        sb.append("<input type=\"reset\" value=\"クリア\"><p></form></div></div></div>");
        
        return new String(sb);
	}
}
