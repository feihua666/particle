package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEquityPledgeExWarehouseVO;
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
 * 企业股权出质入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyEquityPledgeWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "登记编号")
    private String regNo;


    @Schema(description = "出质人")
    private String pledgor;


    @Schema(description = "是否出质人为自然人")
    private Boolean isPledgorNaturalPerson;


    @Schema(description = "出质人公司id")
    private Long pledgorCompanyId;


    @Schema(description = "出质人个人id")
    private Long pledgorCompanyPersonId;


    @Schema(description = "出质人证照/证件号码")
    private String pledgorLicenseNo;


    @Schema(description = "出质股权数额（万股）")
    private BigDecimal equityAmount;


    @Schema(description = "质权人")
    private String pledgee;


    @Schema(description = "是否质权人为自然人")
    private Boolean isPledgeeNaturalPerson;


    @Schema(description = "质权人公司id")
    private Long pledgeeCompanyId;


    @Schema(description = "质权人个人id")
    private Long pledgeeCompanyPersonId;


    @Schema(description = "质权人证照/证件号码")
    private String pledgeeLicenseNo;


    @Schema(description = "股权出质设立登记日期")
    private LocalDate regDate;


    @Schema(description = "状态")
    private String statusName;


    @Schema(description = "公示日期")
    private LocalDate publishDate;


    @Schema(description = "变化情况")
    private String changeSituation;


    @Schema(description = "注销日期")
    private LocalDate cancelDate;


    @Schema(description = "注销原因")
    private String cancelReason;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && StrUtil.isEmpty(regNo)
                && StrUtil.isEmpty(pledgor)
                && Objects.isNull(isPledgorNaturalPerson)
                && Objects.isNull(pledgorCompanyId)
                && Objects.isNull(pledgorCompanyPersonId)
                && StrUtil.isEmpty(pledgorLicenseNo)
                && Objects.isNull(equityAmount)
                && StrUtil.isEmpty(pledgee)
                && Objects.isNull(isPledgeeNaturalPerson)
                && Objects.isNull(pledgeeCompanyId)
                && Objects.isNull(pledgeeCompanyPersonId)
                && StrUtil.isEmpty(pledgeeLicenseNo)
                && Objects.isNull(regDate)
                && StrUtil.isEmpty(statusName)
                && Objects.isNull(publishDate)
                && StrUtil.isEmpty(changeSituation)
                && Objects.isNull(cancelDate)
                && StrUtil.isEmpty(cancelReason)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyEquityPledgeExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(regNo, exWarehouseVO.getRegNo())) {
            this.regNo = null;
        }
        if (Objects.equals(pledgor, exWarehouseVO.getPledgor())) {
            this.pledgor = null;
        }
        if (Objects.equals(isPledgorNaturalPerson, exWarehouseVO.getIsPledgorNaturalPerson())) {
            this.isPledgorNaturalPerson = null;
        }
        if (Objects.equals(pledgorCompanyId, exWarehouseVO.getPledgorCompanyId())) {
            this.pledgorCompanyId = null;
        }
        if (Objects.equals(pledgorCompanyPersonId, exWarehouseVO.getPledgorCompanyPersonId())) {
            this.pledgorCompanyPersonId = null;
        }
        if (Objects.equals(pledgorLicenseNo, exWarehouseVO.getPledgorLicenseNo())) {
            this.pledgorLicenseNo = null;
        }
        if (NumberUtil.equals(equityAmount, exWarehouseVO.getEquityAmount())) {
            this.equityAmount = null;
        }
        if (Objects.equals(pledgee, exWarehouseVO.getPledgee())) {
            this.pledgee = null;
        }
        if (Objects.equals(isPledgeeNaturalPerson, exWarehouseVO.getIsPledgeeNaturalPerson())) {
            this.isPledgeeNaturalPerson = null;
        }
        if (Objects.equals(pledgeeCompanyId, exWarehouseVO.getPledgeeCompanyId())) {
            this.pledgeeCompanyId = null;
        }
        if (Objects.equals(pledgeeCompanyPersonId, exWarehouseVO.getPledgeeCompanyPersonId())) {
            this.pledgeeCompanyPersonId = null;
        }
        if (Objects.equals(pledgeeLicenseNo, exWarehouseVO.getPledgeeLicenseNo())) {
            this.pledgeeLicenseNo = null;
        }
        if (Objects.equals(regDate, exWarehouseVO.getRegDate())) {
            this.regDate = null;
        }
        if (Objects.equals(statusName, exWarehouseVO.getStatusName())) {
            this.statusName = null;
        }
        if (Objects.equals(publishDate, exWarehouseVO.getPublishDate())) {
            this.publishDate = null;
        }
        if (Objects.equals(changeSituation, exWarehouseVO.getChangeSituation())) {
            this.changeSituation = null;
        }
        if (Objects.equals(cancelDate, exWarehouseVO.getCancelDate())) {
            this.cancelDate = null;
        }
        if (Objects.equals(cancelReason, exWarehouseVO.getCancelReason())) {
            this.cancelReason = null;
        }

    }

    public static DataCompanyEquityPledgeWarehouseCommand createByDataCompanyEquityPledgeExWarehouseVO(DataCompanyEquityPledgeExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyEquityPledgeWarehouseCommand command = new DataCompanyEquityPledgeWarehouseCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.regNo = dataCompanyBasicWarehouseCommand.getRegNo();
        command.pledgor = dataCompanyBasicWarehouseCommand.getPledgor();
        command.isPledgorNaturalPerson = dataCompanyBasicWarehouseCommand.getIsPledgorNaturalPerson();
        command.pledgorCompanyId = dataCompanyBasicWarehouseCommand.getPledgorCompanyId();
        command.pledgorCompanyPersonId = dataCompanyBasicWarehouseCommand.getPledgorCompanyPersonId();
        command.pledgorLicenseNo = dataCompanyBasicWarehouseCommand.getPledgorLicenseNo();
        command.equityAmount = dataCompanyBasicWarehouseCommand.getEquityAmount();
        command.pledgee = dataCompanyBasicWarehouseCommand.getPledgee();
        command.isPledgeeNaturalPerson = dataCompanyBasicWarehouseCommand.getIsPledgeeNaturalPerson();
        command.pledgeeCompanyId = dataCompanyBasicWarehouseCommand.getPledgeeCompanyId();
        command.pledgeeCompanyPersonId = dataCompanyBasicWarehouseCommand.getPledgeeCompanyPersonId();
        command.pledgeeLicenseNo = dataCompanyBasicWarehouseCommand.getPledgeeLicenseNo();
        command.regDate = dataCompanyBasicWarehouseCommand.getRegDate();
        command.statusName = dataCompanyBasicWarehouseCommand.getStatusName();
        command.publishDate = dataCompanyBasicWarehouseCommand.getPublishDate();
        command.changeSituation = dataCompanyBasicWarehouseCommand.getChangeSituation();
        command.cancelDate = dataCompanyBasicWarehouseCommand.getCancelDate();
        command.cancelReason = dataCompanyBasicWarehouseCommand.getCancelReason();


        return command;
    }
}
