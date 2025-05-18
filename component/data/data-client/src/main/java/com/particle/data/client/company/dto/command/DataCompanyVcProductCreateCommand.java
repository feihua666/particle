package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcProductWarehouseCommand;

/**
 * <p>
 * 企业融资产品 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Data
@Schema
public class DataCompanyVcProductCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
        @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "产品名称")
    private String productName;


    @Schema(description = "产品logo地址")
    private String productLogoUrl;


    @Schema(description = "产品介绍")
    private String productDescription;


    @Schema(description = "是否是该公司代表性的产品")
    private Boolean isPrimary;


    @Schema(description = "融资次数")
    private Integer roundNum;


    @Schema(description = "竞品数量")
    private Integer competitiveProductNum;


    @Schema(description = "当前融资轮次")
    private Long currentRoundDictId;


    @Schema(description = "融资金额（万元）")
    private BigDecimal amount;

    public static DataCompanyVcProductCreateCommand createByWarehouseCommand(DataCompanyVcProductWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyVcProductCreateCommand command = new DataCompanyVcProductCreateCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.productName = dataCompanyBasicWarehouseCommand.getProductName();
        command.productLogoUrl = dataCompanyBasicWarehouseCommand.getProductLogoUrl();
        command.productDescription = dataCompanyBasicWarehouseCommand.getProductDescription();
        command.isPrimary = dataCompanyBasicWarehouseCommand.getIsPrimary();
        command.roundNum = dataCompanyBasicWarehouseCommand.getRoundNum();
        command.competitiveProductNum = dataCompanyBasicWarehouseCommand.getCompetitiveProductNum();
        command.currentRoundDictId = dataCompanyBasicWarehouseCommand.getCurrentRoundDictId();
        command.amount = dataCompanyBasicWarehouseCommand.getAmount();

        return command;
    }
}
