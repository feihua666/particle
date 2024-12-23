package com.particle.global.tool.template;

import com.particle.global.tool.http.HttpClientTool;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-04-27 14:47
 */
public class HttpClientToolTest {

	public static void main(String[] args) throws IOException, URISyntaxException, ParseException {

		HttpClientTool.ExtConfig extConfig = HttpClientTool.ExtConfig.builder().build().withProxy("44.33.204.72",444);
		String s = HttpClientTool.get("http://baidu.com", extConfig);
		System.out.println(s);
	}
}
