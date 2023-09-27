package com.particle.report.domain.reportapi;

import com.particle.common.domain.id.Id;

/**
 * <p>
 * 报告接口 领域模型id
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
public class ReportReportApiId extends Id {

	public ReportReportApiId(Long id) {
		super(id);
	}

	/**
	 * 基本数据类型 id 转 报告接口 领域模型id
	 * @param id
	 * @return
	 */
	public static ReportReportApiId of(Long id){
		return new ReportReportApiId(id);
	}
}
