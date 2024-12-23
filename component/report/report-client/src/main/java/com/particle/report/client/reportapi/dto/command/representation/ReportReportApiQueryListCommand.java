package com.particle.report.client.reportapi.dto.command.representation;

import com.particle.common.client.dto.command.tree.AbstractBaseTreeQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 报告接口 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Data
@Schema
public class ReportReportApiQueryListCommand extends AbstractBaseTreeQueryCommand {

    @Like
    @Schema(description = "编码,左前缀匹配")
    private String code;

    @Like
    @Schema(description = "名称,左前缀匹配")
    private String name;


    @Schema(description = "是否为组")
    private Boolean isGroup;


    @Like
    @Schema(description = "接口地址,左前缀匹配")
    private String url;


    @Schema(description = "报告片段模板id")
    private Long reportSegmentTemplateId;



}
