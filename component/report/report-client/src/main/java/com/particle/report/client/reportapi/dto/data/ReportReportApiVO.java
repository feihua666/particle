package com.particle.report.client.reportapi.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 报告接口 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Data
@Schema
public class ReportReportApiVO extends AbstractBaseIdTreeVO {

    @Schema(description = "编码")
    private String code;
    
    @Schema(description = "名称")
    private String name;
    
    @Schema(description = "是否为组")
    private Boolean isGroup;
    
    @Schema(description = "接口权限码")
    private String permissions;
    
    @Schema(description = "接口地址")
    private String url;
    
    @Schema(description = "报告片段模板id")
    private Long reportSegmentTemplateId;
    
    @Schema(description = "入参示例")
    private String inParamExampleConfigJson;

	@Schema(description = "后置处理脚本，可以用来对渲染的结果进一步处理，如转为pdf，目前仅支持groovy脚本")
	private String postScript;
    
    @Schema(description = "描述")
    private String remark;

    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_report_report_api, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}