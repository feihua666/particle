package com.particle.report.infrastructure.template.dto;

import com.particle.global.dto.basic.Value;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 报告片段模板渲染参数
 * </p>
 *
 * @author yangwei
 * @since 2023-09-07 14:48:06
 */
@Data
public class ReportSegmentTemplateRenderParam extends Value {

	/**
	 * 全局变量数据
	 */
	private Map<String,Object> global;
	/**
	 * 扩展变量数据
	 */
	private Map<String,Object> ext;
	/**
	 * 全局临时数据
	 */
	private Map<String,Object> globalTemp;
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
	 * 改变 片段模板id 值
	 * @param rootSegmentTemplateId
	 * @return
	 */
	public ReportSegmentTemplateRenderParam changeRootSegmentTemplateId(Long rootSegmentTemplateId) {
		return create(this.global, this.ext, this.globalTemp, rootSegmentTemplateId, this.outputFileParentAbsoluteDir);
	}

	public static ReportSegmentTemplateRenderParam create(Map<String, Object> global,
												   Map<String, Object> ext,
														  Map<String,Object> globalTemp,
												   Long rootSegmentTemplateId,
												   String outputFileParentAbsoluteDir) {
		ReportSegmentTemplateRenderParam reportSegmentTemplateRenderParam = new ReportSegmentTemplateRenderParam();
		reportSegmentTemplateRenderParam.setGlobal(global);
		reportSegmentTemplateRenderParam.setExt(ext);
		reportSegmentTemplateRenderParam.setGlobalTemp(globalTemp);
		reportSegmentTemplateRenderParam.setRootSegmentTemplateId(rootSegmentTemplateId);
		reportSegmentTemplateRenderParam.setOutputFileParentAbsoluteDir(outputFileParentAbsoluteDir);

		return reportSegmentTemplateRenderParam;
	}
}
