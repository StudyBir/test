package com.swaggy7.licenseweb.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2024-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserUsb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_usb_id", type = IdType.AUTO)
    private Integer userUsbId;

    private String userName;

    private String userDept;

    private String userAccount;

    private Integer usbId;

    private String userUsbStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime usbTime;

    private String usbNumber;
}
