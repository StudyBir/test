package com.swaggy7.licenseweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swaggy7.licenseweb.dto.MultiUserApplyInfo;
import com.swaggy7.licenseweb.entity.UserWeb;
import com.swaggy7.licenseweb.mapper.UserWebMapper;
import com.swaggy7.licenseweb.service.impl.UserWebServiceImpl;
import com.swaggy7.licenseweb.utils.Result;
import com.swaggy7.licenseweb.vo.UserWebMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Swaggy7
 * @since 2024-03-12
 */
@RestController
@RequestMapping("/userWeb")
public class UserWebController {

    @Autowired
    private UserWebServiceImpl userWebService;
    @Autowired
    private UserWebMapper userWebMapper;

    @PostMapping("/applyUserWeb")
    public Result applyWeb(@RequestBody UserWeb userWeb) {
        System.out.println("userWeb : " + userWeb);
        QueryWrapper<UserWeb> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userWeb.getUserName())
                .eq("user_dept", userWeb.getUserDept())
                .eq("user_account", userWeb.getUserAccount())
                .eq("web_id", userWeb.getWebId());

        // 查询数据库中是否存在符合条件的记录
        UserWeb existingRecord = userWebService.getOne(queryWrapper);


        if (existingRecord != null) {
            // 构建更新条件
            UpdateWrapper<UserWeb> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_web_id", existingRecord.getUserWebId())
                    .set("user_web_status", userWeb.getUserWebStatus())
                    .set("web_time",userWeb.getWebTime());
            userWebService.update(null, updateWrapper);
        } else {
            userWebService.save(userWeb);
        }
        return Result.success();
    }


    @PostMapping("/releaseUserWeb")
    public Result releaseUseWeb(@RequestBody UserWeb userWeb){
        UpdateWrapper<UserWeb> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_name", userWeb.getUserName())
                .eq("web_id", userWeb.getWebId())
                .set("user_web_status", ""); // 将 status 字段更新为空字符串
        boolean updateResult = userWebService.update(updateWrapper);
        if (updateResult) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }



    @GetMapping("/getUserWebMonitorInfo")
    public Result getUserWebMonitorInfo(@RequestParam String webName,
                            @RequestParam Integer pageNum,
                            @RequestParam Integer pageSize
    ) {
        Page<Object> page = new Page<>(pageNum, pageSize);
        IPage<UserWebMonitor> allWebInfo = userWebMapper.getAllUserMonitorInfo(page, webName);
        return Result.success(allWebInfo);
    }

    @GetMapping("/getApplyMonitorInfo")
    public Result getApplyMonitorInfo(@RequestParam String webName,
                            @RequestParam Integer pageNum,
                            @RequestParam Integer pageSize
    ) {
        Page<Object> page = new Page<>(pageNum, pageSize);
        IPage<UserWebMonitor> allApplyInfo = userWebMapper.getAllApplyMonitorInfo(page, webName);
        return Result.success(allApplyInfo);
    }

    @PostMapping("/agreeApply")
    public Result agreeApply(@RequestBody UserWeb userWeb){
        System.out.println("usb: " + userWeb);
        UpdateWrapper<UserWeb> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_web_id", userWeb.getUserWebId())
                .set("user_web_status", "apply")
                .set("web_time",userWeb.getWebTime()); // 将 status 字段更新为空字符串

        boolean updateResult = userWebService.update(updateWrapper);
        if (updateResult) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("/releaseUse")
    public Result releaseUse(@RequestBody UserWeb userWeb){
        UpdateWrapper<UserWeb> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_web_id", userWeb.getUserWebId())
                .set("user_web_status", ""); // 将 status 字段更新为空字符串
        boolean updateResult = userWebService.update(updateWrapper);
        if (updateResult) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("/refuseApply")
    public Result refuseApply(@RequestBody UserWeb userWeb){
        UpdateWrapper<UserWeb> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_web_id", userWeb.getUserWebId())
                .set("user_web_status", ""); // 将 status 字段更新为空字符串
        boolean updateResult = userWebService.update(updateWrapper);
        if (updateResult) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("/agreeAllApply")
    public Result agreeAllApply(@RequestBody MultiUserApplyInfo multiUserApplyInfo){
        // 创建查询条件
        QueryWrapper<UserWeb> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_web_id", multiUserApplyInfo.getUserWebIds());

        UserWeb updateEntity = new UserWeb();
        updateEntity.setUserWebStatus("apply");
        updateEntity.setWebTime(multiUserApplyInfo.getWebTime());
        int updateCount = userWebMapper.update(updateEntity, queryWrapper);
        if (updateCount > 0) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }

    @PostMapping("/releaseAllUse")
    public Result releaseAllUse(@RequestBody MultiUserApplyInfo multiUserApplyInfo){
        System.out.println("userWeb::::::: " + multiUserApplyInfo);
        // 创建查询条件
        QueryWrapper<UserWeb> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_web_id", multiUserApplyInfo.getUserWebIds());

        UserWeb updateEntity = new UserWeb();
        updateEntity.setUserWebStatus("");
        int updateCount = userWebMapper.update(updateEntity, queryWrapper);
        if (updateCount > 0) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }

}
