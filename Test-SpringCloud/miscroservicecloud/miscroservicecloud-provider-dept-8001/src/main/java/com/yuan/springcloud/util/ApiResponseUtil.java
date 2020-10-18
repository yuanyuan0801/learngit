package com.yuan.springcloud.util;

import com.yuan.springcloud.Common.Response;
import com.yuan.springcloud.entities.ApiResponseEntity;

public class ApiResponseUtil {

    public static <T> ApiResponseEntity<T> success(){
        return new ApiResponseEntity(Response.SUCCESS.getCode(),Response.SUCCESS.getMessage());
    }

    public static <T> ApiResponseEntity<T> successWithData(T data){
        ApiResponseEntity apiResponseEntity = success();
        apiResponseEntity.setData(data);
        return apiResponseEntity;
    }

}
