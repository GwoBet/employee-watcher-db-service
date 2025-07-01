package com.watcher.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@RedisHash
public class Employee extends EmployeeBase {

    /**
     * Дата трудоустройства в компанию
     */
    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime employmentDate;

    /**
     * Дата увольнения. По умолчанию пустое поле.
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dismissalDate;

    /**
     * История работы в разных департаментах/должностях компании
     */
    @Reference
    @JsonIdentityReference(alwaysAsId = true)
    private Set<WorkHistory> workHistories = new HashSet<>();

    public void addWorkHistory(WorkHistory workHistory) {
        workHistories.add(workHistory);
    }

}
