package com.watcher.repository;

import com.watcher.entity.Employee;
import com.watcher.repository.base.PagingAndSortingCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingCrudRepository<Employee, String> {

    Employee findFirstByEmployeeId(String employeeId);

    Employee findFirstByDepartment(String department);

    void deleteByEmployeeId(String employeeId);
}
