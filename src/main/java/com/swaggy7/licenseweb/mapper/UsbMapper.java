package com.swaggy7.licenseweb.mapper;

import com.swaggy7.licenseweb.entity.Usb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Swaggy7
 * @since 2024-04-17
 */
@Mapper
public interface UsbMapper extends BaseMapper<Usb> {

    //为了方便通过usbName获取usbId;
    @Select("select usb_id from usb where usb_name = #{usbName}")
    Integer getUsbIdFromUsbName(String usbName);
}
