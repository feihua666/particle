package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductExWarehouseVO;
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
 * 企业融资产品入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyVcProductWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
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

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyVcProductDataMd5(productName);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && StrUtil.isEmpty(productName)
                && StrUtil.isEmpty(productLogoUrl)
                && StrUtil.isEmpty(productDescription)
                && Objects.isNull(isPrimary)
                && Objects.isNull(roundNum)
                && Objects.isNull(competitiveProductNum)
                && Objects.isNull(currentRoundDictId)
                && Objects.isNull(amount);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyVcProductExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(productName, exWarehouseVO.getProductName())) {
            this.productName = null;
        }
        if (Objects.equals(productLogoUrl, exWarehouseVO.getProductLogoUrl())) {
            this.productLogoUrl = null;
        }
        if (Objects.equals(productDescription, exWarehouseVO.getProductDescription())) {
            this.productDescription = null;
        }
        if (Objects.equals(isPrimary, exWarehouseVO.getIsPrimary())) {
            this.isPrimary = null;
        }
        if (Objects.equals(roundNum, exWarehouseVO.getRoundNum())) {
            this.roundNum = null;
        }
        if (Objects.equals(competitiveProductNum, exWarehouseVO.getCompetitiveProductNum())) {
            this.competitiveProductNum = null;
        }
        if (Objects.equals(currentRoundDictId, exWarehouseVO.getCurrentRoundDictId())) {
            this.currentRoundDictId = null;
        }
        if (NumberUtil.equals(amount, exWarehouseVO.getAmount())) {
            this.amount = null;
        }
    }

    public static DataCompanyVcProductWarehouseCommand createByDataCompanyVcProductExWarehouseVO(DataCompanyVcProductExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyVcProductWarehouseCommand command = new DataCompanyVcProductWarehouseCommand();
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
