package com.swaggy7.licenseweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swaggy7.licenseweb.dto.UsbKeyApplyInfo;
import com.swaggy7.licenseweb.entity.Usb;
import com.swaggy7.licenseweb.entity.UserUsb;
import com.swaggy7.licenseweb.entity.UserWeb;
import com.swaggy7.licenseweb.mapper.UsbMapper;
import com.swaggy7.licenseweb.mapper.UserUsbMapper;
import com.swaggy7.licenseweb.service.impl.UsbServiceImpl;
import com.swaggy7.licenseweb.service.impl.UserUsbServiceImpl;
import com.swaggy7.licenseweb.utils.Result;
import com.swaggy7.licenseweb.vo.UsbKeyManage;
import com.swaggy7.licenseweb.vo.UserUsbMonitor;
import com.swaggy7.licenseweb.vo.UserWebMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Swaggy7
 * @since 2024-04-17
 */
@RestController
@RequestMapping("/userUsb")
public class UserUsbController {

    @Autowired
    private UsbServiceImpl usbService;

    @Autowired
    private UserUsbServiceImpl userUsbService;

    @Autowired
    private UserUsbMapper userUsbMapper;

    @Autowired
    private UsbMapper usbMapper;


    //获取申请信息
    @GetMapping("/getApplyMonitorInfo")
    public Result getApplyMonitorInfo(@RequestParam String usbName,
                                      @RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize
    ) {
        Page<Object> page = new Page<>(pageNum, pageSize);
        Integer usbId = usbMapper.getUsbIdFromUsbName(usbName);
        IPage<UserUsbMonitor> allApplyInfo = userUsbMapper.getAllApplyMonitorInfo(page, usbId);
        return Result.success(allApplyInfo);
    }


    //获取申请信息
    @GetMapping("/getRecycleMonitorInfo")
    public Result getRecycleMonitorInfo(@RequestParam String usbName,
                                      @RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize
    ) {
        Page<Object> page = new Page<>(pageNum, pageSize);
        Integer usbId = usbMapper.getUsbIdFromUsbName(usbName);
        IPage<UserUsbMonitor> allRecycleInfo = userUsbMapper.getAllRecycleMonitorInfo(page, usbId);
        return Result.success(allRecycleInfo);
    }

    @RequestMapping("/getUsbInfo")
    public Result getUsbInfo(@RequestParam Integer pageNum,
                             @RequestParam Integer pageSize,
                             @RequestParam String userName,
                             @RequestParam String userDept,
                             @RequestParam String userAccount){

        // 创建分页对象
        Page<UsbKeyManage> page = new Page<>(pageNum, pageSize);
        List<UsbKeyManage> userUsbInfo = userUsbMapper.getUserUsbInfo(userName, userDept, userAccount);
        // 将查询结果封装到分页对象中
        page.setRecords(userUsbInfo);
        return Result.success(page);
    }


    @RequestMapping("/applyUsbKey")
    public Result applyUsbKey(@RequestBody UsbKeyApplyInfo usbKeyApplyInfo){
        UserUsb oneInfoForApply = userUsbMapper.getOneInfoForApply(usbKeyApplyInfo.getUsbName(), usbKeyApplyInfo.getUserName(), usbKeyApplyInfo.getUserDept(), usbKeyApplyInfo.getUserAccount());
        if(oneInfoForApply != null){
            System.out.println("-----------------------------------------------------------for111111");
//            oneInfoForApply.setUserUsbStatus("applying");
//            oneInfoForApply.setUsbTime(usbKeyApplyInfo.getUsbTime());
            return Result.error("该usb已经申请过或正在等待审批，请勿重复申请");
        }else{
            QueryWrapper<Usb> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("usb_id")
                    .eq("usb_name", usbKeyApplyInfo.getUsbName());
            Usb one = usbService.getOne(queryWrapper);
            Integer usbId = one.getUsbId();

            UserUsb userUsb = new UserUsb();
            userUsb.setUsbId(usbId);
            userUsb.setUserName(usbKeyApplyInfo.getUserName());
            userUsb.setUserDept(usbKeyApplyInfo.getUserDept());
            userUsb.setUserAccount(usbKeyApplyInfo.getUserAccount());
            userUsb.setUserUsbStatus("applying");
            userUsb.setUsbTime(usbKeyApplyInfo.getUsbTime());
            userUsbService.save(userUsb);
        }
        return Result.success();
    }



    @PostMapping("/agreeApply")
    public Result agreeApply(@RequestBody UserUsb userUsb){
        System.out.println("usb: " + userUsb);
        UpdateWrapper<UserUsb> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_usb_id", userUsb.getUserUsbId())
                .set("user_usb_status", "apply")
                .set("usb_number",userUsb.getUsbNumber())
                .set("usb_time",userUsb.getUsbTime()); // 将 status 字段更新为空字符串

        boolean updateResult = userUsbService.update(updateWrapper);
        if (updateResult) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }


    @GetMapping("/refuseApply")
    public Result refuseApply(@RequestParam Integer rowId){
        QueryWrapper<UserUsb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_usb_id",rowId); // 设置条件，替换 your_column_name 和 condition

        int delete = userUsbMapper.delete(queryWrapper);
        if (delete > 0) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }

    @GetMapping("/releaseRecycle")
    public Result releaseRecycle(@RequestParam Integer rowId){
        QueryWrapper<UserUsb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_usb_id",rowId); //

        int delete = userUsbMapper.delete(queryWrapper);
        if (delete > 0) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }


}
