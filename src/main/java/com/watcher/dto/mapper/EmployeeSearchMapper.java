package com.watcher.dto.mapper;

import com.watcher.dto.EmployeeDTO;
import com.watcher.dto.mapper.base.DefaultMapper;
import com.watcher.entity.EmployeeBase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeSearchMapper extends DefaultMapper<EmployeeDTO, EmployeeBase> {
}
