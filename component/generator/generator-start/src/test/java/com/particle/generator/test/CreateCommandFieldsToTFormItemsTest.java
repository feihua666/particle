package com.particle.generator.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONUtil;

import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-01-04 15:01
 */
public class CreateCommandFieldsToTFormItemsTest {

	public static void main(String[] args) {
		String filePath = "/Users/yw/fh/git-source/particle/component/low-code/low-code-client/src/main/java/com/particle/lowcode/client/generator/dto/command/LowcodeSegmentTemplateCreateCommand.java";
		convert(filePath);
	}

	public static void convert(String filePath){

		List<String> list = FileUtil.readUtf8Lines(filePath);
		Iterator<String> iterator = list.iterator();

		List<String> result = new ArrayList<>();
		while (iterator.hasNext()){
			String line = iterator.next();
			if(line.trim().startsWith("@Schema")){
				Map<String, String> map = new HashMap<>();
				map.put("label", matchLabel(line));
				line = iterator.next();
				map.put("prop", matchProp(line));
				result.add(item(map.get("label"),map.get("prop")));
			}
		}
		String resultStr = result.toString();
		System.out.println(resultStr);
		ClipboardUtil.setStr(resultStr);
		System.out.println("已复制到剪切板");

	}


	private static String matchLabel(String rawText){
		return VoFieldsToTableColumnsTest.matchLabel(rawText);
	}
	private static String matchProp(String rawText){
		return VoFieldsToTableColumnsTest.matchProp(rawText);
	}
	private static String item(String label,String prop){
		return "       \n{\n" +
				"        field: {\n" +
				"          name: '"+ prop +"'\n" +
				"        },\n" +
				"        element: {\n" +
				"          comp: 'el-input',\n" +
				"          formItemProps: {\n" +
				"            label: '" + label +"',\n" +
				"            required: true\n" +
				"          },\n" +
				"          compProps: {\n" +
				"            clearable: true,\n" +
				"          }\n" +
				"        }\n" +
				"      }";
	}
}
