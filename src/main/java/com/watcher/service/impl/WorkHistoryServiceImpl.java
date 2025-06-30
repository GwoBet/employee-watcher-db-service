package com.watcher.service.impl;

import com.watcher.dto.WorkHistoryDTO;
import com.watcher.dto.mapper.WorkHistoryMapper;
import com.watcher.entity.WorkHistory;
import com.watcher.repository.WorkHistoryRepository;
import com.watcher.service.WorkHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class WorkHistoryServiceImpl implements WorkHistoryService {

    private final WorkHistoryRepository workHistoryRepository;
    private final WorkHistoryMapper mapper;

    @Autowired
    public WorkHistoryServiceImpl(
            WorkHistoryRepository workHistoryRepository,
            WorkHistoryMapper mapper
    ) {
        this.workHistoryRepository = workHistoryRepository;
        this.mapper = mapper;
    }


    @Override
    public void addWork(WorkHistoryDTO workHistoryDTO) {
        workHistoryRepository.save(mapper.from(workHistoryDTO));
    }

    @Override
    public Set<WorkHistoryDTO> findAll(Pageable pageable) {
        Page<WorkHistory> pagedResult = workHistoryRepository.findAll(pageable);
        Set<WorkHistory> entities = pagedResult.hasContent() ? new HashSet<>(pagedResult.getContent()) : Collections.emptySet();
        return mapper.map(entities);
    }

    @Override
    public void deleteWork(String id) {
        workHistoryRepository.deleteById(id);
    }
}
