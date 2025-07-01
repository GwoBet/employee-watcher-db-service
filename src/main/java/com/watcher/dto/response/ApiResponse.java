package com.watcher.dto.response;

import lombok.Data;

@Data
public class ApiResponse<D> {

    private D response;
    private ExceptionDTO exception;

}
