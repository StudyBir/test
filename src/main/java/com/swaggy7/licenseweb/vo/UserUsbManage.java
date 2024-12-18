package com.swaggy7.licenseweb.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserUsbManage implements Serializable {
    private static final long serialVersionUID = 1L;

}
