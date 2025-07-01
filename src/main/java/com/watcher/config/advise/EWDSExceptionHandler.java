package com.watcher.config.advise;

import com.watcher.dto.response.ApiResponse;
import com.watcher.exceptions.BaseDBSException;
import com.watcher.utils.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class EWDSExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseDBSException.class)
    ResponseEntity<ApiResponse<Void>> handleConflict(RuntimeException ex, WebRequest request) {
        ApiResponse<Void> errorResponse = ResponseUtil.createException(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
