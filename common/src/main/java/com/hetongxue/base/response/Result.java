package com.hetongxue.base.response;

import com.hetongxue.base.enums.ResponseCode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 统一返回结果类
 *
 * @author 何同学
 */
@Data
@Accessors(chain = true)
public class Result implements Serializable {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    private Result() {
    }

    /**
     * 成功返回
     **/
    public static Result Success() {
        return new Result().setCode(ResponseCode.OK.getCode()).setMessage(ResponseCode.OK.getMessage());
    }

    public static Result Success(Object data) {
        return new Result().setCode(ResponseCode.OK.getCode()).setMessage(ResponseCode.OK.getMessage()).setData(data);
    }


    /**
     * 失败返回
     **/
    public static Result Error() {
        return new Result().setCode(ResponseCode.BAD_REQUEST.getCode()).setMessage(ResponseCode.BAD_REQUEST.getMessage());
    }

    public static Result Error(Object data) {
        return new Result().setCode(ResponseCode.BAD_REQUEST.getCode()).setMessage(ResponseCode.BAD_REQUEST.getMessage()).setData(data);
    }

}