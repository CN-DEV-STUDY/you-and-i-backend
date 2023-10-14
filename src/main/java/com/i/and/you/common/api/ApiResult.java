package com.i.and.you.common.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @See https://velog.io/@qotndus43/%EC%8A%A4%ED%94%84%EB%A7%81-API-%EA%B3%B5%ED%86%B5-%EC%9D%91%EB%8B%B5-%ED%8F%AC%EB%A7%B7-%EA%B0%9C%EB%B0%9C%ED%95%98%EA%B8%B0
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResult<T> {

    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";

    private T data;
    private String status;
    private String message;

    public static <T> ResponseEntity<ApiResult<T>> createSuccess(T data) {
        return ResponseEntity
                .ok(new ApiResult<>(SUCCESS_STATUS, data, "정상적으로 처리되었습니다."));
    }


    public static ResponseEntity<ApiResult<Void>> createSuccessWithNoContent() {
        return ResponseEntity
                .ok(new ApiResult<>(SUCCESS_STATUS, null, "정상적으로 처리되었습니다."));
    }

    public static ResponseEntity<ApiResult<?>> createFail(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError error : allErrors) {
            if (error instanceof FieldError) {
                errors.put(((FieldError) error).getField(), error.getDefaultMessage());
            } else {
                errors.put( error.getObjectName(), error.getDefaultMessage());
            }
        }
        return ResponseEntity.badRequest()
                .body(new ApiResult<>(FAIL_STATUS, errors, null));
    }

    // 예외 발생으로 API 호출 실패시 반환
    public static ResponseEntity<ApiResult<?>> createError(String message) {
        return ResponseEntity.badRequest()
                .body(new ApiResult<>(ERROR_STATUS, null, message));
    }

    private ApiResult(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
