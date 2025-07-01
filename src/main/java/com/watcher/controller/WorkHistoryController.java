package com.watcher.controller;

import com.watcher.dto.WorkHistoryDTO;
import com.watcher.dto.response.ApiResponse;
import com.watcher.service.WorkHistoryService;
import com.watcher.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work-history")
public class WorkHistoryController {

    private final WorkHistoryService workHistoryService;

    @Autowired
    public WorkHistoryController(WorkHistoryService workHistoryService) {
        this.workHistoryService = workHistoryService;
    }

    @PostMapping(path = "/update", consumes = "application/json")
    public ResponseEntity<ApiResponse<Void>> update(@RequestBody WorkHistoryDTO workHistoryDTO) {
        workHistoryService.updateWork(workHistoryDTO);
        return ResponseUtil.createVoidSuccess();
    }

}
