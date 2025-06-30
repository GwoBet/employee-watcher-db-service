package com.watcher.repository.base;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagingAndSortingCrudRepository<T, ID>
        extends CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>{
}
