package com.swaggy7.licenseweb.utils;/**
 * @Author Swaggy7
 * @PackageName com.swaggy7.licenseweb.utils
 * @Date 2023/2/2 16:25
 * @describe TODO
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: Swaggy7
 * @time: 2023/2/2 16:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {

    private static final long serialVersionUID = 42L;
    private static final String CODE_SUCCESS = "200";
    private static final String CODE_SYS_ERROR = "500";

    //相应码
    private String code;
    //信息
    private String msg;
    //返回数据
    private Object data;

    public static Result success() {
        return new Result(CODE_SUCCESS, "操作成功", null);
    }

    public static Result success(Object data) {
        return new Result(CODE_SUCCESS, "操作成功", data);
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result error(String msg) {
        return new Result(CODE_SYS_ERROR, msg, null);
    }

    public static Result error() {
        return new Result(CODE_SYS_ERROR, "系统错误", null);
    }

}