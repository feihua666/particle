package com.particle.global.projectinfo;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-05-31 23:31
 */
@Component
@Slf4j
@Data
public class RunningInfo implements InitializingBean {

	@Value("${server.port:8080}")
	private String port = "8080";

	/**
	 * 前面需要带 斜杠
	 */
	@Value("${server.servlet.context-path:}")
	private String contextPath;

	private String externalIp;
	private String localIp = "localhost";

	private String swaggerSuffix = "/swagger-ui/index.html";
	private String swaggerKnife4jSuffix = "/doc.html";


	public RunningInfo(){
		try {
			externalIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("can not get localhost",e);
		}
	}

	public Map<String,String> toMap(){

		String pattern = "http://{}:{}{}";
		String local = StrUtil.format(pattern,localIp,port,contextPath);
		String external = StrUtil.format(pattern,externalIp,port,contextPath);
		Map<String,String> map = new HashMap<>();
		map.put("Local", local);
		map.put("External", external);
		map.put("Local Swagger", local + swaggerSuffix);
		map.put("External Swagger", external + swaggerSuffix);
		map.put("Local Swagger knife4j", local + swaggerKnife4jSuffix);
		map.put("External Swagger knife4j", external + swaggerKnife4jSuffix);

		return map;
	}


	@Override
	public void afterPropertiesSet() throws Exception {

	}
}
