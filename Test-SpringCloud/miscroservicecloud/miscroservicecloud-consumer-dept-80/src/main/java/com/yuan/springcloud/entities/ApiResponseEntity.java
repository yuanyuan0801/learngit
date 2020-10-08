package com.yuan.springcloud.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponseEntity implements Serializable {

    private String code;
    private String message;
    private Dept dept;

}
