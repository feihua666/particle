package com.particle.global.tool.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.google.common.collect.Lists;
import com.particle.global.tool.http.HttpClientTool;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-04-26 14:07
 */
public class ExcelTest {
	public static void main(String[] args) {
		ExcelReader reader = ExcelUtil.getReader(FileUtil.file("/Users/yw/yuansu/temp/component_user.xlsx"));
		List<List<Object>> read = reader.read(1);
		for (List<Object> objectList : read) {
			JSONObject jsonObject = get();
			String userId = objectList.get(0).toString();

			jsonObject.set("userId", userId);
			jsonObject.set("tenantId", "1649388860938944514");


			try {
				String s = HttpClientTool.postJson("http://10.15.20.142:8080/admin/web/tenant_user/create", jsonObject.toString(),
						HttpClientTool.ExtConfig.builder().build().addHeader("c-token-id", "a4824c94-4c66-4f1d-9d5c-6f3bfc95e8a5")
						);
				JSONObject jsonObject1 = JSONUtil.parseObj(s);
				if (!jsonObject1.getBool("success")) {
					System.err.println(jsonObject.toString());
					System.err.println(s);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static JSONObject get(){
		String str = "{\n" +
				"    \"userId\": \"1651119271870377986\",\n" +
				"    \"name\": \"hgzsyjzx\",\n" +
				"    \"tenantId\": \"1649388860938944514\",\n" +
				"    \"isExpired\": false,\n" +
				"    \"expiredReason\": null,\n" +
				"    \"expireAt\": null,\n" +
				"    \"isLeave\": false,\n" +
				"    \"leaveReason\": null,\n" +
				"    \"leaveAt\": null\n" +
				"}";
		return JSONUtil.parseObj(str);
	}
}
