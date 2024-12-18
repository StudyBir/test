package com.swaggy7.licenseweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Swaggy7
 * @since 2024-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_web_id", type = IdType.AUTO)
    private Integer userWebId;

    private String userName;

    private String userDept;

    private String userAccount;

    private Integer webId;

    private String userWebStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime webTime;

}
