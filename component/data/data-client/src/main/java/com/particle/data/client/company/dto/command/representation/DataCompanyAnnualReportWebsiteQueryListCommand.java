package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业年报网站网店 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Data
@Schema
public class DataCompanyAnnualReportWebsiteQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;


    @Schema(description = "年报年度")
    private Integer year;


    @Schema(description = "类型")
    private Long typeDictId;

	@Schema(description = "类型名称，如：网站、网店")
	private String typeName;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "网址")
    private String url;

	@Schema(description = "数据md5,type_name + name + url")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}