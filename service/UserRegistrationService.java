package govt.vaccination.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import govt.vaccination.constants.CommonConstants.Gender;
import govt.vaccination.model.UserRegistration;
import govt.vaccination.repository.UserRegistrationRepository;
import govt.vaccination.utils.DateUtils;
import govt.vaccination.utils.VaccinationUtils;

@Service
public class UserRegistrationService {

	@Autowired
	UserRegistrationRepository userRegistrationRepository;

	public List<UserRegistration> getAllUserRegistration() {
		return userRegistrationRepository.findAll();
	}

	public UserRegistration save(UserRegistration userRegistration) throws ParseException {
		userRegistration.setUser_userregistrationdate(DateUtils.getTodayDate());
		return userRegistrationRepository.save(userRegistration);
	}

	public Map<String, Object> getUserRegistrationStatistics() throws ParseException {
		Map<String, Object> responseMap = new HashMap<>();
         try {
		List<UserRegistration> userRegistrationList = getAllUserRegistration();

		
		responseMap.put("totalStrength", userRegistrationList.size());
		responseMap.put(Gender.MALE, VaccinationUtils.getGenderListCount(Gender.MALE, userRegistrationList));
		responseMap.put(Gender.FEMALE, VaccinationUtils.getGenderListCount(Gender.FEMALE, userRegistrationList));

		// state
		responseMap.put("state", VaccinationUtils.getRegistrationListBasedOnState(userRegistrationList));

		// Last 24 hours
		Date yesterdayDate = DateUtils.minusDays(DateUtils.getTodayDate(), 1);

		responseMap.put("24-hours",
				VaccinationUtils.getRegistrationListCountBasedOnDate(yesterdayDate, userRegistrationList));

		// Last 2 Days
		Date twoDaysBackDate = DateUtils.minusDays(DateUtils.getTodayDate(), 2);

		responseMap.put("2-days",
				VaccinationUtils.getRegistrationListCountBasedOnDate(twoDaysBackDate, userRegistrationList));

		Date monthBackDate = DateUtils.minusDays(DateUtils.getTodayDate(), 30);
		responseMap.put("1-month",
				VaccinationUtils.getRegistrationListCountBasedOnDate(monthBackDate, userRegistrationList));
         }catch(Exception e) {
        	 responseMap.put("status",e.getMessage());
        	 System.out.println(e.getMessage());
         }
		return responseMap;
	}

	

	

	public UserRegistration getUserRegistrationId(Integer userRegistrationId) {
		return  (UserRegistration)userRegistrationRepository.findByUserRegistrationId(userRegistrationId);
	}

}
