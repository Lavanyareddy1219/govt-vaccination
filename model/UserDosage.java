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
@Table(name = "userdoses")
public class UserDosage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uservaccinationId;
	private Integer userRegistrationId;
	private String uservaccinationType;

	private boolean userFirstDose;


	private boolean userSecondDose;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userFirstDoseDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date userSecondDoseDate;
	
    private String userAEFIpostFirstdosedescription;

	private boolean userAEFIpostFirstdose;


}
