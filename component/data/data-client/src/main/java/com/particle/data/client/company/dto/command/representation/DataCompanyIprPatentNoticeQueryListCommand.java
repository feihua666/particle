package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利通知书信息 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Data
@Schema
public class DataCompanyIprPatentNoticeQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;


    @Schema(description = "通知发文日期")
    private LocalDate publicDate;
    

    @Schema(description = "通知挂号")
    private String mailNo;


    @Schema(description = "通知收件人姓名")
    private String receiverName;


    @Schema(description = "通知书类型")
    private String noticeType;


    @Schema(description = "通知书类型说明")
    private String noticeTypeDescription;

	@Schema(description = "数据md5,public_date + mail_no + receiver_name + notice_type + notice_type_description")
	private String dataMd5;


    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}