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
public class LicenseApplyInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    //资源申请id信息
    private String toolId;
    //资源申请ip信息
    private String licenseIp;
    //资源申请开始时间
    private String applyBeginTime;
    //资源开始结束时间
    private String applyEndTime;
    //license剩余数量
    private Integer licenseSurplus;
}
