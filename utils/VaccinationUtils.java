package govt.vaccination.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import govt.vaccination.model.UserDosage;
import govt.vaccination.model.UserRegistration;
import govt.vaccination.service.UserRegistrationService;

@Service
public class VaccinationUtils {
	
	@Autowired
	UserRegistrationService userRegistrationService;

	public static int getGenderListCount(String genderType, List<UserRegistration>  userRegistrationList) {
		
	return (int) userRegistrationList.stream().filter(o-> o.getUser_gender().equals(genderType)).count();
	}
	
	
	
		
	
	
	public static int getRegistrationListCountBasedOnDate(Date date,List<UserRegistration> userRegistrationList ) {
		
		return (int) userRegistrationList.stream().filter(o->date.before(o.getUser_userregistrationdate())).count();
		}
	
	
	
	
	public static Map<String , Integer>  getRegistrationListBasedOnState(List<UserRegistration> userRegistrationList){
		
		Map<String,Integer> stateMap = new HashMap<>();
		Set<String> stateList = userRegistrationList.stream().map(l ->l.getUser_state()).collect(Collectors.toSet());
		
		
		for(String state : stateList) {
			stateMap.put(state, (int) userRegistrationList.stream().filter(l ->l.getUser_state().equals(state)).count());
		}
		
		return stateMap;
	}
	
	

	
	
	
	
	
	public static int getUserDosageListCountBasedOnDateCount(Date date , List<UserDosage> userDosageList) {
		return (int) userDosageList.stream().filter(o -> date.before(o.getUserFirstDoseDate())).count();
	}
	

	public static Map<String,Integer>  getUserDosageListCountBasedOnDoseCount(List<UserDosage> userDosageList){
		Map<String , Integer> vaccinMap = new HashMap<>();
		
		Set<String> vaccinList = userDosageList.stream().map(l->l.getUservaccinationType()).collect(Collectors.toSet());
		
		for(String user_vaccinatedType : vaccinList) {
			
			vaccinMap.put(user_vaccinatedType, (int)userDosageList.stream().filter(l->l.getUservaccinationType().equals(user_vaccinatedType)).count());
		}
		return vaccinMap;
	}
	

	
	public   int getGenderListCountForDosage(String genderType, List<UserDosage>  userDosageList) {
		
		Set<Integer> userDosageRegistrationIdList = userDosageList.stream().filter(Objects :: nonNull).map(l ->l.getUserRegistrationId()).collect(Collectors.toSet());
		
		
	return  (int) userDosageRegistrationIdList.stream().filter(Objects::nonNull).filter(id -> userRegistrationService.getUserRegistrationId(id).getUser_gender().equalsIgnoreCase(genderType)).count();
	}
		
		

	public static int getAEFIPostFirstDosage(List<UserDosage>  userDosageList) {
		
		return (int) userDosageList.stream().filter(l-> l.isUserFirstDose() == true &&  l.isUserAEFIpostFirstdose() == true).count();
		}
	
//   public static int getAEFIPostSecondDosage(List<UserDosage>  userDosageList) {
//		
//		return (int) userDosageList.stream().filter(l-> l.isUserSecondDose()== true &&  l.isUserAEFIpostFirstdose() == true).count();
//		}
	
		
	

		
	
	
}
