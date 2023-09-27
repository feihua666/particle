package com.particle.global.tool.template.templatetreerenderengine.template;

import cn.hutool.core.lang.UUID;
import com.particle.global.tool.template.templatetreerenderengine.OutputFileHandleType;
import com.particle.global.tool.template.templatetreerenderengine.OutputType;
import com.particle.global.tool.template.templatetreerenderengine.TemplateTreeRenderEngine;
import com.particle.global.tool.template.templatetreerenderengine.config.ConfigData;
import com.particle.global.tool.template.templatetreerenderengine.template.impl.GroovyScriptSegmentTemplateRenderDataResolverImpl;
import lombok.Data;

import java.util.*;

/**
 * <p>
 * 片段模板
 * 供{@link TemplateTreeRenderEngine#render(ConfigData, SegmentTemplate)} 方法渲染使用
 * </p>
 *
 * @author yangwei
 * @since 2023-02-10 16:06
 */
@Data
public class SegmentTemplate {

	public SegmentTemplate(){
		this.uniqueId = UUID.fastUUID().toString(true);
	}

	public SegmentTemplate(String uniqueId){
		this.uniqueId = uniqueId;
	}
	/**
	 * 唯一id
	 */
	private String uniqueId;

	/**
	 * 计算模板内容，不参与输出，只负责处理一部分逻辑
	 */
	private String templateComputeContent;

	/**
	 * 模板名称模板，可用于输出文件或目录
	 */
	private String templateNameContent;

	/**
	 * 模板名称输出变量名
	 * 变量名应该符合java变量命名规范
	 * 优先渲染模板名称内容并输出变量，子一级模板渲染时可直接使用，在使用时需添加 parent 前缀。
	 */
	private String outputNameVariableName;

	/**
	 * 模板内容
	 * 模板内容在用来渲染的真实文本内容
	 */
	private String templateContent;

	/**
	 * 内容输出变量名
	 * 变量名应该符合java变量命名规范
	 * 主要用来将根据模板内容渲染的结果保存在该变量中以供后续模板使用
	 * 输出变量可以被父级引用，使用时需要添加 child 前缀
	 */
	private String outputVariableName;

	/**
	 * 输出类型
	 */
	private OutputType outputType;

	/**
	 * 输出类型为文件时，文件的处理方式
	 */
	private OutputFileHandleType outputFileHandleType = OutputFileHandleType.OVERRIDE;

	/**
	 * 共享内容变量
	 * 共享变量旨在 {@link SegmentTemplate#subSegmentTemplates} 子级中可以直接访问并追加数据
	 * 主要应用场景是：比如要生成一个 java 类，其中有import语句，但该语句在依赖其定义的方法或属性来决定的，只有知道了其属性是如日期类型才会import一个java.util.Date
	 */
	private Set<String> shareVariables;
	/**
	 * 子级
	 */
	private List<SegmentTemplate> subSegmentTemplates;

	/**
	 * 字段数据配置，预留
	 * 可以用来设置获取数据的配置，比如请求接口参数等
	 */
	private ExtConfig extConfig;

	/**
	 * 模板渲染数据获取
	 */
	public ISegmentTemplateRenderDataResolver segmentTemplateRenderDataResolver;

	/**
	 * 添加共享变量
	 * @param shareVariableKeys
	 * @return
	 */
	public SegmentTemplate addShareVariables(String ...shareVariableKeys){
		if (shareVariables == null) {
			shareVariables = new HashSet<>();
		}
		for (String shareVariableKey : shareVariableKeys) {
			shareVariables.add(shareVariableKey);
		}
		return this;
	}

	/**
	 * 添加子模板片段
	 * @param segmentTemplate
	 */
	public void addSubSegmentTemplate(SegmentTemplate segmentTemplate){
		if (subSegmentTemplates == null) {
			subSegmentTemplates = new ArrayList<>();
		}
		subSegmentTemplates.add(segmentTemplate);
	}

	@Data
	public static class ExtConfig{
		/**
		 * groovy 脚本,主要用来获取数据，添加该项配置主要是配合 {@link GroovyScriptSegmentTemplateRenderDataResolverImpl} 实现脚本获取
		 */
		private String dataGroovyScript;
		/**
		 * 条件脚本，主要是用来条件判断，如果返回一个特定的值，则对特定的值做特殊的处理，当前主要是用来判断是否需要处理模板及子模板
		 */
		private String conditionGroovyScript;


		public static ExtConfig create(String dataGroovyScript,String conditionGroovyScript) {
			ExtConfig extConfig = new ExtConfig();
			extConfig.dataGroovyScript = dataGroovyScript;
			extConfig.conditionGroovyScript = conditionGroovyScript;
			return extConfig;
		}
	}
}
