package com.watcher.controller;

import com.watcher.dto.EmployeeDTO;
import com.watcher.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

//TODO сделать поиск по employeeId и department
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/create", consumes = "application/json")
    public ResponseEntity<String> create(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.create(employeeDTO);
        return ResponseEntity.ok().build();
    }

    // TODO: сделать update с проверкой на изменения с предыдущей сущностью
    @PostMapping(path = "/update", consumes = "application/json")
    public ResponseEntity<String> update(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.create(employeeDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public Set<EmployeeDTO> findAll(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeService.findAll(pageable);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
