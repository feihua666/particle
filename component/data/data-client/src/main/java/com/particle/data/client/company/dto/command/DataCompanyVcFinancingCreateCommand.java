package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcFinancingWarehouseCommand;

/**
 * <p>
 * 企业融资 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Data
@Schema
public class DataCompanyVcFinancingCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "公司产品id")
    private Long companyVcProductId;

	@Schema(description = "产品名称，冗余产品名称")
	private String productName;


    @Schema(description = "融资轮次")
    private Long roundDictId;

	@Schema(description = "融资轮次名称")
	private String roundName;


    @Schema(description = "融资金额（万元）")
    private BigDecimal amount;


    @Schema(description = "融资金额币种")
    private Long amountCurrencyDictId;


    @Schema(description = "估值")
    private String valuation;


    @Schema(description = "融资日期")
    private LocalDate financingDate;


    @Schema(description = "报道时间")
    private LocalDateTime publishAt;


    @Schema(description = "报道标题")
    private String publishTitle;


    @Schema(description = "报道链接地址")
    private String publishUrl;


    @Schema(description = "报道快照链接地址")
    private String publishSnapshotUrl;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public static DataCompanyVcFinancingCreateCommand createByWarehouseCommand(DataCompanyVcFinancingWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyVcFinancingCreateCommand command = new DataCompanyVcFinancingCreateCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.companyVcProductId = dataCompanyBasicWarehouseCommand.getCompanyVcProductId();
        command.productName = dataCompanyBasicWarehouseCommand.getProductName();
        command.roundDictId = dataCompanyBasicWarehouseCommand.getRoundDictId();
        command.roundName = dataCompanyBasicWarehouseCommand.getRoundName();
        command.amount = dataCompanyBasicWarehouseCommand.getAmount();
        command.amountCurrencyDictId = dataCompanyBasicWarehouseCommand.getAmountCurrencyDictId();
        command.valuation = dataCompanyBasicWarehouseCommand.getValuation();
        command.financingDate = dataCompanyBasicWarehouseCommand.getFinancingDate();
        command.publishAt = dataCompanyBasicWarehouseCommand.getPublishAt();
        command.publishTitle = dataCompanyBasicWarehouseCommand.getPublishTitle();
        command.publishUrl = dataCompanyBasicWarehouseCommand.getPublishUrl();
        command.publishSnapshotUrl = dataCompanyBasicWarehouseCommand.getPublishSnapshotUrl();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
