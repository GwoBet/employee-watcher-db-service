package com.watcher.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@RedisHash
public class WorkHistory {

    /**
     * Идентификатор создаваемый Redis
     */
    @Id
    private String id;

    /**
     * Должность
     */
    @EqualsAndHashCode.Exclude
    private String title;

    /**
     * Департамент в котором работает сотрудник
     */
    @NotNull
    private String department;

    /**
     * Дата трудоустройства на должность
     */
    @EqualsAndHashCode.Exclude
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime employmentDate;

    /**
     * Дата увольнения с должности
     */
    @EqualsAndHashCode.Exclude
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dismissalDate;
}
