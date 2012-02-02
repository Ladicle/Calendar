package user;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Account {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private String name;
	@Persistent
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value="true")
	private String pass;
	@Persistent
	private String mail;
	@Persistent
	private int roll;
	
	{
		roll=0;
	}
	
	/* PrivateConstructor */
	private Account(String name, String pass, String mail){
		this.name=name;
		this.pass=pass;
		this.mail=mail;
	}
	
	/* GetInstance */
	public static Account makeAccount(String name, String pass, String mail){
		return new Account(name, pass, mail);
	}
	
	/* Getter */
	public Long getId(){return id;}
	public String getName(){return name;}
	public String getPass(){return pass;}
	public String getMail(){return mail;}
	public int getRoll(){return roll;}
	
	/* Setter */
	public void setPass(String pass){this.pass=pass;}
	public void setMail(String mail){this.mail=mail;}
	public void setRoll(int roll){this.roll=roll;}

}
