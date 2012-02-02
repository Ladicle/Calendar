package calendar;

import java.util.Calendar;

public class Day {
	private int year;
	private int month;
	private int day;
	private int[][] mtx = new int[6][7];
	private int mRow;
	private int toDay;
	private int toMonth;
	private int toYear;
	private int stDay;//曜日
	private int lsDay;//日付
	private int llDay;//日付
	private int first;
	private int last;
	private Calendar c;
	
	/** 現在の日付で設定 */
	public Day(){
		c = Calendar.getInstance();
		toDay = day = c.get(Calendar.DATE);
		toYear = year= c.get(Calendar.YEAR);
		toMonth = month = c.get(Calendar.MONTH);
		make();
	}
	/** 指定の年で設定 */
	public Day(int year, int month){
		c = Calendar.getInstance();
		toDay = day = c.get(Calendar.DATE);
		toMonth = c.get(Calendar.MONTH);
		toYear = c.get(Calendar.YEAR);
		this.year = year;
		this.month = month-1;
		make();
	}
	public Day(int year, int month, int day){
		c = Calendar.getInstance();
		this.day = toDay = day = c.get(Calendar.DATE);
		toMonth = c.get(Calendar.MONTH);
		toYear = c.get(Calendar.YEAR);
		this.year = year;
		this.month = month-1;
		make();
	}
	
	/** カレンダーの再設定 */
	public void setCalendar(int year, int month){
		c.clear();
		this.year = year;
		this.month = month-1;
		make();
	}
	
	/** 日付の再設定 */
	public void setDate(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	/** 年の設定 */
	public void setYear(int year){
		this.year = year;
	}
	
	/** 月の設定 */
	public void setMonth(int month){
		this.month = month;
	}
	
	/** 日の設定 */
	public void setDay(int day){
		this.day = day;
	}
	
	/** カレンダー配列 */
	public int[][] getCalendar(){
		return mtx;
	}
	
	/** 指定月のカレンダー行数 */
	public int getMRow(){
		return mRow+1;
	}
	
	/** 今日の日付 */
	public int getToDate(){
		return toDay;
	}
	
	/** 今日の月 */
	public int getToMonth(){
		return toMonth;
	}
	
	/** 今日の年 */
	public int getToYear(){
		return toYear;
	}
	
	/** 年の取得 */
	public int getYear(){
		return year;
	}
	
	/** 月の取得 */
	public int getMonth(){
		return month;
	}
	
	/** 日の取得 */
	public int getDay(){
		return day;
	}
	
	/** 前月の終了日 */
	public int getLastDay(){
		return llDay;
	}
	
	/** 今月の終了日 */
	public int getEndDay(){
		return lsDay+1;
	}
	
	/** 月初め */
	public int getFirst(){
		return first;
	}
	
	/** 月終わり */
	public int getLast(){
		return last;
	}
	
	/** カレンダーの表示 */
	public void print(){
		System.out.print("\n"+year+"年"+(month+1)+"月");
		System.out.println("\n月  火  水  木  金  土  日");
		for(int i=0; i<getMRow(); i++){
			for(int j=0; j<mtx[i].length; j++){
				System.out.printf("%2d  ",mtx[i][j]);
			}
			System.out.println();
		}
	}
	
	//カレンダーの作成
	private void make(){
		//月初め
		c.set(year, month, 1);
		stDay = c.get(Calendar.DAY_OF_WEEK);
		
		//月終わり
		lsDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//前月終わり
		if(month==0){
			c.set(year-1, 11, 1);
		}else{
			c.set(year, month-1, 1);
		}
		llDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//カレンダー配列
		int row = 0, col;
		if(stDay == 1){
			col = 6;
		}else{
			col = stDay - 2;
		}
		first = col;

		//前月
		for(int i=0; i<col; i++){
			mtx[0][i] = llDay-(col-i-1);
		}
		
		//当月
		for(int day=1; day<=lsDay; day++){
			mtx[row][col] = day;
			if(col == 6){
				row++;
				col=0;
			}else{
				col++;
			}
		}
		
		last = col;
		
		//来月
		int day = 1;
		for(int i=col; i<7; i++){
			mtx[row][i]= day;
			day++;
		}
		
		mRow = row;
	}
	
	/** 曜日を求める */
	public int dayOfWeek(){
		int y = year;
		int m = month;
		if(m==1 || m==2){
			y--;
			m+=12;
		}
		return(y+y/4-y/100+y/400+(13*m+8)/5+day)%7;
	}
	
	/** 日付の比較 */
	public boolean equalTo(Day d){
		return year == d.year && month==d.month && day==d.day;
	}
}
