package com.watcher.service.impl;

import com.watcher.dto.EmployeeDTO;
import com.watcher.dto.mapper.EmployeeMapper;
import com.watcher.dto.mapper.EmployeeSearchMapper;
import com.watcher.entity.Employee;
import com.watcher.entity.EmployeeBase;
import com.watcher.entity.WorkHistory;
import com.watcher.exceptions.BaseDBSException;
import com.watcher.repository.EmployeeRepository;
import com.watcher.repository.EmployeeSearchRepository;
import com.watcher.repository.WorkHistoryRepository;
import com.watcher.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

// TODO разобраться с transaction и pipeline в redis и переписать создание и обновление объектов
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WorkHistoryRepository workHistoryRepository;
    private final EmployeeSearchRepository employeeSearchRepository;
    private final EmployeeMapper mapper;
    private final EmployeeSearchMapper searchMapper;

    @Autowired
    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            WorkHistoryRepository workHistoryRepository,
            EmployeeSearchRepository employeeSearchRepository,
            EmployeeSearchMapper searchMapper,
            EmployeeMapper mapper
    ) {
        this.employeeRepository = employeeRepository;
        this.workHistoryRepository = workHistoryRepository;
        this.employeeSearchRepository = employeeSearchRepository;
        this.mapper = mapper;
        this.searchMapper = searchMapper;
    }

    @Override
    public void create(EmployeeDTO employeeDTO) {
        try {
            Employee newEmployee = mapper.from(employeeDTO);
            if (!newEmployee.getWorkHistories().isEmpty()) {
                workHistoryRepository.saveAll(newEmployee.getWorkHistories());
            }
            employeeRepository.save(newEmployee);
        } catch (Exception e) {
            log.error("Employee creation failed. Reason: {}", e.getMessage(), e);
            throw new BaseDBSException("Employee creation failed. Reason: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeDTO.getId());
            Employee updatedEmployee = mapper.from(employeeDTO);
            if (employeeOptional.isPresent()) {
                Employee employee = employeeOptional.get();
                int existingSize = employee.getWorkHistories().size();
                int newSize = updatedEmployee.getWorkHistories().size();
                if (existingSize < newSize) {
                    Set<WorkHistory> newWorkHistory =
                            getWorkHistoryDiff(updatedEmployee.getWorkHistories(), employee.getWorkHistories());
                    workHistoryRepository.saveAll(newWorkHistory);
                } else if (existingSize > newSize) {
                    Set<WorkHistory> oldWorkHistory =
                            getWorkHistoryDiff(employee.getWorkHistories(), updatedEmployee.getWorkHistories());
                    workHistoryRepository.deleteAll(oldWorkHistory);
                }
            }
            employeeRepository.save(updatedEmployee);
        } catch (Exception e) {
            log.error("Employee update failed. Reason: {}", e.getMessage(), e);
            throw new BaseDBSException("Employee update failed. Reason: " + e.getMessage(), e);
        }
    }

    @Override
    public Set<EmployeeDTO> findAll(Pageable pageable) {
        try {
            Page<EmployeeBase> pagedResult = employeeSearchRepository.findAll(pageable);
            Set<EmployeeBase> entities = pagedResult.hasContent() ? new HashSet<>(pagedResult.getContent()) : Collections.emptySet();
            return searchMapper.map(entities);
        } catch (Exception e) {
            log.error("Error finding all employees. Reason: {}", e.getMessage(), e);
            throw new BaseDBSException("Error finding all employees. Reason: " + e.getMessage(), e);
        }
    }

    @Override
    public EmployeeDTO findById(String id) {
        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            return employee.map(searchMapper::map).orElse(null);
        } catch (Exception e) {
            log.error("Error finding employee by id. Reason: {}", e.getMessage(), e);
            throw new BaseDBSException("Error finding employee by id. Reason: " + e.getMessage(), e);
        }
    }

    @Override
    public EmployeeDTO findByEmployeeId(String employeeId) {
        try {
            Employee employee = employeeRepository.findFirstByEmployeeId(employeeId);
            return null != employee ? searchMapper.map(employee) : null;
        } catch (Exception e) {
            log.error("Error finding employee by employeeId. Reason: {}", e.getMessage(), e);
            throw new BaseDBSException("Error finding employee by employeeId. Reason: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting employee by id. Reason: {}", e.getMessage(), e);
            throw new BaseDBSException("Error deleting employee by id. Reason: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByEmployeeId(String employeeId) {
        try {
            employeeRepository.deleteByEmployeeId(employeeId);
        } catch (Exception e) {
            log.error("Error deleting employee by employeeId. Reason: {}", e.getMessage(), e);
            throw new BaseDBSException("Error deleting employee by employeeId. Reason: " + e.getMessage(), e);
        }
    }

    private Set<WorkHistory> getWorkHistoryDiff(Collection<WorkHistory> large, Collection<WorkHistory> small) {
        HashSet<WorkHistory> workHistories = new HashSet<>(large);
        workHistories.removeAll(small);
        return workHistories;
    }
}
