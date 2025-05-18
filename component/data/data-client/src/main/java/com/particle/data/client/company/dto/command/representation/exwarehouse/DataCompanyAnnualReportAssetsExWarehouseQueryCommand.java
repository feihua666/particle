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
 * 企业资产状况信息 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@PropValid
@Data
@Schema
public class DataCompanyAnnualReportAssetsExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @PropValid.DependCondition(message = "企业ID 和 企业年报ID 至少填写一个",dependProp = "companyAnnualReportId",ifEqual = "null")
    @Schema(description = "企业ID")
    private Long companyId;

    @PropValid.DependCondition(message = "企业ID 和 企业年报ID 至少填写一个",dependProp = "companyId",ifEqual = "null")
    @Schema(description = "企业年报ID")
    private Long companyAnnualReportId;

    @Schema(description = "年报年度")
    private Integer year;

    public static DataCompanyAnnualReportAssetsExWarehouseQueryCommand create(Long companyId,Long companyAnnualReportId) {
        DataCompanyAnnualReportAssetsExWarehouseQueryCommand command = new DataCompanyAnnualReportAssetsExWarehouseQueryCommand();
        command.companyId = companyId;
        command.companyAnnualReportId = companyAnnualReportId;
        return command;
    }
}
