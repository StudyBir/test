package com.swaggy7.licenseweb.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swaggy7.licenseweb.entity.UserWeb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swaggy7.licenseweb.vo.UserWebMonitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Swaggy7
 * @since 2024-03-12
 */
@Mapper
public interface UserWebMapper extends BaseMapper<UserWeb> {

    @Select("select user_web.user_web_id, user_web.user_dept, user_web.user_account, user_web.web_time, user_web.user_name from user_web join web ON user_web.web_id = web.web_id and web.web_name = #{webName} and user_web.user_web_status = 'apply'")
    IPage<UserWebMonitor> getAllUserMonitorInfo(Page<?> page, String webName);

    @Select("select user_web.user_web_id, user_web.user_dept, user_web.user_account, user_web.web_time, user_web.user_name from user_web join web ON user_web.web_id = web.web_id and web.web_name = #{webName} and user_web.user_web_status = 'applying' ")
    IPage<UserWebMonitor> getAllApplyMonitorInfo(Page<?> page, String webName);
}
