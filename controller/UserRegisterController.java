package govt.vaccination.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import govt.vaccination.model.UserRegistration;
import govt.vaccination.service.UserRegistrationService;
import govt.vaccination.utils.DateUtils;

@RestController
@RequestMapping("/userregistrations")
public class UserRegisterController {

     @Autowired
	UserRegistrationService userRegistrationService;

	@GetMapping("/get-all-registered-users")
	public List<UserRegistration> getAllUserRegistration(){
		
			return  userRegistrationService.getAllUserRegistration();
			
		}
	
	
	@GetMapping("/get-all-user-registration-statistics")
	public Map getUserRegistrationStatistics() throws ParseException {
		return userRegistrationService.getUserRegistrationStatistics();
		
	}
	
	
	

	
	@PostMapping("/add")
	public UserRegistration insertUserRegistration(@RequestBody UserRegistration userRegistration ) throws ParseException {
		
	userRegistration.setUser_userregistrationdate(DateUtils.getTodayDate());
     return userRegistrationService.save(userRegistration); 
}
}
	
	
	
	
	
	

