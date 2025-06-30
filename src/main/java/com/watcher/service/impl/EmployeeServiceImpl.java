package com.watcher.service.impl;

import com.watcher.dto.EmployeeDTO;
import com.watcher.dto.mapper.EmployeeMapper;
import com.watcher.entity.Employee;
import com.watcher.repository.EmployeeRepository;
import com.watcher.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    @Autowired
    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            EmployeeMapper mapper
    ) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(EmployeeDTO employeeDTO) {
        employeeRepository.save(mapper.from(employeeDTO));
    }

    @Override
    public Set<EmployeeDTO> findAll(Pageable pageable) {
        Page<Employee> pagedResult = employeeRepository.findAll(pageable);
        Set<Employee> entities = pagedResult.hasContent() ? new HashSet<>(pagedResult.getContent()) : Collections.emptySet();
        return mapper.map(entities);
    }

    @Override
    public EmployeeDTO findById(String id) {
        Employee employee = employeeRepository.findFirstByEmployeeId(id);
        return null != employee ? mapper.map(employee) : null;
    }

    @Override
    public EmployeeDTO findByDepartment(String department) {
        Employee employee = employeeRepository.findFirstByDepartment(department);
        return null != employee ? mapper.map(employee) : null;
    }

    @Override
    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteByEmployeeId(String employeeId) {
        employeeRepository.deleteByEmployeeId(employeeId);
    }
}
