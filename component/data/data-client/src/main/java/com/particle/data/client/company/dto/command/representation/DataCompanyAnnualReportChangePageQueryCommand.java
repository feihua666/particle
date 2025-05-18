package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业年报变更 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Data
@Schema
public class DataCompanyAnnualReportChangePageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "企业年报表ID")
    private Long companyAnnualReportId;


    @Schema(description = "年报年度")
    private Integer year;


    @Schema(description = "序号")
    private Integer serialNumber;


    @Schema(description = "变更事项")
    private Long changeItemDictId;


    @Schema(description = "变更事项")
    private String changeItemName;


    @Schema(description = "变更前内容")
    private String contentBefore;


    @Schema(description = "变更后内容")
    private String contentAfter;


    @Schema(description = "变更日期")
    private LocalDate changeDate;

	@Schema(description = "数据md5,change_item_name + content_before + content_after + change_date")
	private String dataMd5;
    

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}
