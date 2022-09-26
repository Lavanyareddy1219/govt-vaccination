package govt.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import govt.vaccination.model.UserDosage;

@Repository
public interface UserDosageRepository extends JpaRepository<UserDosage, Integer> {

}
