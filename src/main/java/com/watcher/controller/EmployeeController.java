package com.watcher.controller;

import com.watcher.dto.EmployeeDTO;
import com.watcher.dto.response.ApiResponse;
import com.watcher.service.EmployeeService;
import com.watcher.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.create(employeeDTO);
        return ResponseUtil.createVoidSuccess();
    }

    @PostMapping(path = "/update", consumes = "application/json")
    public ResponseEntity<ApiResponse<Void>> update(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.update(employeeDTO);
        return ResponseUtil.createVoidSuccess();
    }

    @GetMapping("/findAll")
    public ResponseEntity<ApiResponse<Set<EmployeeDTO>>> findAll() {
        return ResponseUtil.createSuccess(employeeService.findAll());
    }

    @GetMapping("/findById")
    public ResponseEntity<ApiResponse<EmployeeDTO>> findById(@RequestParam String id) {
        return ResponseUtil.createSuccess(employeeService.findById(id));
    }

    @GetMapping("/findByEmployeeId")
    public ResponseEntity<ApiResponse<EmployeeDTO>> findByEmployeeId(@RequestParam String employeeId) {
        return ResponseUtil.createSuccess(employeeService.findByEmployeeId(employeeId));
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> delete(@RequestParam String id) {
        employeeService.delete(id);
        return ResponseUtil.createVoidSuccess();
    }
}
