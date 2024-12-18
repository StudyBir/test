package com.swaggy7.licenseweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.swaggy7.licenseweb.bean.LicenseBean;
import com.swaggy7.licenseweb.bean.LicenseTransmit;
import com.swaggy7.licenseweb.bean.MsgToServer;
import com.swaggy7.licenseweb.entity.LicenseRegist;
import com.swaggy7.licenseweb.mapper.LicenseRegistMapper;
import com.swaggy7.licenseweb.service.impl.LicenseRegistServiceImpl;
import com.swaggy7.licenseweb.utils.Result;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Swaggy7
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/licenseInfo")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LicenseRegistController {
	@Autowired
	private LicenseRegistServiceImpl licenseRegistService;

	@Autowired
	private LicenseRegistMapper licenseRegistMapper;

	@Value("${trans.ip}")
	private String transIp;

	@GetMapping("/getLicenseInfo")
	public Result getLicenseInfo(@RequestParam String sideName) {

		Integer usedNum = licenseRegistService.getBaseMapper().getUsedNum(sideName);

		List<LicenseBean> licenseInfo = licenseRegistService.getBaseMapper().getLicenseInfo(sideName);

		for (LicenseBean lb : licenseInfo) {
			lb.setLicenseUsedNum(usedNum);
		}
		return Result.success(licenseInfo);
	}

	@GetMapping("/getAllLicenseInfo")
	public Result getAllLicenseInfo() {
		List<LicenseRegist> allLicenseInfo = licenseRegistService.getBaseMapper().selectList(null);
		return Result.success(allLicenseInfo);
	}

	@GetMapping("/getLicenseName")
	public Result getLicenseName() {
		QueryWrapper<LicenseRegist> queryWrapper = new QueryWrapper<LicenseRegist>().select("distinct license_name")
				.orderByAsc("license_name");
		List<LicenseRegist> licenseName = licenseRegistService.getBaseMapper().selectList(queryWrapper);

		return Result.success(licenseName);
	}

	@GetMapping("/deleteLicense")
	public Result deleteLicense(@RequestParam Integer removeId) {
		QueryWrapper<LicenseRegist> wrapper = new QueryWrapper<>();
		wrapper.eq("license_id", removeId);
		licenseRegistService.remove(wrapper);
		return Result.success();
	}

	@GetMapping("/getDelSocketInfo")
	public Result getDelSocketInfo(@RequestParam String LicenseName, @RequestParam String NodeId) throws IOException {
		System.out.println("trains: " + transIp);
		System.out.println("licenseName:" + LicenseName);
		System.out.println("NodeId:" + NodeId);
		Socket socket = new Socket(transIp, 6666);
		OutputStream os = socket.getOutputStream(); // 字节输出流
		PrintWriter pw = new PrintWriter(os); // 将输出流包装为打印流
		MsgToServer msgToServer = new MsgToServer();
		msgToServer.setLicenseName(LicenseName);
		msgToServer.setRbId(NodeId);
		Gson gson = new Gson();
		String toServer = gson.toJson(msgToServer);
		pw.write("Rec_" + toServer);
		pw.flush();
		pw.close();
		socket.close();

		return Result.success();
	}

	@PostMapping("/insertLicenseInfo")
	public Result insertLicenseInfo(@RequestBody LicenseRegist licenseRegist) throws Exception {
		System.out.println(licenseRegist);
		QueryWrapper<LicenseRegist> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("license_name", licenseRegist.getLicenseName());
		LicenseRegist theOneResult = licenseRegistMapper.selectOne(queryWrapper);
		if (theOneResult == null) {
			licenseRegistService.save(licenseRegist);
			Socket socket = new Socket(transIp, 7777);
			OutputStream os = socket.getOutputStream(); // 字节输出流
			PrintWriter pw = new PrintWriter(os); // 将输出流包装为打印流
			Gson gson = new Gson();
			LicenseTransmit lic = new LicenseTransmit(licenseRegist.getLicenseName(),
					Integer.valueOf(licenseRegist.getLicenseTransPort()));
			String toServer = gson.toJson(lic);
			pw.write(toServer);
			pw.flush();
			pw.close();
			socket.close();
			return Result.success();
		} else {
			return Result.error("该License已经注册过");
		}
	}

	@PostMapping("/modifyLicenseInfo")
	public Result modifyLicenseInfo(@RequestBody LicenseRegist licenseRegist) {
		System.out.println(licenseRegist);
		QueryWrapper<LicenseRegist> queryWrapper = new QueryWrapper<>();

		QueryWrapper<LicenseRegist> getLicenseNamequeryWrapper = new QueryWrapper<LicenseRegist>()
				.select("distinct license_name");
		List<LicenseRegist> licenses = licenseRegistService.getBaseMapper().selectList(getLicenseNamequeryWrapper);
		for (LicenseRegist licenseRegist1 : licenses) {
			if (licenseRegist.getLicenseName().equals(licenseRegist.getLicenseName())) {
				queryWrapper.eq("license_name", licenseRegist.getLicenseName());
				licenseRegistService.update(licenseRegist, queryWrapper);
				return Result.success();
			} else {
				return Result.error("该License不存在");
			}
		}
		return null;

	}

}
