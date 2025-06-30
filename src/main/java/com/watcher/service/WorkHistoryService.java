package com.watcher.service;

import com.watcher.dto.WorkHistoryDTO;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface WorkHistoryService {

    void addWork(WorkHistoryDTO workHistoryDTO);

    Set<WorkHistoryDTO> findAll(Pageable pageable);

    void deleteWork(String id);

}
