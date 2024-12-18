package com.swaggy7.licenseweb.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swaggy7.licenseweb.entity.UserUsb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.swaggy7.licenseweb.entity.UserWeb;
import com.swaggy7.licenseweb.vo.UsbKeyManage;
import com.swaggy7.licenseweb.vo.UserUsbMonitor;
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
 * @since 2024-04-17
 */
@Mapper
public interface UserUsbMapper extends BaseMapper<UserUsb> {

    //找到需要添加或修改的那条userusb数据
    @Select("select a.* from user_usb a left join usb b on a.usb_id = b.usb_id where b.usb_name = #{usbName} and a.user_name = #{userName} and a.user_dept = #{userDept} and a.user_account = #{userAccount}")
    UserUsb getOneInfoForApply(String usbName,String userName,String userDept,String userAccount);


    @Select("select a.usb_number,b.usb_name from user_usb a left join usb b on a.usb_id = b.usb_id where a.user_name = #{userName} and a.user_dept = #{userDept} and a.user_account = #{userAccount} and a.usb_number != ''")
    List<UsbKeyManage> getUserUsbInfo(String userName, String userDept, String userAccount);

    //获取申请信息
    @Select("select user_usb_id, user_name,user_dept,user_account,usb_time from user_usb where user_usb_status = 'applying' and usb_id = #{usbId}")
    IPage<UserUsbMonitor> getAllApplyMonitorInfo(Page<?> page, Integer usbId);

    //获取回收信息
    @Select("select user_usb_id, user_name, user_dept, user_account, usb_time, usb_number from user_usb where user_usb_status = 'apply' and usb_id = #{usbId}")
    IPage<UserUsbMonitor> getAllRecycleMonitorInfo(Page<?> page, Integer usbId);
}
