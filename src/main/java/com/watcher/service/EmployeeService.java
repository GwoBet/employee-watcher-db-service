package com.watcher.service;

import com.watcher.dto.EmployeeDTO;

import java.util.Set;

public interface EmployeeService {

    void create(EmployeeDTO employeeDTO);

    void update(EmployeeDTO employeeDTO);

    Set<EmployeeDTO> findAll();

    EmployeeDTO findById(String id);

    EmployeeDTO findByEmployeeId(String employeeId);

    void delete(String id);

}
