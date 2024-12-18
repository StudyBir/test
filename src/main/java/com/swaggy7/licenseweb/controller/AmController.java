package com.swaggy7.licenseweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swaggy7.licenseweb.entity.Am;
import com.swaggy7.licenseweb.entity.LicenseRegist;
import com.swaggy7.licenseweb.mapper.AmMapper;
import com.swaggy7.licenseweb.mapper.LicenseRegistMapper;
import com.swaggy7.licenseweb.service.impl.AmServiceImpl;
import com.swaggy7.licenseweb.utils.Result;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Swaggy7
 * @since 2023-03-16
 */
@RestController
@RequestMapping("/am")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AmController {
    @Autowired
    private AmServiceImpl amService;

    @Autowired
    private AmMapper amMapper;

    @Autowired
    private LicenseRegistMapper licenseRegistMapper;



    @GetMapping("/getAllAmInfo")
    public Result getAllAmInfo(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize) {
        QueryWrapper<Am> queryWrapper = new QueryWrapper<Am>().select("am_id", "am_ip_acc", "am_head_ip", "am_end_ip", "am_time_acc", "am_begin_time", "am_end_time");
        return Result.success(amService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @PostMapping("/insertAmInfo")
    public Result insertAmInfo(@RequestBody Am am) {
        System.out.println("am:    " + am);
        amService.save(am);
        return Result.success();
    }

    @DeleteMapping("/deleteAmInfo")
    public Result deleteAmInfo(@RequestParam Integer amId){
        // 创建查询条件
        QueryWrapper<LicenseRegist> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("license_am").eq("license_am", amId);
        // 执行查询
        int count = licenseRegistMapper.selectCount(queryWrapper);
        if(count > 0){
            return Result.error("当前正有license在使用这个申请策略，无法删除");
        }else{
            amMapper.deleteById(amId);
            return Result.success();
        }
    }
}
