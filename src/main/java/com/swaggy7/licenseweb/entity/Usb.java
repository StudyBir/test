package com.swaggy7.licenseweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Usb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "usb_id", type = IdType.AUTO)
    private Integer usbId;

    private String usbName;


}
