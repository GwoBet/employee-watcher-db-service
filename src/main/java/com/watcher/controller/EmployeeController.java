package com.watcher.controller;

import com.watcher.dto.EmployeeDTO;
import com.watcher.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @PostMapping(path = "/update", consumes = "application/json")
    public ResponseEntity<String> update(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.update(employeeDTO);
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

    @GetMapping("/findById")
    public ResponseEntity<EmployeeDTO> findById(@RequestParam String id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("/findByEmployeeId")
    public ResponseEntity<EmployeeDTO> findByEmployeeId(@RequestParam String employeeId) {
        return ResponseEntity.ok(employeeService.findByEmployeeId(employeeId));
    }

    @GetMapping("/findByDepartment")
    public ResponseEntity<EmployeeDTO> findByDepartment(@RequestParam String department) {
        return ResponseEntity.ok(employeeService.findByDepartment(department));
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteByEmployeeId")
    public ResponseEntity<String> deleteByEmployeeId(@RequestParam String employeeId) {
        employeeService.delete(employeeId);
        return ResponseEntity.ok().build();
    }
}
