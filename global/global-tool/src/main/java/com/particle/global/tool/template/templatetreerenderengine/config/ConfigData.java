package com.particle.global.tool.template.templatetreerenderengine.config;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
	 * 全局临时数据
	 */
	private Map<String,Object> globalTemp = new HashMap<>();

	/**
	 * 输出文件的父目录路径,尽量不要以文件分隔符结尾
	 * 如：/user/yw/test
	 */
	private String outputFileParentAbsoluteDir;

	/**
	 * java 包键，这会自动将对应的包键添加对应的路径键，包括 global 和 ext
	 * 如：global.parentPackage=com.particle，那么在渲染时会添加一个key=global.parentPackagePath=com/particle
	 */
	private Set<String> javaPackageKeys;

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
		javaPackageHandle();
		Map<String,Object> result = new HashMap<>();
		result.put("global", getGlobal());
		result.put("sys", getSys());
		result.put("ext", getExt());
		result.put("globalTemp", getGlobalTemp());
		result.put("outputFileParentAbsoluteDir", getOutputFileParentAbsoluteDir());

		return result;
	}


	public void javaPackageHandle() {
		Map<String, Object> globalTemp = new HashMap<>();
		Map<String, Object> extTemp = new HashMap<>();
		if (CollectionUtil.isNotEmpty(javaPackageKeys)) {
			for (String javaPackageKey : javaPackageKeys) {

				Map<String, Object> globalPathMap = javaPackageHandle(javaPackageKey, global);
				if (globalPathMap != null) {
					globalTemp.putAll(globalPathMap);
				}
				Map<String, Object> extPathMap = javaPackageHandle(javaPackageKey, ext);
				if (extPathMap != null) {
					extTemp.putAll(extPathMap);
				}
			}
		}

		if (globalTemp.isEmpty() && extTemp.isEmpty()) {
			return;
		}

		Optional.ofNullable(getGlobal()).ifPresent(g -> g.putAll(globalTemp));
		Optional.ofNullable(getExt()).ifPresent(g -> g.putAll(extTemp));

	}

	private Map<String, Object> javaPackageHandle(String javaPackageKey, Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		if (!map.containsKey(javaPackageKey)) {
			return null;
		}
		Object o = map.get(javaPackageKey);
		if (o == null) {
			return null;
		}
		Map<String, Object> temp = new HashMap<>();

		temp.put(javaPackageKey + "Path", o.toString().replace(".", File.separator));
		return temp;
	}
}
