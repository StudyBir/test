package com.swaggy7.licenseweb.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.URLDecoder;

import com.alibaba.fastjson.JSONObject;
import com.swaggy7.licenseweb.utils.MCUtil;
import com.swaggy7.licenseweb.utils.OpeEnum;

public class LogFilter implements Filter {

	@Value("${mc.ip}")
	private String mcIP;

	public LogFilter(String mcIP) {
		super();
		this.mcIP = mcIP;
	}

	public String getMcIP() {
		return mcIP;
	}

	public void setMcIP(String mcIP) {
		this.mcIP = mcIP;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 过滤器初始化
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter");
		try {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			String reqUrl = httpServletRequest.getRequestURI();
			System.out.println(reqUrl);
			String opeName = OpeEnum.stringOf(reqUrl);
			String opeContent = "";
			String method = httpServletRequest.getMethod();
			String userName = "";
			String userAccount = "";
			if(reqUrl.contains("uploadSoft")) {
				chain.doFilter(request, response);
			}
			else if (method.equals("GET") || method.equals("DELETE")) {
				String queryString = httpServletRequest.getQueryString();
				opeContent = URLDecoder.decode(queryString, "utf-8");
				userName = httpServletRequest.getParameter("userName");
				userAccount = httpServletRequest.getParameter("userAccount");
				chain.doFilter(request, response);
			}
			else if (method.equals("POST") || method.equals("PUT")) {

				StringBuffer data = new StringBuffer();
				String line = null;
				BufferedReader reader = null;
				try {
					System.out.println("22222222222");
					reader = httpServletRequest.getReader();
					while (null != (line = reader.readLine()))
						data.append(line);
					System.out.println("33333333333333");
					JSONObject json = JSONObject.parseObject(data.toString());
					opeContent = json.toString();
					userName = json.getString("userName");
					userAccount = json.getString("userAccount");
					System.out.println("44444444444444444444");
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					
				}
				System.out.println("1111111111111");
				String requestBody = data.toString();

				// 创建自定义的HttpServletRequestWrapper，传递请求体的副本给后续的过滤器或目标处理程序
				HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(httpServletRequest) {
					@Override
					public ServletInputStream getInputStream() throws IOException {
						byte[] requestBodyBytes = requestBody.getBytes();
						return new CustomServletInputStream(requestBodyBytes);
					}

					@Override
					public BufferedReader getReader() throws IOException {
						return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(requestBody.getBytes())));
					}
				};
				System.out.println("POST DOFILTER");
				chain.doFilter((ServletRequest) wrappedRequest, response);
			}


			String executor = userName + "[" + userAccount + "]";
			System.out.println("mcIP: " + mcIP + ";      URI:" + reqUrl +  ";      opeName: " + opeName + ";      opeContent: " + opeContent + ";      executor:" + executor);

			MCUtil.batchSave(mcIP, reqUrl, opeName, opeContent, executor);


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 继续过滤链
//			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// 过滤器销毁
	}

}
