package com.watcher.dto.mapper.base;

import java.util.Set;

public interface DefaultMapper<D, E> {

    E from(D dto);

    D map(E entity);

    Set<D> map(Iterable<E> set);

    Set<E> from(Iterable<D> set);
}
