package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权出质入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPledgeWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "知识产权登记证号")
    private String regNo;


    @Schema(description = "知识产权名称")
    private String name;


    @Schema(description = "知识产权种类")
    private Long typeName;


    @Schema(description = "出质人名称")
    private String pledgor;


    @Schema(description = "是否出质人为自然人")
    private Boolean isPledgorNaturalPerson;


    @Schema(description = "出质人公司id")
    private Long pledgorCompanyId;


    @Schema(description = "出质人个人id")
    private Long pledgorCompanyPersonId;


    @Schema(description = "质权人名称")
    private String pledgee;


    @Schema(description = "是否质权人为自然人")
    private Boolean isPledgeeNaturalPerson;


    @Schema(description = "质权人公司id")
    private Long pledgeeCompanyId;


    @Schema(description = "质权人个人id")
    private Long pledgeeCompanyPersonId;


    @Schema(description = "质权登记期限自")
    private LocalDate pledgeFromDate;


    @Schema(description = "质权登记期限至")
    private LocalDate pledgeToDate;


    @Schema(description = "状态")
    private String statusName;


    @Schema(description = "公示日期")
    private LocalDate publishDate;


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
                && StrUtil.isEmpty(name)
                && Objects.isNull(typeName)
                && StrUtil.isEmpty(pledgor)
                && Objects.isNull(isPledgorNaturalPerson)
                && Objects.isNull(pledgorCompanyId)
                && Objects.isNull(pledgorCompanyPersonId)
                && StrUtil.isEmpty(pledgee)
                && Objects.isNull(isPledgeeNaturalPerson)
                && Objects.isNull(pledgeeCompanyId)
                && Objects.isNull(pledgeeCompanyPersonId)
                && Objects.isNull(pledgeFromDate)
                && Objects.isNull(pledgeToDate)
                && StrUtil.isEmpty(statusName)
                && Objects.isNull(publishDate)
                && Objects.isNull(cancelDate)
                && StrUtil.isEmpty(cancelReason)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPledgeExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(regNo, exWarehouseVO.getRegNo())) {
            this.regNo = null;
        }
        if (Objects.equals(name, exWarehouseVO.getName())) {
            this.name = null;
        }
        if (Objects.equals(typeName, exWarehouseVO.getTypeName())) {
            this.typeName = null;
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
        if (Objects.equals(pledgeFromDate, exWarehouseVO.getPledgeFromDate())) {
            this.pledgeFromDate = null;
        }
        if (Objects.equals(pledgeToDate, exWarehouseVO.getPledgeToDate())) {
            this.pledgeToDate = null;
        }
        if (Objects.equals(statusName, exWarehouseVO.getStatusName())) {
            this.statusName = null;
        }
        if (Objects.equals(publishDate, exWarehouseVO.getPublishDate())) {
            this.publishDate = null;
        }
        if (Objects.equals(cancelDate, exWarehouseVO.getCancelDate())) {
            this.cancelDate = null;
        }
        if (Objects.equals(cancelReason, exWarehouseVO.getCancelReason())) {
            this.cancelReason = null;
        }

    }

    public static DataCompanyIprPledgeWarehouseCommand createByDataCompanyIprPledgeExWarehouseVO(DataCompanyIprPledgeExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyIprPledgeWarehouseCommand command = new DataCompanyIprPledgeWarehouseCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.regNo = dataCompanyBasicWarehouseCommand.getRegNo();
        command.name = dataCompanyBasicWarehouseCommand.getName();
        command.typeName = dataCompanyBasicWarehouseCommand.getTypeName();
        command.pledgor = dataCompanyBasicWarehouseCommand.getPledgor();
        command.isPledgorNaturalPerson = dataCompanyBasicWarehouseCommand.getIsPledgorNaturalPerson();
        command.pledgorCompanyId = dataCompanyBasicWarehouseCommand.getPledgorCompanyId();
        command.pledgorCompanyPersonId = dataCompanyBasicWarehouseCommand.getPledgorCompanyPersonId();
        command.pledgee = dataCompanyBasicWarehouseCommand.getPledgee();
        command.isPledgeeNaturalPerson = dataCompanyBasicWarehouseCommand.getIsPledgeeNaturalPerson();
        command.pledgeeCompanyId = dataCompanyBasicWarehouseCommand.getPledgeeCompanyId();
        command.pledgeeCompanyPersonId = dataCompanyBasicWarehouseCommand.getPledgeeCompanyPersonId();
        command.pledgeFromDate = dataCompanyBasicWarehouseCommand.getPledgeFromDate();
        command.pledgeToDate = dataCompanyBasicWarehouseCommand.getPledgeToDate();
        command.statusName = dataCompanyBasicWarehouseCommand.getStatusName();
        command.publishDate = dataCompanyBasicWarehouseCommand.getPublishDate();
        command.cancelDate = dataCompanyBasicWarehouseCommand.getCancelDate();
        command.cancelReason = dataCompanyBasicWarehouseCommand.getCancelReason();


        return command;
    }
}
