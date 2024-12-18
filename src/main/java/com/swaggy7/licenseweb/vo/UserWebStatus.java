package com.swaggy7.licenseweb.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserWebStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer webId;
    private String webName;
    private String webAddr;
    private String userWebStatus;
}
