package govt.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import govt.vaccination.model.UserRegistration;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Integer> {

	String findByUserRegistrationId = null;

	UserRegistration findByUserRegistrationId(Integer userRegistrationId);

	UserRegistration findByuserRegistrationId(int i);



}
