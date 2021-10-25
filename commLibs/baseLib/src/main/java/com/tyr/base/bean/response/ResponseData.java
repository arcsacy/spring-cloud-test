package com.tyr.base.bean.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tyrance on 2018/5/8.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回响应数据")
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = -2925753341673455128L;

    @ApiModelProperty(value = "返回数据")
    private T data; // 返回结果
    @ApiModelProperty(value = "状态码")
    private int code; // 错误码
    @ApiModelProperty(value = "描述信息")
    private String msg; // 错误描述
    @ApiModelProperty(value = "返回时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time = new Date();

    public ResponseData(ResponseCode code) {
        this.setCode(code.code());
        this.setMsg(code.msg());
    }

    public ResponseData(T data, ResponseCode code) {
        this.setData(data);
        this.setCode(code.code());
        this.setMsg(code.msg());
    }

    public ResponseData(T data, ResponseCode code, String msg) {
        this.setData(data);
        this.setCode(code.code());
        this.setMsg(msg);
    }

}
