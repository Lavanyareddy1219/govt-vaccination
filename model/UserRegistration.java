package govt.vaccination.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;



@Data
@Entity
@Table(name = "users")
public class UserRegistration {
	public static final Object password = null;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userRegistrationId;
	private String user_username;
	private String user_password;
	private String user_gender;
	private String user_state;
	private String user_mobile;
	private String user_email;
	@Temporal(TemporalType.TIMESTAMP)
	private Date user_userregistrationdate;
	public void setUser_userregistrationdate(String string) {
		
		
	}
	public Object get() {
		
		return null;
	}
	public void setUser_userregistrationdate(Date todayDate) {
		
		
	}
	public static Object getpassword() {
		
		return null;
	}
	
	
	
}