package com.watcher.repository;

import com.watcher.entity.WorkHistory;
import com.watcher.repository.base.PagingAndSortingCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHistoryRepository extends PagingAndSortingCrudRepository<WorkHistory, String> {


}
