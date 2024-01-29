package com.particle.global.tool.template;

import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.obj.NullObj;

import java.util.HashMap;
import java.util.Map;

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

		Map<String, Object> map = new HashMap<>();
		map.put("aaa", null);
		map.put("bbb", NullObj.NULL);

		String toJsonStr = JsonTool.toJsonStr(map);

		System.out.println(toJsonStr);

		JSONObject jsonObject = JSONUtil.parseObj(toJsonStr);
		// debug查看aaa为null而不是JSONNull
		// debug查看bbb为null而不是JSONNull
		System.out.println(jsonObject);
	}
}
