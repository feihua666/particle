package com.particle.report.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.particle.global.tool.http.HttpClientTool;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.pinyin.Pinyin;
import com.particle.global.tool.pinyin.PinyinTool;
import io.swagger.v3.core.util.Json;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-09-15 09:27
 */
public class TempTest {
	public static void main(String[] args) throws IOException, BadHanyuPinyinOutputFormatCombination {

		String cTokenId = "6e9b60ad-141c-4cac-b85c-fd4c5566667f";
		String parentId = "1704762692855676930";
		String s1 = FileUtil.readUtf8String("/Users/yw/yuansu/temp/gupianxinxi.json");
		JSONArray objects = JSONUtil.parseArray(s1);
/*
		String[] orderIds = new String[]{
				"F1003",
				"F1001",
				"F1004",
				"F1006",
				"G3001",
				"F1007",
				"F2001",
				"M3001",
				"B1013",
				"M3002",
				"M3003",
				"H7004",
				"H1001",
				"H2001",
				"H4008",
				"K3001",
				"I1017",
				"I1018",
				"M3005",
				"I2008",
				"D3005",
				"I4001",
				"I4004",
				"F1002"};
		JSONArray objectsNew = new JSONArray();
		for (String orderId : orderIds) {
			for (Object object : objects) {
				if (((Map) object).get("id").equals(orderId)) {
					objectsNew.add(object);
				}
			}
		}
		objects = objectsNew;*/

		List children = objects;

		List pinyinList = new ArrayList();

		for (Object childrenItem : children) {
			Object header = ((Map) childrenItem).get("header");
			List headerList = (List) header;
			List headerNew = new ArrayList();
			String childrenItemText = (String) ((Map<?, ?>) childrenItem).get("text");
			String childrenItemId = (String) ((Map<?, ?>) childrenItem).get("id");
			for (Object o2 : headerList) {
				Map o21 = (Map) o2;
				Object key = o21.get("prop");
				Object label = o21.get("label");
				Object showInDetail = o21.get("showInDetail");
				if ("operation".equals(key) || (showInDetail != null && ((Boolean) showInDetail))) {
					continue;
				}

				Map headerItem = new HashMap();
				headerItem.put("valueKey", key);
				headerItem.put("valueLabel", label);
				headerNew.add(headerItem);
			}


			String toJsonPrettyStr = JSONUtil.toJsonPrettyStr(headerNew);
			String pinyin = getPinyin(childrenItemText);
			pinyinList.add(pinyin);
			Map<String, Object> param = new HashMap<>();
			param.put("id", "1702597354596679682");
			param.put("parentId", parentId);
			param.put("isIncludeAllChildren", "false");
			param.put("keyWordReplace", StrUtil.format(
					"通用表格复制模板={}===GGGGGGGG={}===tongYongBiaoGeFuZhiMuBan={}==={{header}}={}",
					childrenItemText,childrenItemId, pinyin,toJsonPrettyStr));

			String s = HttpClientTool.postJson(
					"http://localhost:8080/admin/web/report_segment_template/copy",
					JsonTool.toJsonStr(param),
					HttpClientTool.ExtConfig.builder().build().addHeader("c-token-id", cTokenId));

			System.out.println(s);

		}

		pinyinList.stream().forEach(item -> System.out.println("#(child.outputVar." + item + ")"));
	}


	public static String getPinyin(String text) throws BadHanyuPinyinOutputFormatCombination {
		Pinyin pinyin = PinyinTool.getPinyin(text);

		String result =  pinyin.getPinyins().stream().map(item -> StrUtil.upperFirst(item[0])).collect(Collectors.joining());
		return result = StrUtil.lowerFirst(result);
	}
}
