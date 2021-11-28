package com.example.demo;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findByFnameAndLname(String fname, String lname);
    Employee findById(Long id);
}