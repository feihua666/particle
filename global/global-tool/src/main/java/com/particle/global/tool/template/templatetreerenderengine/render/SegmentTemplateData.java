package com.particle.global.tool.template.templatetreerenderengine.render;

import cn.hutool.core.util.StrUtil;
import com.particle.global.tool.template.templatetreerenderengine.OutputType;
import com.particle.global.tool.template.templatetreerenderengine.template.SegmentTemplate;
import lombok.Data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 运行时片段模板数据
 * </p>
 *
 * @author yangwei
 * @since 2023-02-10 16:46
 */
@Data
public class SegmentTemplateData {

	/**
	 * 片段模板
	 */
	private SegmentTemplate segmentTemplate;

	/**
	 * 名称渲染结果文本
	 */
	private String templateNameContentResult;

	/**
	 * 渲染结果文本
	 * 也可能是一个渲染结果句柄，例如渲染的是一个文件或目录，该结果应该是持有渲染生成的文件或目录路径表示，具体是相对路径还是绝对路径应根据模板内容决定
	 */
	private String templateContentResult;

	/**
	 * 名称渲染结果文件句柄
	 */
	private File templateNameContentResultFile;

	/**
	 * 输出结果
	 * @return
	 */
	public Map<String,Object> outputVariableMap(){
		Map<String, Object> result = new HashMap<>();

		if (segmentTemplate != null) {
			if (StrUtil.isNotEmpty(segmentTemplate.getOutputNameVariableName())) {
				result.put(segmentTemplate.getOutputNameVariableName(), templateNameContentResult);
				String javaExtensionSuffix = ".java";
				//	java package 处理
				if (segmentTemplate.getOutputType() == OutputType.FILE && StrUtil.isNotEmpty(templateNameContentResult) && templateNameContentResult.endsWith(javaExtensionSuffix)) {
					String substring = templateNameContentResult.substring(0, templateNameContentResult.lastIndexOf(File.separator));
					result.put(segmentTemplate.getOutputNameVariableName() + "JavaPackage", substring.replace(File.separator, "."));
				}
			}
			if (StrUtil.isNotEmpty(segmentTemplate.getOutputVariableName())) {
				result.put(segmentTemplate.getOutputVariableName(), templateContentResult);
			}
		}
		return result;
	}

}
