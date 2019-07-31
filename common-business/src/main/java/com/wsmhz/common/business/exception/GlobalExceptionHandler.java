package com.wsmhz.common.business.exception;

import com.wsmhz.common.business.response.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.InsufficientResourcesException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ForbiddenException.class})
    @ResponseBody
    public ServerResponse handleForbiddenException(Exception ex) {
        // 禁止访问不用打印异常
        log.info("拒绝访问,原因: {}", ex.getMessage());
        return ServerResponse.createByErrorCodeMessage(HttpStatus.FORBIDDEN.value(),ex.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    public ServerResponse handleMultipartException(MultipartException ex) {
        log.error(ex.toString());
        return ServerResponse.createByErrorMessage("文件大小超出系统限制!");
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(
                ex,
                ServerResponse.createByErrorMessage("不支持的请求数据类型!"),
                headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(
                ex,
                ServerResponse.createByErrorMessage("无法读取请求数据 请检查入参数据是否正确!"),
                headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(
                ex,
                ServerResponse.createByErrorMessage(
                        String.format("参数类型不匹配 值 %s 无法转换为 %s",
                                      ex.getValue(),
                                      ex.getRequiredType().getSimpleName())),
                headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpServletRequest req = ((ServletWebRequest) request).getNativeRequest(HttpServletRequest.class);
        return handleExceptionInternal(
                ex,
                ServerResponse.createByErrorCodeMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), req.getRequestURI() + req.getMethod() + "请求METHOD不支持"),
                headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        HttpServletRequest req = ((ServletWebRequest) request).getNativeRequest(HttpServletRequest.class);
        return handleExceptionInternal(
                ex,
                ServerResponse.createByErrorCodeMessage(HttpStatus.NOT_FOUND.value(), req.getRequestURI() + "路径未找到!"),
                headers, status, request);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public  ServerResponse handleControllerException(Throwable ex) {
        log.error(ex.toString());
        return  ServerResponse.createByErrorCodeMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getClass().getName() + ": " + ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body instanceof ServerResponse) {((ServerResponse) body).setCache();}
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
