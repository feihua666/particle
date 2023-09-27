package com.particle.report.domain.template;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 报告片段模板 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
public class ReportSegmentTemplateId extends Id {

	public ReportSegmentTemplateId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 报告片段模板 领域模型id
	 * @param id
	 * @return
	 */
	public static ReportSegmentTemplateId of(Long id){
		return new ReportSegmentTemplateId(id);
	}
}
