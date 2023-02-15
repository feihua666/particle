package com.particle.global.tool.template;

import com.particle.global.tool.template.templatetreerenderengine.OutputType;
import com.particle.global.tool.template.templatetreerenderengine.TemplateTreeRenderEngine;
import com.particle.global.tool.template.templatetreerenderengine.config.ConfigData;
import com.particle.global.tool.template.templatetreerenderengine.template.SegmentTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 模板树渲染引擎测试类
 * </p>
 *
 * @author yangwei
 * @since 2023-02-13 11:29
 */
public class TemplateTreeRenderEngineTest {
	public static void main(String[] args) {
		templateTreeRenderEngineFileTest();
		templateTreeRenderEngineDirTest();
		templateTreeRenderEngineTextTest();
	}
	public static void templateTreeRenderEngineFileTest(){
		TemplateTreeRenderEngine templateTreeRenderEngine = new TemplateTreeRenderEngine();
		ConfigData configData = configData();
		SegmentTemplate segmentTemplate = segmentTemplate();
		segmentTemplate.setTemplateNameContent("filefile.java");
		segmentTemplate.setOutputType(OutputType.FILE);
		String render = templateTreeRenderEngine.render(configData, segmentTemplate);
		System.out.println(render);
	}
	public static void templateTreeRenderEngineDirTest(){
		TemplateTreeRenderEngine templateTreeRenderEngine = new TemplateTreeRenderEngine();
		ConfigData configData = configData();
		SegmentTemplate segmentTemplate = segmentTemplate();
		segmentTemplate.setTemplateNameContent("dirdir");
		segmentTemplate.setOutputType(OutputType.DIR);
		String render = templateTreeRenderEngine.render(configData, segmentTemplate);
		System.out.println(render);
	}
	public static void templateTreeRenderEngineTextTest(){
		TemplateTreeRenderEngine templateTreeRenderEngine = new TemplateTreeRenderEngine();
		ConfigData configData = configData();
		SegmentTemplate segmentTemplate = segmentTemplate();
		String render = templateTreeRenderEngine.render(configData, segmentTemplate);
		System.out.println(render);
	}

	/**
	 * 配置数据
	 * @return
	 */
	public static ConfigData configData(){
		ConfigData configData = ConfigData.create();

		Map<String, Object> global = new HashMap<>();
		global.put("author", "yw");

		configData.setGlobal(global);

		configData.setOutputFileParentAbsoluteDir("/Users/yw/temp");
		return configData;
	}

	/**
	 * 模板
	 * @return
	 */
	public static SegmentTemplate segmentTemplate(){
		SegmentTemplate segmentTemplate = new SegmentTemplate();

		segmentTemplate.addShareVariables("shareTest");
		StringBuffer segmentTemplateContent = new StringBuffer();
		segmentTemplateContent.append("作者：#(global.author)" + "\n");
		// 子级变量
		segmentTemplateContent.append("child：#(child.sub1)" + "\n");
		segmentTemplateContent.append("share：#(share.shareTest)" + "\n");


		List<SegmentTemplate> subSegmentTemplates = subSegmentTemplates("sub", 10);
		segmentTemplate.setSubSegmentTemplates(subSegmentTemplates);

		segmentTemplate.setTemplateContent(segmentTemplateContent.toString());


		return segmentTemplate;
	}

	public static List<SegmentTemplate> subSegmentTemplates(String prefix,int max){
		List<SegmentTemplate> result = new ArrayList<>();

		for (int i = 0; i < max; i++) {

			String prefixI = prefix + "" + i;
			SegmentTemplate segmentTemplate = new SegmentTemplate();

			StringBuffer segmentTemplateContent = new StringBuffer();
			segmentTemplateContent.append( prefixI + "作者：#(global.author)" + "\n");
			segmentTemplateContent.append(  "sharesub：#(parents.share.shareTest.add('"+prefixI+"'))" + "\n");
			//segmentTemplateContent.append(  "sharesub1：#(parents.shareAct.add('shareTest','"+prefixI+"'))" + "\n");

			segmentTemplate.setTemplateContent(segmentTemplateContent.toString());

			segmentTemplate.setOutputVariableName(prefixI);
			result.add(segmentTemplate);
		}
		return result;
	}
}
