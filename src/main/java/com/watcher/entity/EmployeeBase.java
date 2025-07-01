package com.watcher.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RedisHash
public class EmployeeBase {

    /**
     * Идентификатор создаваемый Redis
     */
    @Id
    private String id;

    /**
     * Табельный номер сотрудника
     */
    @NotNull
    @EqualsAndHashCode.Include
    @Indexed
    private String employeeId;

    /**
     * ФИО сотрудника
     */
    @NotNull
    private String name;

    /**
     * Должность сотрудника
     */
    @NotNull
    private String title;

    /**
     * Департамент в котором работает сотрудник
     */
    @NotNull
    @Indexed
    private String department;
}
