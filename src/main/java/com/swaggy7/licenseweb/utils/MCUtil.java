package com.swaggy7.licenseweb.utils;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;

public class MCUtil {

	public static RestTemplate restTemplate = new RestTemplate();

	public static void batchSave(String mcIp, String reqUrl, String opeName, String opeContent, String executor) {
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(3 * 1000);
        simpleClientHttpRequestFactory.setReadTimeout(3 * 1000);
        restTemplate.setRequestFactory(simpleClientHttpRequestFactory);
		JSONObject requestMap = new JSONObject();
		String uuid = UUID.randomUUID().toString();
		requestMap.put("id", uuid);
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String ip = request.getHeader("X-Real-IP");
		requestMap.put("ip", ip);
		requestMap.put("reqUrl", reqUrl);
		requestMap.put("opeName", opeName);
		requestMap.put("opeContent", opeContent);
		requestMap.put("executor", executor);
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		requestMap.put("executionTime", dateFormat.format(date));
		requestMap.put("logType", "操作日志");
		requestMap.put("moduleType", "mc-license");
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(requestMap);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Type", "application/json;charset=UTF-8");
		HttpEntity<String> entity = new HttpEntity(jsonArray, httpHeaders);
		JSONObject result = restTemplate.postForObject("http://" + mcIp + ":8088/sysLogs/batchSave", entity,
				JSONObject.class);
	}

}
