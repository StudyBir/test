package com.swaggy7.licenseweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swaggy7.licenseweb.entity.LicenseRegist;
import com.swaggy7.licenseweb.entity.Soft;
import com.swaggy7.licenseweb.entity.Usb;
import com.swaggy7.licenseweb.entity.UserUsb;
import com.swaggy7.licenseweb.entity.Web;
import com.swaggy7.licenseweb.service.impl.UsbServiceImpl;
import com.swaggy7.licenseweb.utils.Result;
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
@RequestMapping("/usb")
public class UsbController {
    @Autowired
    private UsbServiceImpl usbService;

    //获取uebName
    @GetMapping("/getUsbName")
    public Result getUsbName() {
        QueryWrapper<Usb> queryWrapper = new QueryWrapper<Usb>().select("distinct usb_name");
        List<Usb> usbName = usbService.getBaseMapper().selectList(queryWrapper);

        return Result.success(usbName);
    }

    @GetMapping("/getAllUsbKeys")
    public Result getAllUsbKeys(){
        QueryWrapper<Usb> queryWrapper = new QueryWrapper<Usb>().select("distinct usb_name");
        List<Usb> usbNames = usbService.getBaseMapper().selectList(queryWrapper);

        return Result.success(usbNames);
    }


    @PostMapping("/uploadUsbKey")
    public Result uploadUsbKey(@RequestBody Usb usb){
        usbService.save(usb);
        return Result.success();
    }
    
    @GetMapping("/deleteUsbKey")
    public Result deleteUsbKey(@RequestParam String usbName) {
    	QueryWrapper<Usb> wrapper = new QueryWrapper<>();
        wrapper.eq("usb_name", usbName);
        usbService.remove(wrapper);
    	return Result.success();
    }
}
