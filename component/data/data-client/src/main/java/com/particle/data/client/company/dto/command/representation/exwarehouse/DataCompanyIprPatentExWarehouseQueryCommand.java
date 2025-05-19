package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import com.particle.global.validation.props.PropValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业知识产权专利 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyIprPatentExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @Schema(description = "企业ID")
    private Long companyId;

    @Schema(description = "是否申请人")
    private Boolean isApplicant;

    @Schema(description = "是否发明人")
    private Boolean isInvent;

    @Schema(description = "是否代理人")
    private Boolean isAgent;

    @Schema(description = "是否代理机构")
    private Boolean isAgency;

    @Schema(description = "是否审查员")
    private Boolean isExaminer;

    @Schema(description = "是否权利人")
    private Boolean isRight;

    @Schema(description = "是否当前权利人")
    private Boolean isCurrentRight;

    @Schema(description = "原始申请号")
    private String applyNo;

    @Schema(description = "原始公布号")
    private String publicNo;

    public static DataCompanyIprPatentExWarehouseQueryCommand create(Long companyId,
                                                                     Boolean isApplicant,
                                                                     Boolean isInvent,
                                                                     Boolean isAgent,
                                                                     Boolean isAgency,
                                                                     Boolean isExaminer,
                                                                     Boolean isRight,
                                                                     Boolean isCurrentRight,
                                                                     String applyNo,
                                                                     String publicNo) {
        DataCompanyIprPatentExWarehouseQueryCommand command = new DataCompanyIprPatentExWarehouseQueryCommand();
        command.companyId = companyId;
        command.isApplicant = isApplicant;
        command.isInvent = isInvent;
        command.isAgent = isAgent;
        command.isAgency = isAgency;
        command.isExaminer = isExaminer;
        command.isRight = isRight;
        command.isCurrentRight = isCurrentRight;
        command.applyNo = applyNo;
        command.publicNo = publicNo;
        return command;
    }
}
