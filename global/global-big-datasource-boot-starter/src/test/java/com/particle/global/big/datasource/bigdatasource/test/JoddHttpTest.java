package com.particle.global.big.datasource.bigdatasource.test;

import jodd.http.HttpRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-03-28 20:33
 */
public class JoddHttpTest {

	public static void main(String[] args) {
		String result = HttpRequest.post("https://www.cnblogs.com/jiezai/p/12512600.html")
				.send().charset("utf-8").bodyText();

		System.out.println(result);
	}
}
