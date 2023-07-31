package com.particle.global.tool.template;

import com.particle.global.tool.json.JsonTool;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-07-26 14:25
 */
public class JsonToolTest {
	public static void main(String[] args) {
		System.out.println(JsonTool.isJsonStrEmpty(""));
		System.out.println(JsonTool.isJsonStrEmpty("{}"));
		System.out.println(JsonTool.isJsonStrEmpty("{   }"));
		System.out.println(JsonTool.isJsonStrEmpty("{ \"age\":22  }"));
		System.out.println(JsonTool.isJsonStrEmpty("[]"));
		System.out.println(JsonTool.isJsonStrEmpty("[   ]"));
		System.out.println(JsonTool.isJsonStrEmpty("[ 22  ]"));
	}
}
