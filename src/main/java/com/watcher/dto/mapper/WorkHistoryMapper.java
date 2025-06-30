package com.watcher.dto.mapper;

import com.watcher.dto.WorkHistoryDTO;
import com.watcher.dto.mapper.base.DefaultMapper;
import com.watcher.entity.WorkHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkHistoryMapper extends DefaultMapper<WorkHistoryDTO, WorkHistory> {
}
