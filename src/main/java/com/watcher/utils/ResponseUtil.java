package com.watcher.utils;

import com.watcher.dto.response.ApiResponse;
import com.watcher.dto.response.ExceptionDTO;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

public class ResponseUtil {

    public static <D> ResponseEntity<ApiResponse<D>> createSuccess(D responseDTO) {
        ApiResponse<D> apiResponse = new ApiResponse<>();
        apiResponse.setResponse(responseDTO);
        return ResponseEntity.ok(apiResponse);
    }

    public static ResponseEntity<ApiResponse<Void>> createVoidSuccess() {
        return ResponseEntity.ok(new ApiResponse<>());
    }

    public static <D> ApiResponse<D> createException(Exception exception) {
        ApiResponse<D> apiResponse = new ApiResponse<>();
        ExceptionDTO exceptionDTO =
                new ExceptionDTO(
                        exception.getClass().getName(),
                        exception.getMessage(),
                        Arrays.toString(exception.getStackTrace())
                );
        apiResponse.setException(exceptionDTO);
        return apiResponse;
    }
}
