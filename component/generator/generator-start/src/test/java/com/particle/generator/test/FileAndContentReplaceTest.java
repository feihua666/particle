package com.particle.generator.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件替换，并生成新文件一般一个功能可能和另一个功能文件类似，只是替换就能使用
 * </p>
 *
 * @author yangwei
 * @since 2023-01-04 13:11
 */

public class FileAndContentReplaceTest {


	public static void main(String[] args) {
		String originStr = "Model";
		String newStr = "ModelItem";
		String originStrName = "模型";
		String newStrName = "模型项";

		Map<String, String> replaceContent = new HashMap<>();

		//replaceContent.put(originStr,newStr);
		replaceContent.put(StrUtil.upperFirst(originStr),StrUtil.upperFirst(newStr));
		replaceContent.put(StrUtil.lowerFirst(originStr),StrUtil.lowerFirst(newStr));
		replaceContent.put(originStrName,newStrName);

		List<String> pathList = new ArrayList<>();

		//pathList.add("/Users/yw/fh/git-source/particle/web/component/pc/lowcode/views/generator/admin");
		//pathList.add("/Users/yw/fh/git-source/particle/web/component/pc/lowcode/LowCodeRoutes.ts");
		pathList.add("");
		pathList.add("/Users/yw/fh/git-source/particle/web/component/pc/lowcode/api/generator/admin/lowCodeModelAdminApi.ts");

		for (String path : pathList) {
			if (StrUtil.isEmpty(path)) {
				continue;
			}
			replace(path, replaceContent);

		}
	}

	/**
	 * 替换
	 * @param path 文件绝对路径 支持目录和单文件。如果是目录将替换目录下的所有文件
	 */
	public static void replace(String path, Map<String,String> replaceContent){
		if (FileUtil.isDirectory(path)) {
			for (File file : FileUtil.ls(path)) {
				doReplace(file.getAbsolutePath(),replaceContent);
			}
		}else {
			doReplace(path,replaceContent);
		}
	}

	/**
	 * 文件替换，包括文件名和内容
	 * @param filePath
	 * @param replaceContent
	 */
	private static void doReplace(String filePath,Map<String,String> replaceContent){
		File file = FileUtil.file(filePath);
		String fileName = file.getName();
		String path = file.getParent();

		String newFileName = replaceContent(fileName, replaceContent);
		String fileContent = FileUtil.readUtf8String(file);
		String newFileContent = replaceContent(fileContent, replaceContent);

		String newFilePath = path + File.separator + newFileName;
		FileUtil.writeUtf8String(newFileContent,newFilePath);
		System.out.println("文件生成，" +  newFilePath);
	}

	/**
	 * 字符串替换
	 * @param rawText
	 * @param replaceContent
	 * @return
	 */
	private static String replaceContent(String rawText,Map<String,String> replaceContent){
		String tempText = rawText;
		replaceContent.forEach((key,value)->{
		});
		for (String key : replaceContent.keySet()) {
			tempText = tempText.replace(key,replaceContent.get(key));

		}
		return tempText;
	}
}
