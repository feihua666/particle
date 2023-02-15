package com.particle.lowcode.domain.generator.enums;

import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 代码片段模板输出类型
 * 该枚举应该与 {@link com.particle.global.tool.template.templatetreerenderengine.OutputType} 保持一致
 * </p>
 *
 * @author yangwei
 * @since 2023-01-06
 */
public enum OutputType implements IDictItem {
	/**
	 * 输出文件
	 */
	FILE,
	/**
	 * 输出目录
	 */
	DIR,
	/**
	 * 输出片段字符串
	 */
	SEGMENT,
	/**
	 * 仅分组，仅分组不渲染
	 */
	GROUPONLY;

	@Override
	public String itemValue() {
		return this.name();
	}

	@Override
	public String groupCode() {
		return Group.lowcode_segment_template_output_type.groupCode();
	}

	/**
	 * 代码片段模板输出类型字典组
	 */
	public enum Group implements IDictGroup {
		/**
		 * 字典组编码
		 */
		lowcode_segment_template_output_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}
}
