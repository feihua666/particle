package com.particle.global.tool.template.templatetreerenderengine.render;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 模板渲染上下文数据
 * </p>
 *
 * @author yangwei
 * @since 2023-02-10 16:43
 */
@Data
public class TemplateRenderContext {

	public TemplateRenderContext(){
		this.shareDataAction = new ShareDataAction(this.shareData);
	}

	/**
	 * 模板渲染条件
	 */
	private TemplateRenderCondition templateRenderCondition;

	/**
	 * 渲染的模板和结果
	 */
	private SegmentTemplateData segmentTemplateData;

	/**
	 * 渲染的数据
	 */
	private Map<String, Object> renderData = new HashMap<>();

	/**
	 * 共享数据
	 * 共享数据仅用来渲染模板内容，主要解决在子模板使用了某些变量，需要放到全局的场景
	 * 如：在生成 java 代码时，全局的有import语句，但import哪个类是由子模板内容决定的，这就需要在渲染完子模板后才能确定，但可以在渲染子模板时放到共享变量，在渲染父模板时可以直接渲染出来。
	 */
	private Map<String, Set<String>> shareData = new HashMap<>();

	/**
	 * 共享数据操作实例
	 */
	private ShareDataAction shareDataAction;
	/**
	 * 上一级渲染上下文
	 */
	private TemplateRenderContext parentTemplateRenderContext;


	/**
	 * 防止共享数据变化，导致操作失效
	 * @param shareData
	 */
	public void setShareData(Map<String, Set<String>> shareData) {
		this.shareData = shareData;
		this.shareDataAction = new ShareDataAction(this.shareData);
	}


	/**
	 * 共享数据转 map
	 * @return
	 */
	public Map<String,Object> shareToMap(){
		if (segmentTemplateData == null) {
			throw new RuntimeException("segmentTemplateData must be set first");
		}
		Set<String> shareVariables = segmentTemplateData.getSegmentTemplate().getShareVariables();
		if (CollectionUtil.isNotEmpty(shareVariables)) {
			for (String shareVariable : shareVariables) {
				if (shareData.get(shareVariable) == null) {
					shareData.put(shareVariable, new HashSet<>());
				}
			}
		}

		if (shareData.isEmpty()) {
			return null;
		}

		Map<String,Object> result = new HashMap<>();
		result.put("share", getShareData());
		result.put("shareAct", getShareDataAction());
		return result;
	}

	/**
	 * 运行时，动态添加共享变量
	 * @param shareVariableKeys
	 * @return
	 */
	public Map<String,Object> addShareVariables(String ...shareVariableKeys){
		if (segmentTemplateData == null) {
			throw new RuntimeException("segmentTemplateData must be set first");
		}
		segmentTemplateData.getSegmentTemplate().addShareVariables(shareVariableKeys);
		return shareToMap();
	}

	/**
	 * 共享数据
	 */
	@Data
	public static class ShareDataAction{
		private Map<String, Set<String>> shareData;

		public ShareDataAction(Map<String, Set<String>> shareData){
			this.shareData = shareData;
		}

		/**
		 * 添加
		 * @param key
		 * @param var
		 */
		public void add(String key, String var){
			Set<String> strings = shareData.get(key);
			if (strings == null) {
				strings = new HashSet<>();
				shareData.put(key, strings);
			}
			strings.add(var);
		}
	}

}
