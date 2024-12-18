package com.swaggy7.licenseweb.mapper;

import com.swaggy7.licenseweb.entity.RbInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.swaggy7.licenseweb.utils.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Swaggy7
 * @since 2023-03-08
 */
public interface RbInfoMapper extends BaseMapper<RbInfo> {
//    //    @Select("select rb_ip rbIp,rb_begintime rbBeginTime,rb_endtime rbEndTime from rb_info rbInfo,match_rb matchRb where rbInfo.rb_id = matchRb.rb_id AND matchRb.rb_license_id = #{}")
//    @Select("select rb_ip rbIp,rb_begintime rbBeginTime,rb_endtime rbEndTime from rb_info matchRb where ")
//    IPage<RbInfo> selectUserpage(IPage<RbInfo> page, @Param(Constants.WRAPPER) Wrapper<RbInfo> queryWrapper);

}
