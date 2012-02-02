package bigtable;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class empMas {
	@PrimaryKey
	//@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	//private Key empkey;
	@Persistent
	private String empno;
	@Persistent
	private String empname;
	@Persistent
	private String depart;
	@Persistent
	private String section;
	@Persistent
	private Date date;
	
	public empMas(String empno, String empname, String depart, String section, Date date){
		//this.empkey = empkey;
		this.empno = empno;
		this.empname = empname;
		this.depart = depart;
		this.section = section;
		this.date = date;
	}
	
	//public  Key getEmpno(){return empkey;}
	public String getEmpno(){return empno;}
	public String getEmpname(){return empname;}
	public String getDepart(){return depart;}
	public String getSection(){return section;}
	public Date getDate(){return date;}
	
	//public void setEmpno(Key empkey){this.empkey = empkey;}
	public void setEmpno(String empno){this.empno = empno;}
	public void setEmpname(String empname){this.empname=empname;}
	public void setDepart(String depart){this.depart=depart;}
	public void setSection(String section){this.section=section;}
	public void setDate(Date date){this.date=date;}
}
