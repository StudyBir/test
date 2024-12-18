package com.swaggy7.licenseweb.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

//前端web监控下的User
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserWebMonitor implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)

    private String userWebId;

    private String userName;

    private String userDept;

    private String userAccount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime webTime;

}
