package com.watcher.repository;

import com.watcher.entity.EmployeeBase;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSearchRepository extends PagingAndSortingRepository<EmployeeBase, String> {

}
