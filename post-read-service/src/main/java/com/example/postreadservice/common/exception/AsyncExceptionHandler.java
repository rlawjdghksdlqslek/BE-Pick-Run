package com.example.postreadservice.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        if (ex instanceof BaseException baseException) {
            log.error("BaseException: [{}] {}", baseException.getStatus(), baseException.getMessage());
        } else {
            log.error("Async method [{}] threw an exception", method.getName(), ex);
        }
    }
}