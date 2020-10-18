package com.yuan.springcloud.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class ApiResponseEntity<T>{

    private String code;
    private String message;

    private T data;

    public ApiResponseEntity(String code,String message){
        this.code=code;
        this.message=message;
    }




}
