package com.swaggy7.licenseweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swaggy7.licenseweb.entity.*;
import com.swaggy7.licenseweb.mapper.WebMapper;
import com.swaggy7.licenseweb.service.impl.WebServiceImpl;
import com.swaggy7.licenseweb.utils.Result;
import com.swaggy7.licenseweb.vo.UserWebStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Swaggy7
 * @since 2024-03-11
 */
@RestController
@RequestMapping("/web")
public class WebController {

    @Autowired
    private WebServiceImpl webService;

    @Autowired
    private WebMapper webMapper;

    @PostMapping("/addWebResource")
    public Result addWebResource(@RequestBody Web web) {
        webService.save(web);
        return Result.success();
    }

    @GetMapping("/getAllWebInfo")
    public Result getAllWebInfo(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam String userName) {
        Page<Object> page = new Page<>(pageNum, pageSize);
        IPage<UserWebStatus> allWebInfo = webMapper.getAllWebInfo(page, userName);
        return Result.success(allWebInfo);
    }


    //获取webName
    @GetMapping("/getWebName")
    public Result getWebName() {
        QueryWrapper<Web> queryWrapper = new QueryWrapper<Web>().select("distinct web_name");
        List<Web> licenseName = webService.getBaseMapper().selectList(queryWrapper);

        return Result.success(licenseName);
    }

    @GetMapping("/deleteWeb")
    public Result deleteWeb(@RequestParam Integer webId){
        QueryWrapper<Web> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("web_id",webId); // 设置删除条件

        webService.remove(queryWrapper);
        return Result.success();
    }
}
