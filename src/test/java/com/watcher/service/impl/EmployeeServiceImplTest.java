package com.watcher.service.impl;

import com.watcher.dto.EmployeeDTO;
import com.watcher.dto.mapper.EmployeeMapper;
import com.watcher.dto.mapper.EmployeeMapperImpl;
import com.watcher.entity.Employee;
import com.watcher.entity.WorkHistory;
import com.watcher.repository.EmployeeRepository;
import com.watcher.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository ;
    private EmployeeMapper mapper = new EmployeeMapperImpl();

    private EmployeeService employeeService;

    @BeforeEach
    void init() {
        employeeService = new EmployeeServiceImpl(employeeRepository, mapper);

        when(employeeRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<Employee>(makeEmployees()));

    }

//    @Test
//    void create() {
//    }

    @Test
    void findAll() {
        Set<EmployeeDTO> expected = makeEmployeesDTO();

        Pageable pageable = PageRequest.of(0, 1);
        Set<EmployeeDTO> actual = employeeService.findAll(pageable);

        Assert.notEmpty(actual, "Ошибка при маппинге полученного списка работников");
        log.info("\nActual\t:{}\nExpected:\t{}", actual, expected);
        Assertions.assertIterableEquals(expected, actual);
    }

//    @Test
//    void findById() {
//    }
//
//    @Test
//    void findByDepartment() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void deleteByEmployeeId() {
//    }


    private List<Employee> makeEmployees() {
        List<Employee> result = new ArrayList<>();
        Employee employee = new Employee();
        employee.setId( "id" );
        employee.setEmployeeId( "employeeId" );
        employee.setName( "Solid Snake" );
        employee.setTitle( "Programmer" );
        employee.setDepartment( "Security" );
        employee.setEmploymentDate(LocalDateTime.now().minusDays(2));
        employee.setDismissalDate(null);
        Set<WorkHistory> set = new HashSet<>();
        WorkHistory history = new WorkHistory();
        history.setId("histId");
        history.setTitle("Programmer");
        history.setEmploymentDate(LocalDateTime.now().minusDays(2));
        set.add(history);
        employee.setWorkHistories( new LinkedHashSet<WorkHistory>( set ) );
        result.add(employee);
        return result;
    }

    private Set<EmployeeDTO> makeEmployeesDTO() {
        Set<EmployeeDTO> result = new HashSet<>();
        EmployeeDTO employee = new EmployeeDTO();
        employee.setId( "id" );
        employee.setEmployeeId( "employeeId" );
        employee.setName( "Solid Snake" );
        employee.setTitle( "Programmer" );
        employee.setDepartment( "Security" );
        employee.setEmploymentDate(LocalDateTime.now().minusDays(2));
        employee.setDismissalDate(null);
        Set<WorkHistory> set = new HashSet<>();
        WorkHistory history = new WorkHistory();
        history.setId("histId");
        history.setTitle("Programmer");
        history.setEmploymentDate(LocalDateTime.now().minusDays(2));
        set.add(history);
        employee.setWorkHistories( new LinkedHashSet<WorkHistory>( set ) );
        result.add(employee);
        return result;
    }

}