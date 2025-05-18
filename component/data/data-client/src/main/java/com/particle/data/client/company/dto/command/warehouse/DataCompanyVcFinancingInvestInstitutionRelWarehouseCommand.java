package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Objects;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>
 * 企业融资历史投资机构关系入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业融资表ID 不能为空")
    @Schema(description = "企业融资表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcFinancingId;


    @NotNull(message = "企业投资机构表id 不能为空")
    @Schema(description = "企业投资机构表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyVcInvestInstitutionId;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyVcFinancingId)
                && Objects.isNull(companyVcInvestInstitutionId);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyVcFinancingId, exWarehouseVO.getCompanyVcFinancingId())) {
            this.companyVcFinancingId = null;
        }
        if (Objects.equals(companyVcInvestInstitutionId, exWarehouseVO.getCompanyVcInvestInstitutionId())) {
            this.companyVcInvestInstitutionId = null;
        }

    }

    public static DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand create(Long companyVcFinancingId, Long companyVcInvestInstitutionId) {
        DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand command = new DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand();
        command.companyVcFinancingId = companyVcFinancingId;
        command.companyVcInvestInstitutionId = companyVcInvestInstitutionId;
        return command;

    }
}
