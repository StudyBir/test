package com.swaggy7.licenseweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swaggy7.licenseweb.entity.Ep;
import com.swaggy7.licenseweb.entity.LicenseRegist;
import com.swaggy7.licenseweb.mapper.AmMapper;
import com.swaggy7.licenseweb.mapper.EpMapper;
import com.swaggy7.licenseweb.mapper.LicenseRegistMapper;
import com.swaggy7.licenseweb.service.impl.EpServiceImpl;
import com.swaggy7.licenseweb.utils.Result;
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
@RequestMapping("/ep")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EpController {

    @Autowired
    private EpServiceImpl epService;

    @Autowired
    private EpMapper epMapper;
    @Autowired
    private LicenseRegistMapper licenseRegistMapper;

    @GetMapping("/getAllEpInfo")
    public Result getAllEpInfo(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize) {
        QueryWrapper<Ep> queryWrapper = new QueryWrapper<Ep>().select("ep_id", "ep_time_acc", "ep_limittime", "ep_range_acc", "ep_recovertime");
        System.out.println(epService.getBaseMapper().selectList(queryWrapper).toString());
        return Result.success(epService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @PostMapping("/insertEpInfo")
    public Result insertEpInfo(@RequestBody Ep ep) {
        System.out.println("ep:    " + ep);
        epService.save(ep);
        return Result.success();
    }


    @DeleteMapping("/deleteEpInfo")
    public Result deleteEpInfo(@RequestParam Integer epId){
        // 创建查询条件
        QueryWrapper<LicenseRegist> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("license_ep").eq("license_ep", epId);
        // 执行查询
        int count = licenseRegistMapper.selectCount(queryWrapper);
        if(count > 0){
            return Result.error("当前正有license在使用这个回收策略，无法删除");
        }else{
            epMapper.deleteById(epId);
            return Result.success();
        }
    }
}