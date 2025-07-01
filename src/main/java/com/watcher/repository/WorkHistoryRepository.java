package com.watcher.repository;

import com.watcher.entity.WorkHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHistoryRepository extends CrudRepository<WorkHistory, String> {


}
