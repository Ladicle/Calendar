package calendar;

import java.util.Calendar;

public class Time {
	Calendar c;
	private int hour,minu;
	
	/** 現在の時刻で設定 */
	public Time(){
		c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR);
		minu = c.get(Calendar.MINUTE);
	}
	/** 指定の時刻で設定 */
	public Time(int hour, int minu){
		this.hour = hour;
		this.minu = minu;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getHour() {
		return hour;
	}
	public void setMinu(int minu) {
		this.minu = minu;
	}
	public int getMinu() {
		return minu;
	}
}
