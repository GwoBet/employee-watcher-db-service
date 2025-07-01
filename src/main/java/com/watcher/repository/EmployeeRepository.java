package com.watcher.repository;

import com.watcher.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

    Employee findFirstById(String id);

    Employee findFirstByEmployeeId(String employeeId);

    Employee findFirstByDepartment(String department);

    void deleteByEmployeeId(String employeeId);
}
