package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业严重违法 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Data
@Schema
public class DataCompanySeriousIllegalQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;

	@Schema(description = "企业名称")
	private String companyName;

	@Schema(description = "列入决定书文号")
	private String putNo;

    @Schema(description = "列入原因")
    private String putReason;


    @Schema(description = "列入日期")
    private LocalDate putDate;


    @Schema(description = "作出列入决定机关公司id")
    private Long putInstituteCompanyId;


    @Schema(description = "作出列入决定机关名称")
    private String putInstituteName;

	@Schema(description = "移出决定书文号")
	private String removeNo;


    @Schema(description = "移除原因")
    private String removeReason;


    @Schema(description = "移除日期")
    private LocalDate removeDate;


    @Schema(description = "作出移除决定机关公司id")
    private Long removeInstituteCompanyId;


    @Schema(description = "作出移除决定机关名称")
    private String removeInstituteName;

	@Schema(description = "数据md5,type + put_reason + put_date")
	private String dataMd5;

	@Schema(description = "最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动")
	private LocalDateTime latestHandleAt;









}
