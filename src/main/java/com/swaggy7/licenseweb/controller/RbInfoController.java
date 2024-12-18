package com.swaggy7.licenseweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swaggy7.licenseweb.entity.LicenseRegist;
import com.swaggy7.licenseweb.entity.RbInfo;
import com.swaggy7.licenseweb.service.impl.RbInfoServiceImpl;
import com.swaggy7.licenseweb.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Swaggy7
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/rbInfo")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RbInfoController {
    @Autowired
    private RbInfoServiceImpl rbInfoService;


    @GetMapping("/getRbInfo")
    public Result getRbInfo(@RequestParam String sideName,
                            @RequestParam Integer pageNum,
                            @RequestParam Integer pageSize
    ) {

        QueryWrapper<RbInfo> queryWrapper = new QueryWrapper<RbInfo>().select("rb_id rbId,rb_ip rbIp,rb_begintime rbBegintime,rb_endtime rbEndtime").orderByAsc("rb_id");
        queryWrapper.like(true, "rb_license_name", sideName);

        return Result.success(rbInfoService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @GetMapping("/deleteRbInfo")
    public Result deleteLicense(@RequestParam Integer removeId) {
        System.out.println("removeid:" + removeId);
        QueryWrapper<RbInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("rb_id", removeId);
        rbInfoService.remove(wrapper);
        return Result.success();
    }
}
