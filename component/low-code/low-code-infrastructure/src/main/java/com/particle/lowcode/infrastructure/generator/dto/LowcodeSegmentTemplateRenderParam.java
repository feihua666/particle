package com.particle.lowcode.infrastructure.generator.dto;

import com.particle.global.dto.basic.Value;
import lombok.Data;

import java.util.Map;

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
}
