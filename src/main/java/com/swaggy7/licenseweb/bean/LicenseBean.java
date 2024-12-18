package com.swaggy7.licenseweb.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LicenseBean implements Serializable {
    private String licenseIp;
    private String licensePort;
    private Integer licenseAccNum;
    private Integer licenseUsedNum;
    private Integer amIpAcc;
    private String amHeadIp;
    private String amEndIp;
    private Integer amTimeAcc;
    private String amBegintime;
    private String amEndtime;
    private Integer epTimeAcc;
    private Integer epLimittime;
    private Integer epRangeAcc;
    private String epRecovertime;
}
