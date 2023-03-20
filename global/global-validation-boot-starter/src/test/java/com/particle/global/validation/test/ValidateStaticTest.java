package com.particle.global.validation.test;

import com.particle.global.validation.ValidateTool;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-03-20 10:30
 */
public class ValidateStaticTest {
	public static void main(String[] args) {

		Map<String, Object> map = new HashMap<>();
		map.put("name", "name");
		map.put("value", "123");
		Object evaluate = ValidateTool.javascriptAssert("name == name", map);

		System.out.println(evaluate);


		evaluate = ValidateTool.javascriptAssert("function testName(name) {name == 'er'}; testName(name)", map);

		System.out.println(evaluate);
	}
}
