package com.particle.data.client.company.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利法律状态 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Data
@Schema
public class DataCompanyIprPatentLegalStatusPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业知识产权专利表id")
    private Long companyIprPatentId;


    @Schema(description = "法律状态")
    private Long legalStatusDictId;

	@Schema(description = "法律状态代码")
	private String legalStatusCode;

	@Schema(description = "原始法律状态名称")
	private String legalStatusName;

	@Schema(description = "英文法律状态名称")
	private String legalStatusNameEn;

	@Schema(description = "中文法律状态名称")
	private String legalStatusNameCn;


    @Schema(description = "原始法律状态详情")
    private String legalStatusDetail;

	@Schema(description = "英文法律状态详情")
	private String legalStatusDetailEn;

	@Schema(description = "中文法律状态详情")
	private String legalStatusDetailCn;


    @Schema(description = "法律状态日期")
    private LocalDate legalStatusDate;

	@Schema(description = "数据md5,legal_status_name + legal_status_detail + legal_status_date")
	private String dataMd5;
    

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}