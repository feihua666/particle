package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Objects;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>
 * 企业融资入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyVcFinancingWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
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

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyVcFinancingDataMd5(productName,roundName,amount,valuation,financingDate,publishTitle);
        }
        return dataMd5;
    }


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && Objects.isNull(companyVcProductId)
                && StrUtil.isEmpty(productName)
                && Objects.isNull(roundDictId)
                && StrUtil.isEmpty(roundName)
                && Objects.isNull(amount)
                && Objects.isNull(amountCurrencyDictId)
                && StrUtil.isEmpty(valuation)
                && Objects.isNull(financingDate)
                && Objects.isNull(publishAt)
                && StrUtil.isEmpty(publishTitle)
                && StrUtil.isEmpty(publishUrl)
                && StrUtil.isEmpty(publishSnapshotUrl);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyVcFinancingExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(companyVcProductId, exWarehouseVO.getCompanyVcProductId())) {
            this.companyVcProductId = null;
        }
        if (Objects.equals(productName, exWarehouseVO.getProductName())) {
            this.productName = null;
        }
        if (Objects.equals(roundDictId, exWarehouseVO.getRoundDictId())) {
            this.roundDictId = null;
        }
        if (Objects.equals(roundName, exWarehouseVO.getRoundName())) {
            this.roundName = null;
        }
        if (NumberUtil.equals(amount, exWarehouseVO.getAmount())) {
            this.amount = null;
        }
        if (Objects.equals(amountCurrencyDictId, exWarehouseVO.getAmountCurrencyDictId())) {
            this.amountCurrencyDictId = null;
        }
        if (Objects.equals(valuation, exWarehouseVO.getValuation())) {
            this.valuation = null;
        }
        if (Objects.equals(financingDate, exWarehouseVO.getFinancingDate())) {
            this.financingDate = null;
        }
        if (Objects.equals(publishAt, exWarehouseVO.getPublishAt())) {
            this.publishAt = null;
        }
        if (Objects.equals(publishTitle, exWarehouseVO.getPublishTitle())) {
            this.publishTitle = null;
        }
        if (Objects.equals(publishUrl, exWarehouseVO.getPublishUrl())) {
            this.publishUrl = null;
        }
        if (Objects.equals(publishSnapshotUrl, exWarehouseVO.getPublishSnapshotUrl())) {
            this.publishSnapshotUrl = null;
        }

    }

    public static DataCompanyVcFinancingWarehouseCommand createByDataCompanyVcFinancingExWarehouseVO(DataCompanyVcFinancingExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyVcFinancingWarehouseCommand command = new DataCompanyVcFinancingWarehouseCommand();
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

        return command;
    }
}
