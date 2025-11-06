package com.lanxige.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class RespResult<T> implements Serializable {
    private T data;
    private Integer code;
    private String msg;

    public RespResult() {

    }

    public RespResult(RespCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public RespResult(T data, RespCode resultCode) {
        this.data = data;
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public static RespResult ok() {
        return new RespResult(RespCode.SUCCESS);
    }

    public static RespResult ok(Object data) {
        return new RespResult(data, RespCode.SUCCESS);
    }

    public static RespResult error() {
        return new RespResult(null,RespCode.ERROR);
    }

    public static RespResult error(String msg) {
        return secByError(RespCode.ERROR.getCode(), msg);
    }

    //自定义异常
    public static RespResult secByError(Integer code, String msg) {
        RespResult respResult = new RespResult();
        respResult.setCode(code);
        respResult.setMsg(msg);
        return respResult;
    }
}
