package com.english.english_vision.Base;

import com.english.english_vision.enums.ResponseEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.validation.BindingResult;

import java.util.Objects;

/**
 * @Author he
 * @Description 统一响应
 * @Date 8.30
 **/
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseResult <T>{

    private Integer status;

    private String msg;

    private T data;
    private ResponseResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ResponseResult(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> ResponseResult<T> successByMsg(String msg) {
        return new ResponseResult<>(ResponseEnum.SUCCESS.getCode(), msg);
    }

    public static <T>ResponseResult<T> success(T data) {
        return new ResponseResult<>(ResponseEnum.SUCCESS.getCode(), data);
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getDesc());
    }

    public static <T> ResponseResult<T> error(ResponseEnum responseEnum) {
        return new ResponseResult<>(responseEnum.getCode(), responseEnum.getDesc());
    }
    public static <T> ResponseResult<T> error(String msg)
    {
        return new ResponseResult<>(ResponseEnum.ERROR.getCode(),msg);
    }
    public static <T> ResponseResult<T> error(ResponseEnum responseEnum, String msg) {
        return new ResponseResult<>(responseEnum.getCode(), msg);
    }

    public static <T> ResponseResult<T> error(ResponseEnum responseEnum, BindingResult bindingResult) {
        return new ResponseResult<>(responseEnum.getCode(),
                Objects.requireNonNull(bindingResult.getFieldError()).getField() + " " + bindingResult.getFieldError().getDefaultMessage());
    }
}
