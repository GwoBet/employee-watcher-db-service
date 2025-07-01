package com.watcher.service.impl;

import com.watcher.dto.EmployeeDTO;
import com.watcher.dto.WorkHistoryDTO;
import com.watcher.dto.mapper.EmployeeMapper;
import com.watcher.dto.mapper.EmployeeMapperImpl;
import com.watcher.dto.mapper.EmployeeSearchMapper;
import com.watcher.dto.mapper.EmployeeSearchMapperImpl;
import com.watcher.entity.EmployeeBase;
import com.watcher.repository.EmployeeRepository;
import com.watcher.repository.EmployeeSearchRepository;
import com.watcher.repository.WorkHistoryRepository;
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
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeSearchRepository employeeSearchRepository;
    @Mock
    private WorkHistoryRepository workHistoryRepository;
    private EmployeeMapper mapper = new EmployeeMapperImpl();
    private EmployeeSearchMapper searchMapper = new EmployeeSearchMapperImpl();

    private EmployeeService employeeService;

    @BeforeEach
    void init() {
        employeeService =
                new EmployeeServiceImpl(
                        employeeRepository,
                        workHistoryRepository,
                        employeeSearchRepository,
                        searchMapper, mapper
                );

        when(employeeSearchRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(makeEmployeesBase()));

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


    private List<EmployeeBase> makeEmployeesBase() {
        List<EmployeeBase> result = new ArrayList<>();
        EmployeeBase employee = new EmployeeBase();
        employee.setId( "id" );
        employee.setEmployeeId( "employeeId" );
        employee.setName( "Solid Snake" );
        employee.setTitle( "Programmer" );
        employee.setDepartment( "Security" );
        result.add(employee);
        return result;
    }

    private Set<EmployeeDTO> makeEmployeesSearchDTO() {
        Set<EmployeeDTO> result = new HashSet<>();
        EmployeeDTO employee = new EmployeeDTO();
        employee.setId( "id" );
        employee.setEmployeeId( "employeeId" );
        employee.setName( "Solid Snake" );
        employee.setTitle( "Programmer" );
        employee.setDepartment( "Security" );
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
        Set<WorkHistoryDTO> set = new HashSet<>();
        WorkHistoryDTO history = new WorkHistoryDTO();
        history.setId("histId");
        history.setTitle("Programmer");
        history.setEmploymentDate(LocalDateTime.now().minusDays(2));
        set.add(history);
        employee.setWorkHistories( new LinkedHashSet<>( set ) );
        result.add(employee);
        return result;
    }

}