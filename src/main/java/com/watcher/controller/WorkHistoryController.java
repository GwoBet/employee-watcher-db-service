package com.watcher.controller;

import com.watcher.dto.WorkHistoryDTO;
import com.watcher.service.WorkHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class WorkHistoryController {

    private final WorkHistoryService workHistoryService;

    @Autowired
    public WorkHistoryController(WorkHistoryService workHistoryService) {
        this.workHistoryService = workHistoryService;
    }

    @PostMapping(path = "/update", consumes = "application/json")
    public ResponseEntity<String> update(@RequestBody WorkHistoryDTO workHistoryDTO) {
        workHistoryService.updateWork(workHistoryDTO);
        return ResponseEntity.ok().build();
    }

}
