package com.vang.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * CreatedDate: 01/01/2025
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: BaseRest
 */
@Setter
@Getter
public class BaseRest<T> {

    private T data;
    private Pageable pageable;
    private HttpServletRequest httpServletRequest;

    public void buildRequest(T data, Pageable pageable, HttpServletRequest httpServletRequest, MultiValueMap<String, Object>... parameters) {

        Map<String, String[]> request = httpServletRequest.getParameterMap();
        pageable = PageRequest.of(Integer.parseInt(request.get("page")[0]), Integer.parseInt(request.get("size")[0]));
        //map class
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {

            System.out.println(field.getName());
        }
    }
}