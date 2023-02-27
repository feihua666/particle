package com.particle.lowcode.infrastructure.generator.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.global.dto.basic.Value;
import lombok.Data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * <p>
 * 低代码片段模板渲染参数
 * </p>
 *
 * @author yangwei
 * @since 2023-02-14 15:31
 */
@Data
public class LowcodeSegmentTemplateRenderParam extends Value {

	/**
	 * 全局变量数据
	 */
	private Map<String,Object> global;
	/**
	 * 扩展变量数据
	 */
	private Map<String,Object> ext;
	/**
	 * 片段模板id
	 */
	private Long rootSegmentTemplateId;

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


	public LowcodeSegmentTemplateRenderParam javaPackageHandle() {
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
			return this;
		}
		LowcodeSegmentTemplateRenderParam lowcodeSegmentTemplateRenderParam = create(this.global, this.ext, rootSegmentTemplateId, outputFileParentAbsoluteDir,javaPackageKeys);
		Optional.ofNullable(lowcodeSegmentTemplateRenderParam.getGlobal()).ifPresent(g -> g.putAll(globalTemp));
		Optional.ofNullable(lowcodeSegmentTemplateRenderParam.getExt()).ifPresent(g -> g.putAll(extTemp));

		return lowcodeSegmentTemplateRenderParam;
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


	/**
	 * 改变 片段模板id 值
	 * @param rootSegmentTemplateId
	 * @return
	 */
	public LowcodeSegmentTemplateRenderParam changeRootSegmentTemplateId(Long rootSegmentTemplateId) {
		return create(this.global, this.ext, rootSegmentTemplateId, outputFileParentAbsoluteDir,javaPackageKeys);
	}

	public LowcodeSegmentTemplateRenderParam create(Map<String, Object> global,
													Map<String, Object> ext,
													Long rootSegmentTemplateId,
													String outputFileParentAbsoluteDir,
													Set<String> javaPackageKeys) {
		LowcodeSegmentTemplateRenderParam lowcodeSegmentTemplateRenderParam = new LowcodeSegmentTemplateRenderParam();
		lowcodeSegmentTemplateRenderParam.setGlobal(global);
		lowcodeSegmentTemplateRenderParam.setExt(ext);
		lowcodeSegmentTemplateRenderParam.setRootSegmentTemplateId(rootSegmentTemplateId);
		lowcodeSegmentTemplateRenderParam.setOutputFileParentAbsoluteDir(outputFileParentAbsoluteDir);
		lowcodeSegmentTemplateRenderParam.setJavaPackageKeys(javaPackageKeys);

		return lowcodeSegmentTemplateRenderParam;
	}
}
