package com.particle.generator.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONUtil;
import com.particle.global.tool.json.JsonTool;

import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-01-04 15:01
 */
public class VoFieldsToTableColumnsTest {

	public static void main(String[] args) {
		String filePath = "/Users/yw/fh/git-source/particle/component/low-code/low-code-client/src/main/java/com/particle/lowcode/client/generator/dto/data/LowcodeSegmentTemplateVO.java";
		convert(filePath);
	}

	public static void convert(String filePath){

		List<String> list = FileUtil.readUtf8Lines(filePath);
		Iterator<String> iterator = list.iterator();

		List<Map<String, String>> result = new ArrayList<>();
		while (iterator.hasNext()){
			String line = iterator.next();
			if(line.trim().startsWith("@Schema")){
				Map<String, String> map = new HashMap<>();
				map.put("label", matchLabel(line));
				line = iterator.next();
				map.put("prop", matchProp(line));
				result.add(map);
			}
		}
		String resultStr = JSONUtil.toJsonPrettyStr(result);
		resultStr = resultStr.replace("\"label\"", "label").replace("\"prop\"", "prop");
		System.out.println(resultStr);
		ClipboardUtil.setStr(resultStr);
		System.out.println("已复制到剪切板");

	}


	public static String matchLabel(String rawText){
		String result = ReUtil.get("\".*\"", rawText.trim(), 0);
		return result.replace("\"","").split(",")[0];
	}
	public static String matchProp(String rawText){
		String[] split = rawText.split(" ");
		return split[split.length - 1].replace(";", "");
	}
}
