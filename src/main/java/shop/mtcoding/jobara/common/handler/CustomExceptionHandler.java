package shop.mtcoding.jobara.common.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.jobara.common.dto.RespDto;
import shop.mtcoding.jobara.common.ex.CustomException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) {
        return new ResponseEntity<>(new RespDto<>(-1, 0, e.getMessage(), null), e.getStatus());
    }
}
