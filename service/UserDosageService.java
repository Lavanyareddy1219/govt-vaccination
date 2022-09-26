package govt.vaccination.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import govt.vaccination.constants.CommonConstants.Gender;
import govt.vaccination.model.UserDosage;
import govt.vaccination.repository.UserDosageRepository;
import govt.vaccination.utils.DateUtils;
import govt.vaccination.utils.VaccinationUtils;

@Service
public class UserDosageService {
	@Autowired
	UserDosageRepository 	userDosageRepository;
	@Autowired
	VaccinationUtils vaccinationUtils;


	public List<UserDosage> getAlldosesList() {
		return userDosageRepository.findAll();
	}


	public UserDosage save(UserDosage userDosage) throws ParseException {

		boolean isUserFirstDose = userDosage.isUserFirstDose();
		boolean isUserSecondDose = userDosage.isUserSecondDose();
//		if(isUserFirstDose && isUserSecondDose ) {
//			//display error messages
//			System.err.println("Both Dosage cannot be possible for a single user");
//			return null;
//			
//		}
		
		
		if(isUserFirstDose == false && isUserSecondDose == false) {
			System.err.println("Both Dosage cannot be send empty while inserting data");
			return null;

	}
		if(isUserFirstDose == true && isUserSecondDose == true) {
			userDosage.setUserFirstDoseDate(DateUtils.getTodayDate());
			userDosage.setUserSecondDoseDate(DateUtils.getTodayDate());
        return  userDosageRepository.save(userDosage);

			
		}
		if(isUserFirstDose == false && isUserSecondDose == false) {
			System.err.println("Both Dosage cannot be send empty while inserting data");
			return null;

	}
		
		if(isUserFirstDose) {
		userDosage.setUserFirstDoseDate(DateUtils.getTodayDate());
		
		return userDosageRepository.save(userDosage);

		}
		if(isUserSecondDose) {
		userDosage.setUserSecondDoseDate(DateUtils.getTodayDate());
		return userDosageRepository.save(userDosage);
		}
		return null;
	}


	

	public Map<String, Object> getUserDosageStatistics() throws ParseException {
		Map<String, Object> responseMap = new HashMap<>();

		try {
		List<UserDosage> userDosageList = getAlldosesList();
		
		if(userDosageList == null) {
			responseMap.put("Status", "Dosage is empty");
      return responseMap;
		}
		responseMap.put("totalDosageStrength", userDosageList.size());
		
		responseMap.put(Gender.FEMALE,
				vaccinationUtils.getGenderListCountForDosage(Gender.FEMALE, userDosageList));

		responseMap.put(Gender.MALE,
				vaccinationUtils.getGenderListCountForDosage(Gender.MALE, userDosageList));
	

		
		

		responseMap.put("user_vaccinatedType", VaccinationUtils.getUserDosageListCountBasedOnDoseCount(userDosageList));
		

		// Last 24 hours
		Date yesterdayDate = DateUtils.minusDays(DateUtils.getTodayDate(), 1);

		responseMap.put("24-hours", VaccinationUtils.getUserDosageListCountBasedOnDateCount(yesterdayDate, userDosageList));

		// Last 2 Days
		Date twoDaysBackDate = DateUtils.minusDays(DateUtils.getTodayDate(), 2);

		responseMap.put("2-days", VaccinationUtils.getUserDosageListCountBasedOnDateCount(twoDaysBackDate, userDosageList));

		Date monthBackDate = DateUtils.minusDays(DateUtils.getTodayDate(), 30);
		responseMap.put("1-month", VaccinationUtils.getUserDosageListCountBasedOnDateCount(monthBackDate, userDosageList));

         responseMap.put("AEFI-cases",VaccinationUtils.getAEFIPostFirstDosage(userDosageList)) ;

       //  responseMap.put("AEFI-cases",VaccinationUtils.getAEFIPostSecondDosage(userDosageList)) ;

		}catch(Exception e) {
			responseMap.put("status",e.getMessage());
			System.out.println(e.getMessage());
		}
		return responseMap;

	}


	

}
