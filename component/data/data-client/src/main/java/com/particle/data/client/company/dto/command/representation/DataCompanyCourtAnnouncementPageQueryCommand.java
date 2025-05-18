package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业法院公告 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Data
@Schema
public class DataCompanyCourtAnnouncementPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "公告号")
    private String announcementNo;


    @Schema(description = "公告类型")
    private String announcementType;


    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "案由")
    private String caseReason;


    @Schema(description = "法院公司id")
    private Long courtCompanyId;


    @Schema(description = "法院名称")
    private String courtName;


    @Schema(description = "刊登板面页码")
    private String publishPage;


    @Schema(description = "刊登板面日期")
    private LocalDate publishPageDate;
    

    @Schema(description = "发布日期")
    private LocalDate publishDate;

	@Schema(description = "数据md5,announcement_no + announcement_type + case_no + case_reason + publish_date")
	private String dataMd5;
    

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}