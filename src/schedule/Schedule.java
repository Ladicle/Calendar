package schedule;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Schedule {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long No;
	@Persistent
	private String name;
	@Persistent
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value="true")
	private String title;
	@Persistent
	private int year;
	@Persistent
	private int month;
	@Persistent
	private int day;
	@Persistent
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value="true")
	private String stime;
	@Persistent
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value="true")
	private String etime;
	@Persistent
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value="true")
	private String place;
	@Persistent
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value="true")
	private String memo;
	
	/* PrivateConstructor */
	private Schedule(String name, String title, int year, int month, int day, String stime, String etime, String place, String memo){
		this.name=name;
		this.title=title;
		this.year=year;
		this.month=month;
		this.day=day;
		this.stime=stime;
		this.etime=etime;
		this.place=place;
		this.memo=memo;
	}
	
	/* GetInstance */
	public static Schedule makeSchedule(String name, String title, int year, int month, int day, String stime, String etime, String place, String memo){
		return new Schedule(name, title, year, month, day, stime, etime, place, memo);
	}
	
	/* Getter */
	public Long getNo() {return No;}
	public String getName() {return name;}
	public String getTitle() {return title;}
	public int getYear(){return year;}
	public int getMonth(){return month;}
	public int getDay(){return day;}
	public String getSTime() {return stime;}
	public String getETime(){return etime;}
	public String getPlace() {return place;}
	public String getMemo() {return memo;}
	
	/* Setter */
	public void setNo(Long no) {No = no;}
	public void setName(String name) {this.name = name;}
	public void setTitle(String title) {this.title = title;}
	public void setYear(int year){this.year=year;}
	public void setMonth(int month){this.month = month;}
	public void setDay(int day){this.day = day;}
	public void setSTime(String stime) {this.stime = stime;}
	public void setETime(String etime){this.etime = etime;}
	public void setPlace(String place) {this.place = place;}
	public void setMemo(String memo) {this.memo = memo;}

}
