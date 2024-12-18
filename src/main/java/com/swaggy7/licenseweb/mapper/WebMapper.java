package com.swaggy7.licenseweb.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swaggy7.licenseweb.vo.UserWebStatus;
import com.swaggy7.licenseweb.entity.Web;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Swaggy7
 * @since 2024-03-11
 */
@Mapper
public interface WebMapper extends BaseMapper<Web> {

    @Select("select distinct a.*, c.user_web_status from web a left join (select * from user_web b where b.user_name = #{userName}) c on a.web_id = c.web_id")
    IPage<UserWebStatus> getAllWebInfo(Page<?> page, String userName);


}
