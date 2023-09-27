package com.particle.report.client.dto.data;

import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 报告生成响应对象
 * </p>
 *
 * @author yangwei
 * @since 2023-09-07 14:57
 */
@Data
@Schema
public class ReportApiGenerateVO extends VO {

	@Schema(description = "生成的报告地址")
	private String url;


	public static ReportApiGenerateVO create(String url) {
		ReportApiGenerateVO reportApiGenerateVO = new ReportApiGenerateVO();
		reportApiGenerateVO.setUrl(url);
		return reportApiGenerateVO;
	}
}
