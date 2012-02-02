package calendar;

import java.util.List;

import javax.jdo.PersistenceManager;

import schedule.Schedule;
import bigtable.PMF;

public class PrintCalendar extends Day{
	private String name;
	
	public PrintCalendar(String name){
		super();
		this.name=name;
	}
	public PrintCalendar(int year, int month, String name){
		super(year, month);
		this.name=name;
	}
	
	/** カレンダーページの作成 */
	public StringBuffer make(){
		StringBuffer sb = new StringBuffer();
		sb.append(head());
		sb.append(wrap());
		
		/* LEFT */
		sb.append("<div id=\"left\" class=\"area\"><div id=\"min-calendar\" class=\"blocks decorate\">");
		sb.append("<div class=\"headbar header\"><h2>Calendar</h2><div class=\"icon\"></div></div>");
		sb.append("<h3>"+getYear()+"/"+(getMonth()+1)+"</h3><div id=\"minc-main\">"+calender()+"</div></div>");
		sb.append("<div id=\"category\" class=\"blocks decorate\">");
		sb.append("<div class=\"headbar header\"><h2>Category</h2><div class=\"icon\"></div></div></div></div>");
		
		/* CENTER */
		int[] last = getLMonth();
		int[] next = getNMonth();
		sb.append("<div id=\"center\" class=\"area\">");
		sb.append("<div id=\"calendar\" class=\"decorate\">");
		sb.append("<div id =\"c-header\">");
		sb.append("<div id=\"change\"></div>");
		sb.append("<div class=\"change-month\">");
		sb.append("<a href=\"/cd/calendar?YEAR="+last[0]+"&MONTH="+last[1]+"\">");
		sb.append("<img src=\"/pic/left.png\" /></a>"+printLMonth()+"</div>");
		sb.append("<h2 id=\""+getYear()+"\" name=\""+(1+getMonth())+"\">"+getYear()+"."+(getMonth()+1)+"</h2>");
		sb.append("<div class=\"change-month\">"+printNMonth());
		sb.append("<a href=\"/cd/calendar?YEAR="+next[0]+"&MONTH="+next[1]+"\">");
		sb.append("<img src=\"/pic/right.png\" /></a></div>");
		sb.append("<ul id=\"tabs\">");
		sb.append("<a href=\"/cd/calendar?YEAR="+getToYear()+"&MONTH="+(getToMonth()+1)+"\">");
		sb.append("<li class=\"header\">Today</li></a>");
		sb.append("<li class=\"header select-tab\">Month</li>");
		sb.append("<li class=\"header\">Week</li>");
		sb.append("<li class=\"header\">list</li>");
		sb.append("</ul>");
		sb.append("</div>");
		sb.append("<div id=\"main-c\">");
		sb.append(calender());
		sb.append("</div></div></div>");
		sb.append("<div id=\"right\" class=\"area\"><div id=\"day\" class=\"decorate\">");
		sb.append("</div></div></div></div></body></html>");
		return sb;
	}
	
	/** HEAD(BODY前) */
	public String head(){
		StringBuffer sb = new StringBuffer();
		
		/* 設定 */
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		sb.append("<html><head>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
		sb.append("<title>spuz</title>");
		sb.append("<link rel=\"shortcut icon\" href=\"/pic/favicon.ico\" />");
		sb.append("<link rel=\"stylesheet\" href=\"/css/reset.css\" />");
		sb.append("<link rel=\"stylesheet\" href=\"/css/calendar.css\" />");
		sb.append("<script type=\"text/javascript\" src=\"/js/jq.js\"></script>");
		sb.append("<script type=\"text/javascript\" src=\"/js/ui.js\"></script>");
		sb.append("<script type=\"text/javascript\" src=\"/js/calendar.js\"></script>");
		
		return new String(sb);
	}
	
	/** 外枠 */
	public String wrap(){
		StringBuffer sb = new StringBuffer();
				
		/* BODY */
		sb.append("</head><body><div id=\"header\" class=\"header\">");
		sb.append("<a href=\"/cd/calendar\"><img src=\"/pic/logo3.png\" id=\"logo\" /></a>");
		sb.append("<ul><li id=\"username\"  class=\"bico ui-icon-triangle-1-s\"><a href=\"/Logout\"><strong>"+name+"</strong></a>");
		sb.append("<span class=\"iccon\"><img src=\"/pic/bicon.png\" /></span></li>");
		sb.append("<li><img src=\"/pic/gear.png\" /></li></ul></div>");
		sb.append("<div id=\"wrap\"><div id=\"bg\">");
		
		return new String(sb);
	}
	
	/** カレンダーテーブル */
	public String calender(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		StringBuffer sb = new StringBuffer();
		sb.append("<table>");
		sb.append("<tr id=\"week\">");
		sb.append("<td>月</td><td>火</td><td>水</td><td>木</td><td>金</td><td>土</td><td>日</td>");
		sb.append("</tr>");
		
		//前月の出力
		int row = 0;
		int col = getFirst();
		sb.append("<tr>");
		for(int i=0; i<col; i++){
			sb.append("<td class=\"last-month\">"+(getLastDay()-(col-i-1))+"</td>");
		}
		// 当月の出力
		String today;
		for(int day=1; day<getEndDay(); day++){
			System.out.println(name);
			String query = "select from " + Schedule.class.getName()+ " where year == "+getYear()+" && month =="+(getMonth()+1)+" && day =="+day+" && name =='"+name+"'";
			@SuppressWarnings("unchecked")
			List<Schedule> data = (List<Schedule>) pm.newQuery(query).execute();
			String plan = "";
			if(!data.isEmpty()){
				for(Schedule s : data){
					//System.out.println("<span>"+s.getTitle()+"</span>");
					plan = plan+"<div id=\"sch\" class=\"all-radius\">"+s.getTitle()+"</div>";
				}
			}

			
			if(day == getToDate() && getMonth() == getToMonth() && getYear() == getToYear()){
				today = " today";
			}else{
				today="";
			}
			switch(col){
			case 5:
				sb.append("<td class=\"satday this-month"+today+"\" "+"id=\""+day+"\"><a href=\"/cd/form?YEAR="+getYear()+"&MONTH="+(getMonth()+1)+"&DAY="+day+"\"><div class=\"cheader\"><span>"+day+"</span></div><div class=\"cbody\">"+plan+"</div></a></td>");
				col++;
				break;
			case 6:
				sb.append("<td class=\"sunday this-month"+today+"\" "+"id=\""+day+"\"><a href=\"/cd/form?YEAR="+getYear()+"&MONTH="+(getMonth()+1)+"&DAY="+day+"\"><div class=\"cheader\"><span>"+day+"</span></div><div class=\"cbody\">"+plan+"</div></a></td>");
				sb.append("</tr>");
				if(row!=(getMRow()-1)){
					sb.append("<tr>");
				}
				row++;
				col=0;
				break;
			default:
				sb.append("<td class=\"normalday this-month"+today+"\" "+"id=\""+day+"\"><a href=\"/cd/form?YEAR="+getYear()+"&MONTH="+(getMonth()+1)+"&DAY="+day+"\"><div class=\"cheader\"><span>"+day+"</span></div><div class=\"cbody\">"+plan+"</div></a></td>");
				col++;
				break;
			}
		}
		/* 来月の出力 */
		int day = 1;
		for(int i= col; i<7; i++){
			sb.append("<td class=\"next-month\">"+day+"</td>");
			day++;
		}
		sb.append("</tr></table>");
		pm.close();
		return new String(sb);
	}
	
	/** 前月のチェック */
	public String printLMonth(){
		int[] last = getLMonth();
		return last[0] + "/" + last[1];
	}
	
	/** 先月の年と月
	 * 
	 * @return last[0] 年
	 * @return last[1] 月
	 * 
	 */
	public int[] getLMonth(){
		int last[] = new int[2];
		if(getMonth()==0){
			last[0] = getYear()-1;
			last[1] = 12;
		}else{
			last[0] = getYear();
			last[1] = getMonth();
		}
		return last;
	}
	
	/** 来月のチェック */
	public String printNMonth(){
		int[] next = getNMonth();
		return next[0] + "/" + next[1];
	}
	
	/** 来月の年と月
	 * 
	 * @return next[0] 年
	 * @return next[1] 月
	 * 
	 */
	public int[] getNMonth(){
		int[] next = new int[2];
		if(getMonth()==11){
			next[0] = getYear()+1;
			next[1] = 1;
		}else{
			next[0] = getYear();
			next[1] = getMonth()+2;
		}
		return next;
	}
}
