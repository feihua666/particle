package com.particle.data.client.company.dto.command.warehouse;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductCompetitiveProductRelExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业融资产品竞品关系入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyVcProductCompetitiveProductRelWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业融资产品ID 不能为空")
    @Schema(description = "企业融资产品ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcProductId;


    @NotNull(message = "企业竞品id 不能为空")
    @Schema(description = "企业竞品id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcCompetitiveProductId;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyVcProductId)
                && Objects.isNull(companyVcCompetitiveProductId);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyVcProductCompetitiveProductRelExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyVcProductId, exWarehouseVO.getCompanyVcProductId())) {
            this.companyVcProductId = null;
        }
        if (Objects.equals(companyVcCompetitiveProductId, exWarehouseVO.getCompanyVcCompetitiveProductId())) {
            this.companyVcCompetitiveProductId = null;
        }

    }

    public static DataCompanyVcProductCompetitiveProductRelWarehouseCommand create(Long companyVcProductId,Long companyVcCompetitiveProductId) {
        DataCompanyVcProductCompetitiveProductRelWarehouseCommand command = new DataCompanyVcProductCompetitiveProductRelWarehouseCommand();
        command.companyVcProductId = companyVcProductId;
        command.companyVcCompetitiveProductId = companyVcCompetitiveProductId;
        return command;
    }
}
