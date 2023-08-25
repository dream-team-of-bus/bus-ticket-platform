package com.bus.ticket.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.bus.ticket.util.HttpResponseBodyUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/7/22
 */
@Slf4j
@RestControllerAdvice
public class RestApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private String[] supportReturnTypeClassNamePrefixes = {"com.bus.ticket", "java.lang", "java.util", "void",
        "com.baomidou.mybatisplus.extension.plugins.pagination.Page"};

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Class<?> returnClass = returnType.getParameterType();
        String returnClassName = returnClass.getName();
        if (!StringUtils.startsWithAny(returnClassName, supportReturnTypeClassNamePrefixes)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {
        return HttpResponseBodyUtil.toJson(body);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        Map<String, Object> wrapper = new HashMap<>(4);
        log.error("unexpect exception: request={}", request, ex);
        wrapper.put("code", "10000");
        wrapper.put("message", "系统错误：" + ex.getMessage());
        return ResponseEntity.ok(wrapper);
    }
}
