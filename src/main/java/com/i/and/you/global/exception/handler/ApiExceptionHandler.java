package com.i.and.you.global.exception.handler;

import com.i.and.you.global.api.ApiResult;
import com.i.and.you.global.exception.CustomException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * 잘못된 파라미터
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiResult<?>> handlerIllegalArgumentException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException", ex);
        return ApiResult.createError(ex.getMessage());
    }

    /**
     * 메소드의 파라미터 타입이 잘못된 경우
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ApiResult<?>> handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ApiResult.createError("잘못된 요청입니다. 데이터 타입을 확인해주세요.");
    }

    /**
     * validation 실패
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResult<?>> handlerBindException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        return ApiResult.createFail(bindingResult);
    }

    /**
     * 데이터가 존재하지 않는 경우
     */
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiResult<?>> handlerEntityNotFoundException(EntityNotFoundException ex) {
        return ApiResult.createError(ex.getMessage());
    }

    /**
     * 데이터가 존재하지 않는 경우
     */
    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ApiResult<?>> handlerEntityNotFoundException(CustomException ex) {
        return ApiResult.createError(ex.getApiErrorCode().getMessage());
    }

    /**
     * 위에서 처리하지 못한 모든 예외 처리
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResult<?>> handlerException(Exception ex) {
        log.error("Exception", ex);
        return ApiResult.createError("예상치 못한 에러가 발생했습니다. 관리자에게 문의해주세요.");
    }
}
