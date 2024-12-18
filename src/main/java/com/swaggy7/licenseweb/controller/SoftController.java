package com.swaggy7.licenseweb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swaggy7.licenseweb.entity.LicenseRegist;
import com.swaggy7.licenseweb.entity.Soft;
import com.swaggy7.licenseweb.mapper.SoftMapper;
import com.swaggy7.licenseweb.service.impl.SoftServiceImpl;
import com.swaggy7.licenseweb.utils.Result;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URLEncoder;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Swaggy7
 * @since 2024-03-07
 */
@RestController
@RequestMapping("/soft")
public class SoftController {
    @Autowired
    private SoftServiceImpl softService;
    @Autowired
    private SoftMapper softMapper;
    
    @Value("${soft.path}")
    private String path;

    @PostMapping ("/uploadSoft")
    public Result uploadSoft(Soft soft,MultipartFile file) throws IllegalStateException, IOException {
        File folder = new File(path + soft.getSoftName() + "\\" + soft.getSoftVersion());
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        System.out.println("file.get:  " + file.getOriginalFilename());
        file.transferTo(new File(folder, file.getOriginalFilename()));
        Soft softForSave = new Soft();
        softForSave.setSoftId((long) 0);
        softForSave.setSoftName(soft.getSoftName());
        softForSave.setSoftVersion(soft.getSoftVersion());
        softForSave.setSoftPath(folder + "\\" + soft.getSoftPath());
        softService.save(softForSave);
        return Result.success();
    };

    @GetMapping("/getAllSoftInfo")
    public Result getAllSoftInfo(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize){
        QueryWrapper<Soft> queryWrapper = new QueryWrapper<Soft>().select("soft_id", "soft_name", "soft_version");
        return Result.success(softService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @GetMapping ("/test")
    public void downloadSoft(@RequestParam Integer softId, HttpServletResponse response){

        QueryWrapper<Soft> wrapper = new QueryWrapper<>();
        wrapper.eq("soft_id",softId);
        Soft soft = softMapper.selectOne(wrapper);
        String softPath = soft.getSoftPath();

        File file = new File(softPath);
        System.out.println("file: " + file);
        // 取得文件名。
        String fileName = file.getName();

        System.out.println("fineName: " + fileName);
        InputStream fis = null;
        try {
            fis = new FileInputStream(file);

//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/html; charset=UTF-8");
            response.setHeader("content-disposition" ,  "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setHeader("Content-Length", String.valueOf(file.length()));

            byte[] b = new byte[1024];
            int len;
            while ((len = fis.read(b)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
            response.flushBuffer();
            fis.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @GetMapping("downloadSoft")
    public void test(@RequestParam Integer softId, HttpServletResponse response) throws UnsupportedEncodingException {
    	String url = "http://127.0.0.1:8081/soft/test?softId=" + softId;
    	ResponseExtractor<Boolean> responseExtractor = clientHttpResponse -> {
			// 设置响应头，直接用第三方文件服务的响应头
			HttpHeaders headers = clientHttpResponse.getHeaders();
			headers.forEach((key, value) -> response.setHeader(key, value.get(0)));
//			response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
			// 收到响应输入流即时拷贝写出到响应输出流中: inputStream -> outputStream
			StreamUtils.copy(clientHttpResponse.getBody(), response.getOutputStream());
			return true;
		};
		Map<String, String> uriVariables = new HashMap<>();
		RestTemplate restTemplate = new RestTemplate();
		Boolean execute = restTemplate.execute(url, HttpMethod.GET, null, responseExtractor, uriVariables);
		System.out.println("res:" + execute);
    }

    @GetMapping("/deleteSoft")
    public Result deleteSoft(@RequestParam Integer softId){
        QueryWrapper<Soft> wrapper = new QueryWrapper<>();
        wrapper.eq("soft_id", softId);
        Soft soft = softMapper.selectOne(wrapper);
        String softPath = soft.getSoftPath();

        File file = new File(softPath);
        file.delete();
        softService.remove(wrapper);
        return Result.success();
    }
}
