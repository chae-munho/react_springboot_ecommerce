package com.phegondev.Phegon.Eccormerce.exception;

import com.phegondev.Phegon.Eccormerce.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

//스프링부트 애플리케이션에서 전역 예외 처리를 담당하는 클래스
@ControllerAdvice // 전역 예외 처리기 역할 모든 컨트롤러에서 발생한 예외를 여기서 잡아 처리할 수 있도록 함
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // Exception.class 타입의 예외가 발생했을 때 이 메서드가 실행됨 즉, 모든 종류의 예외에 대해 공통된 처리 방식을 제공하는 메서드
    public ResponseEntity<Response> handleAllException(Exception ex, WebRequest request) { // Exception ex : 실제로 발생한 예외 객체, WebRequest request : 예외가 발생했을 때의 웹 요청 정보
        Response errorResponse = Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NotFoundException ex, WebRequest request) {
        Response errorResponse = Response.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Response> handleInvaildCredentialsException(InvalidCredentialsException ex, WebRequest request) {
        Response errorResponse = Response.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }



}
