package com.bridgelabz.employeepayrollusingdto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails, Long> {

    @Query(value = "SELECT username, email FROM employees WHERE username = ?1%", nativeQuery = true)
    List<EmployeeDetails> findByUsername(String name);

    //JPQL query@Repository
    //public interface UserRepository extends JpaRepository<User, Long> {
    //    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.email = ?2")
    //    List<User> findByUsernameAndEmail(String username, String email);
    //}
}
