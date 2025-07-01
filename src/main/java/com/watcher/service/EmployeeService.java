package com.watcher.service;

import com.watcher.dto.EmployeeDTO;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface EmployeeService {

    void create(EmployeeDTO employeeDTO);

    void update(EmployeeDTO employeeDTO);

    Set<EmployeeDTO> findAll(Pageable pageable);

    EmployeeDTO findById(String id);

    EmployeeDTO findByEmployeeId(String employeeId);

    void delete(String id);

    void deleteByEmployeeId(String employeeId);
}
