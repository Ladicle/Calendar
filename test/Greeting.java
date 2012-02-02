package bigtable;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Greeting {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String author;
	@Persistent
	private String content;
	@Persistent
	private Date date;
	
	private Greeting(String author, String content, Date date){
		this.author = author;
		this.content = content;
		this.date = date;
	}
	
	public static Greeting makeInstance(String author, String content, Date date){
		return new Greeting(author, content, date);
	}
	
	public Long getId(){return id;}
	public String getName(){return author;}
	public String getContent(){return content;}
	public Date getDate(){return date;}
	
	public void setName(String author){
		this.author = author;
	}
	public void setContent(String content){
		this.content = content;
	}
	public void setDate(Date date){
		this.date = date;
	}
}
