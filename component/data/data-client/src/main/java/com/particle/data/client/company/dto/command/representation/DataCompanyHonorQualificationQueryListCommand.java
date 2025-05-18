package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业荣誉资质 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Data
@Schema
public class DataCompanyHonorQualificationQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "等级")
    private String level;


    @Schema(description = "发布日期")
    private LocalDate publishDate;
    

    @Schema(description = "发布日期")
    private LocalDate startDate;
    

    @Schema(description = "发布日期")
    private LocalDate endDate;
    

    @Schema(description = "证书编号")
    private String certificateNo;


    @Schema(description = "发布单位")
    private String publishOffice;


    @Schema(description = "发布标题")
    private String publishTitle;

	@Schema(description = "数据md5,name + level + certificate_no + publish_office + publish_title")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}