package com.swaggy7.licenseweb.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Swaggy7
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LicenseRegist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer licenseId;

    private String licenseName;

    private String licenseIp;

    private String licensePort;

    private Integer licenseAccNum;

    private Integer licenseAm;

    private Integer licenseEp;
    
    private String licenseTransPort;

}
