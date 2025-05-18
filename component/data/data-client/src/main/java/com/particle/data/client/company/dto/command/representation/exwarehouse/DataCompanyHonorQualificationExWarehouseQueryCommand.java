package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业荣誉资质 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyHonorQualificationExWarehouseQueryCommand extends AbstractBasePageQueryCommand {

    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "证书编号")
    private String certificateNo;
    public static DataCompanyHonorQualificationExWarehouseQueryCommand create(Long companyId,String certificateNo) {
        DataCompanyHonorQualificationExWarehouseQueryCommand command = new DataCompanyHonorQualificationExWarehouseQueryCommand();
        command.companyId = companyId;
        command.certificateNo = certificateNo;
        return command;
    }
}
