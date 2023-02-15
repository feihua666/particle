package com.particle.global.tool.template.templatetreerenderengine.config;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 配置数据，作为模板渲染的入口数据
 * </p>
 *
 * @author yangwei
 * @since 2023-02-10 16:26
 */
@Data
public class ConfigData {


	public ConfigData(){

	}

	/**
	 * 全局数据
	 */
	private Map<String,Object> global = new HashMap<>();
	/**
	 * 扩展数据
	 */
	private Map<String,Object> ext = new HashMap<>();
	/**
	 * 系统内置数据
	 */
	private SystemData sys = new SystemData();

	/**
	 * 输出文件的父目录路径,尽量不要以文件分隔符结尾
	 * 如：/user/yw/test
	 */
	private String outputFileParentAbsoluteDir;

	/**
	 * 创建一个配置对象
	 * @return
	 */
	public static ConfigData create(){
		return new ConfigData();
	}

	/**
	 * 根据全局配置数据创建一个配置对象
	 * @param global
	 * @return
	 */
	public static ConfigData create(Map<String, Object> global) {
		ConfigData configData = create();
		configData.setGlobal(global);
		return configData;
	}

	/**
	 * 转为map
	 * @return
	 */
	public Map<String,Object> toMap(){
		Map<String,Object> result = new HashMap<>();
		result.put("global", getGlobal());
		result.put("sys", getSys());
		result.put("ext", getExt());
		result.put("outputFileParentAbsoluteDir", getOutputFileParentAbsoluteDir());

		return result;
	}
}
