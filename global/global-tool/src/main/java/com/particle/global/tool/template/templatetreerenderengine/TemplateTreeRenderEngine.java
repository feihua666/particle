package com.particle.global.tool.template.templatetreerenderengine;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.extra.template.engine.enjoy.EnjoyEngine;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.str.FilePathTool;
import com.particle.global.tool.template.CustomEnjoyEngine;
import com.particle.global.tool.template.TemplateTool;
import com.particle.global.tool.template.templatetreerenderengine.config.ConfigData;
import com.particle.global.tool.template.templatetreerenderengine.render.RenderContext;
import com.particle.global.tool.template.templatetreerenderengine.render.SegmentTemplateData;
import com.particle.global.tool.template.templatetreerenderengine.render.TemplateRenderCondition;
import com.particle.global.tool.template.templatetreerenderengine.render.TemplateRenderContext;
import com.particle.global.tool.template.templatetreerenderengine.template.ISegmentTemplateRenderDataResolver;
import com.particle.global.tool.template.templatetreerenderengine.template.ResolveRenderDataParam;
import com.particle.global.tool.template.templatetreerenderengine.template.SegmentTemplate;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.script.Bindings;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 模板树渲染引擎
 * 支持模板带有子级渲染规则如下：
 * 1. 模板支持名称渲染和内容渲染
 * 2. 优先渲染模板名称，模板名称一般用户输出文件或目录，其输出变量可在直接子级（子一级中）中使用，需添加 parent 前缀，如：#(parent.outputVar.[outputVariable])
 * 3. 内容模板用来生成模板片段或文件内容，其可以使用直接子级中定义的内容输出变量，来聚合子集内容
 * 4. 在父级中可使用的子级输出变量为 child.outputVar.[输出自定义变量],在子级中可使用的父级输出变量为 parent.outputVar.[输出自定义变量]，parent.share.[共享自定义变量] ，parent.shareAct([共享自定义变量],[变量值])
 * 5. 可以模板中处理模板获取的数据 前缀：trd.[获取的数据]，通过 {@link SegmentTemplate#segmentTemplateRenderDataResolver} 获取的数据，模板自定义数据可以在该模板所有位置使用（包括计算模板、标题模板、内容模板）
 * 6. 如果输出为 .java 文件后缀文件，会输出一个 [名称输出变量名]+JavaPackage 变量，值为java包名，这是对java文件特有的支持
 * 7. 如果全局或扩展数据有java package值，建议指定{@link ConfigData#getJavaPackageKeys()} 以自动转换为 [key]Path的全局或扩展变量作为路径处理
 * 8. 渲染模板时，自动配置一个变量为 tempMap 的 map变量以做为临时map变量，这样可以被子级访问
 * 9. 更多的功能可使用 parent.context 来访问父级渲染上下文
 * </p>
 *
 * @author yangwei
 * @since 2023-02-10 16:53
 */
@Data
@Slf4j
public class TemplateTreeRenderEngine {

	/**
	 * 使用 enjoyTemplateEngine 来渲染字符串模板
	 */
	public static final TemplateEngine enjoyTemplateEngine = TemplateTool.defaultTemplateEngine;

	/**
	 * 渲染逻辑
	 * @param configData 要渲染的数据配置
	 * @param segmentTemplate 要渲染的模板配置
	 * @return
	 */
	public RenderContext renderWithRenderContext(ConfigData configData, SegmentTemplate segmentTemplate) {
		log.info("start render configData={},segmentTemplate={}", JsonTool.toJsonStr(configData),JsonTool.toJsonStr(segmentTemplate));
		long start = System.currentTimeMillis();
		// 渲染上下文
		RenderContext renderContext = new RenderContext();
		renderContext.setConfigData(configData);

		// 执行渲染
		String result =  doRender(segmentTemplate,null, renderContext);

		log.info("end render,duration={}ms",System.currentTimeMillis() - start);
		return renderContext;
	}

	/**
	 * 渲染返回根模板渲染内容
	 * @param configData
	 * @param segmentTemplate
	 * @return
	 */
	public String render(ConfigData configData, SegmentTemplate segmentTemplate) {
		RenderContext renderContext = renderWithRenderContext(configData, segmentTemplate);
		List<TemplateRenderContext> templateRenderContexts = renderContext.getTemplateRenderContexts();
		if (!templateRenderContexts.isEmpty()) {
			// 如果渲染上下文不为空，第一个即是根模板渲染内容
			TemplateRenderContext next = templateRenderContexts.iterator().next();
			return next.getSegmentTemplateData().getTemplateContentResult();
		}
		return null;
	}
	/**
	 * 渲染
	 * @param segmentTemplate 要渲染的模板
	 * @param renderContext 要渲染的数据上下文
	 * @param parentTemplateRenderContext 上一级渲染上下文 如果为根，可以为 null
	 * @return
	 */
	protected String doRender(SegmentTemplate segmentTemplate,TemplateRenderContext parentTemplateRenderContext,RenderContext renderContext) {

		TemplateRenderContext templateRenderContext = preDoRender(segmentTemplate, parentTemplateRenderContext, renderContext);

		// 判断渲染条件
		TemplateRenderCondition templateRenderCondition = templateRenderCondition(segmentTemplate, templateRenderContext);
		if (templateRenderCondition != null) {
			// 如果返回结果为忽略，后续逻辑不再执行
			if (templateRenderCondition == TemplateRenderCondition.ignore) {
				log.debug("templateRenderCondition is ignore.returned!");
				return null;
			}
		}

		/*************************** 渲染名称*********************************************************/
		doRenderTemplateNameContent(templateRenderContext,segmentTemplate, parentTemplateRenderContext, renderContext);
		/*************************** 渲染名称 结束*********************************************************/

		/*************************** 渲染子模板*********************************************************/

		// 渲染子模板,子模板可以使用 父级的名称变量
		List<SegmentTemplate> subSegmentTemplates = segmentTemplate.getSubSegmentTemplates();
		if (CollectionUtil.isNotEmpty(subSegmentTemplates)) {
			renderSubSegmentTemplate(subSegmentTemplates,templateRenderContext,renderContext);
		}
		/*************************** 渲染子模板 结束*********************************************************/


		/*************************** 渲染模板*********************************************************/

		String render = doRenderTemplateContent(templateRenderContext,segmentTemplate, parentTemplateRenderContext, renderContext);
		/*************************** 渲染模板 结束*********************************************************/


		return render;
	}


	/**
	 * 渲染之前处理，主是要组件渲染数据上下文
	 * @param segmentTemplate
	 * @param parentTemplateRenderContext
	 * @param renderContext
	 * @return
	 */
	@SneakyThrows
	protected TemplateRenderContext preDoRender(SegmentTemplate segmentTemplate, TemplateRenderContext parentTemplateRenderContext, RenderContext renderContext){

		// 渲染数据上下文
		TemplateRenderContext templateRenderContext = new TemplateRenderContext();
		// 父级
		templateRenderContext.setParentTemplateRenderContext(parentTemplateRenderContext);
		// 待渲染的数据,如果父级存在，将父级数据加入
		Map<String, Object> objectMap = renderContext.getConfigData().toMap();

		// 渲染条件预定义对象
		objectMap.put("renderCondition", TemplateRenderCondition.templateRenderConditionMap);

		Map<String, Object> parent = new HashMap<>();
		objectMap.put("parent", parent);

		String tempMap = "temp";
		objectMap.put(tempMap, new HashMap<>());

		parent.put("context", parentTemplateRenderContext);


		// 将父级输出变量可引用
		if (parentTemplateRenderContext != null) {
			parent.put("outputVar", parentTemplateRenderContext.getSegmentTemplateData().outputVariableMap());
			parent.put(tempMap, parentTemplateRenderContext.getRenderData().get(tempMap));
		}
		templateRenderContext.setRenderData(objectMap);

		// 片段模板与渲染数据
		SegmentTemplateData segmentTemplateData = new SegmentTemplateData();
		segmentTemplateData.setSegmentTemplate(segmentTemplate);
		templateRenderContext.setSegmentTemplateData(segmentTemplateData);


		// 共享数据输出
		Map<String, Object> shareMap = templateRenderContext.shareToMap();
		// 这里返回null代表没有设置共享变量，不再设置
		if (shareMap != null) {
			objectMap.putAll(shareMap);
		}
		if (parentTemplateRenderContext != null) {
			Map<String, Object> parentsShareData = parentShareDataMap(templateRenderContext);
			if (parentsShareData != null) {
				parent.putAll(parentsShareData);
			}
		}

		// 获取模板私有数据
		ISegmentTemplateRenderDataResolver segmentTemplateRenderDataResolver = segmentTemplate.getSegmentTemplateRenderDataResolver();
		if (segmentTemplateRenderDataResolver != null) {
			ResolveRenderDataParam resolveRenderDataParam = ResolveRenderDataParam.create(objectMap,segmentTemplate);
			Object resolveRenderData = segmentTemplateRenderDataResolver.resolveRenderData(resolveRenderDataParam);
			// trd 为TemplateRenderData的缩写
			objectMap.put("trd", resolveRenderData);
		}


		// 添加到全局上下文中
		renderContext.addTemplateRenderContext(templateRenderContext);

		return templateRenderContext;
	}

	/**
	 * 渲染结果条件
	 * @return
	 */
	@SneakyThrows
	protected TemplateRenderCondition templateRenderCondition(SegmentTemplate segmentTemplate, TemplateRenderContext templateRenderContext){
		String conditionGroovyScript = Optional.ofNullable(segmentTemplate)
				.map(item -> item.getExtConfig()).map(item -> item.getConditionGroovyScript()).orElse(null);
		if (StrUtil.isNotEmpty(conditionGroovyScript)) {
			Bindings bindings = GroovyTool.createBindings();
			Map<String, Object> renderData = Optional.ofNullable(templateRenderContext).map(item -> item.getRenderData()).orElse(null);
			if (CollectionUtil.isNotEmpty(renderData)) {
				bindings.putAll(renderData);
			}

			Object o = GroovyTool.compileAndEval(conditionGroovyScript, bindings, true);
			if (o instanceof TemplateRenderCondition) {
				return ((TemplateRenderCondition) o);
			}
		}
		return null;
	}
	/**
	 * 获取最近的父一级共享变量数据
	 * @param templateRenderContext
	 * @return
	 */
	protected Map<String,Object> parentShareDataMap(TemplateRenderContext templateRenderContext){
		TemplateRenderContext parentTemplateRenderContext = templateRenderContext.getParentTemplateRenderContext();
		if (parentTemplateRenderContext != null) {
			Map<String, Object> objectMap = parentTemplateRenderContext.shareToMap();
			if (objectMap != null) {
				return objectMap;
			}else {
				return parentShareDataMap(parentTemplateRenderContext);
			}
		}
		return null;
	}

	/**
	 * 渲染模板名称
	 * @param templateRenderContext
	 * @param segmentTemplate
	 * @param parentTemplateRenderContext
	 * @param renderContext
	 */
	protected void doRenderTemplateNameContent(TemplateRenderContext templateRenderContext,SegmentTemplate segmentTemplate,TemplateRenderContext parentTemplateRenderContext,RenderContext renderContext){

		SegmentTemplateData segmentTemplateData = templateRenderContext.getSegmentTemplateData();

		if (StrUtil.isNotBlank(segmentTemplate.getTemplateComputeContent())) {
			TemplateTool.render(segmentTemplate.getTemplateComputeContent(), templateRenderContext.getRenderData(), enjoyTemplateEngine);
		}

		// 先渲染名称
		String renderName = TemplateTool.render(segmentTemplate.getTemplateNameContent(), templateRenderContext.getRenderData(), enjoyTemplateEngine);
		segmentTemplateData.setTemplateNameContentResult(renderName);

		// 渲染完成后置处理
		postDoRenderTemplateNameContent(templateRenderContext, segmentTemplate, renderContext, renderName);
	}

	/**
	 * 模板名称渲染结果后置处理
	 * @param templateRenderContext
	 * @param segmentTemplate
	 * @param renderContext
	 * @param renderName 模板名称渲染结果
	 */
	protected void postDoRenderTemplateNameContent(TemplateRenderContext templateRenderContext,SegmentTemplate segmentTemplate,RenderContext renderContext,String renderName){
		SegmentTemplateData segmentTemplateData = templateRenderContext.getSegmentTemplateData();
		// 输出类型
		OutputType outputType = segmentTemplate.getOutputType();

		// 处理渲染结果文件句柄
		if (outputType != null) {
			// 渲染结果不为空
			if (StrUtil.isNotEmpty(renderName)) {
				boolean absolutePath = FileUtil.isAbsolutePath(renderName);
				String path = renderName;
				String outputFileParentAbsoluteDir = renderContext.getConfigData().getOutputFileParentAbsoluteDir();
				if (!absolutePath && StrUtil.isNotEmpty(outputFileParentAbsoluteDir)) {
					path = FilePathTool.concat(outputFileParentAbsoluteDir, renderName);
				}
				if (outputType == OutputType.DIR) {
					File mkdir = FileUtil.mkdir(path);
					log.info("mkdir, path={}",mkdir.getAbsolutePath());
					segmentTemplateData.setTemplateNameContentResultFile(mkdir);
				}else if (outputType == OutputType.FILE) {
					File file = FileUtil.newFile(path);
					log.info("new file, path={}",file.getAbsolutePath());
					segmentTemplateData.setTemplateNameContentResultFile(file);
				}
			}else {
				if (outputType == OutputType.DIR || outputType == OutputType.FILE){
					log.warn("templateName render result is empty! so can not output dir or file!!");
				}
			}

		}
	}

	/**
	 * 模板渲染
	 * @param templateRenderContext
	 * @param segmentTemplate
	 * @param parentTemplateRenderContext
	 * @param renderContext
	 * @return
	 */
	protected String doRenderTemplateContent(TemplateRenderContext templateRenderContext,SegmentTemplate segmentTemplate,TemplateRenderContext parentTemplateRenderContext,RenderContext renderContext){
		SegmentTemplateData segmentTemplateData = templateRenderContext.getSegmentTemplateData();

		// 模板渲染
		String render = TemplateTool.render(segmentTemplate.getTemplateContent(), templateRenderContext.getRenderData(), enjoyTemplateEngine);

		// 渲染结果保存
		segmentTemplateData.setTemplateContentResult(render);


		// 渲染完成后置处理
		postDoRenderTemplateContent(segmentTemplateData, segmentTemplate, parentTemplateRenderContext, render);

		return render;
	}

	/**
	 * 模板内容渲染完成后置处理
	 * @param segmentTemplateData
	 * @param segmentTemplate
	 * @param parentTemplateRenderContext
	 * @param render 模板渲染结果
	 */
	protected void postDoRenderTemplateContent(SegmentTemplateData segmentTemplateData,SegmentTemplate segmentTemplate,TemplateRenderContext parentTemplateRenderContext,String render){
		// 输出类型
		OutputType outputType = segmentTemplate.getOutputType();
		OutputFileHandleType outputFileHandleType = segmentTemplate.getOutputFileHandleType();

		String childKey = "child";
		String childOutputVarKey = "outputVar";
		// 将渲染完的结果变量给到上一级使用
		if (parentTemplateRenderContext != null) {
			Map child = (Map)parentTemplateRenderContext.getRenderData().get(childKey);
			if (child == null) {
				child = new HashMap<>();
				parentTemplateRenderContext.getRenderData().put(childKey, child);
			}
			Map childOutputVar = (Map)child.get(childOutputVarKey);
			if (childOutputVar == null) {
				childOutputVar = new HashMap<>();
				child.put(childOutputVarKey, childOutputVar);
			}
			((Map) childOutputVar).putAll(segmentTemplateData.outputVariableMap());
		}

		// 将数据写入文件
		if (outputType != null) {
			if (outputType == OutputType.FILE) {
				if (segmentTemplateData.getTemplateNameContentResultFile() != null) {

					if(OutputFileHandleType.OVERRIDE == outputFileHandleType){
						File file = FileUtil.writeUtf8String(render, segmentTemplateData.getTemplateNameContentResultFile());
						log.info("write file content completed, path={}",file.getAbsolutePath());
					}else if(OutputFileHandleType.APPEND == outputFileHandleType){
						File file = FileUtil.appendUtf8String(render, segmentTemplateData.getTemplateNameContentResultFile());
						log.info("append file content completed, path={}",file.getAbsolutePath());
					}else if(OutputFileHandleType.DELETE == outputFileHandleType){
						boolean del = FileUtil.del(segmentTemplateData.getTemplateNameContentResultFile());
						if (del) {
							log.info("delete file, path={}",segmentTemplateData.getTemplateNameContentResultFile().getAbsolutePath());
						}else {
							log.info("delete file failed, path={}",segmentTemplateData.getTemplateNameContentResultFile().getAbsolutePath());
						}
					}


				}

			}
		}
	}
	
	/**
	 * 渲染子模板
	 * @param subSegmentTemplates
	 * @param parentTemplateRenderContext
	 * @param renderContex
	 * @return 子模板不返回
	 */
	protected void renderSubSegmentTemplate(List<SegmentTemplate> subSegmentTemplates,TemplateRenderContext parentTemplateRenderContext,RenderContext renderContex) {
		for (SegmentTemplate subSegmentTemplate : subSegmentTemplates) {
			doRender(subSegmentTemplate, parentTemplateRenderContext, renderContex);
		}

	}
}
