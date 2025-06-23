package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业送达公告 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Data
@Schema
public class DataCompanyDeliveryAnnouncementQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "案号")
    private String caseNo;


    @Schema(description = "案由")
    private String caseReason;


    @Schema(description = "公告标题")
    private String title;


    @Schema(description = "法院名称")
    private String courtName;


    @Schema(description = "发布日期")
    private LocalDate publishDate;

	@Schema(description = "数据md5,case_no + case_reason + title + court_name + publish_date")
	private String dataMd5;
    

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}