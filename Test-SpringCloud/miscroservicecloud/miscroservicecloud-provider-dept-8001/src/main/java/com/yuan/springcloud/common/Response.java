package com.yuan.springcloud.common;

import lombok.Getter;

@Getter
public enum Response {

    SUCCESS("0","请求执行成功"),
    FAIL("1","请求执行失败");

    private String code;
    private String message;
    Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
