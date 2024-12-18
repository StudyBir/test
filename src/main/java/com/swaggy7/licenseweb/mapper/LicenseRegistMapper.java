package com.swaggy7.licenseweb.mapper;

import com.swaggy7.licenseweb.bean.LicenseBean;
import com.swaggy7.licenseweb.entity.LicenseRegist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swaggy7.licenseweb.utils.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Swaggy7
 * @since 2023-03-01
 */
//@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
@Repository
public interface LicenseRegistMapper extends BaseMapper<LicenseRegist> {
//select count(1) from rb_info where rb_license_name = "License1" and isnull(rb_endtime);

    @Select("select " +
            "licenseRegist.license_ip licenseIp," +
            "licenseRegist.license_port licensePort," +
            "licenseRegist.license_acc_num licenseAccNum," +
            "Am.am_ip_acc amIpAcc, " +
            "Am.am_head_ip amHeadIp, " +
            "Am.am_end_ip amEndIp, " +
            "Am.am_time_acc amTimeAcc, " +
            "Am.am_begin_time amBegintime, " +
            "Am.am_end_time amEndtime, " +
            "Ep.ep_time_acc epTimeAcc, " +
            "Ep.ep_limittime epLimittime, " +
            "Ep.ep_range_acc epRangeAcc, " +
            "Ep.ep_recovertime epRecovertime " +
            "from license_regist licenseRegist,am Am,ep Ep " +
            "where LicenseRegist.license_name = #{sideName} AND LicenseRegist.license_am = Am.am_id AND LicenseRegist.license_ep = Ep.ep_id")
    List<LicenseBean> getLicenseInfo(String licenseName);

    @Select("select count(1) from rb_info where rb_license_name = #{sideName} and isnull(rb_endtime);")
    Integer getUsedNum(String licenseName);

}
