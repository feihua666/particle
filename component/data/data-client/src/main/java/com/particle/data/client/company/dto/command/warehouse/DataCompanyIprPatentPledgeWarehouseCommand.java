package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPledgeExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Objects;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>
 * 企业知识产权专利质押信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentPledgeWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利id 不能为空")
    @Schema(description = "企业知识产权专利id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;


    @Schema(description = "质押登记号")
    private String pledgeNo;


    @Schema(description = "质押保全类型")
    private String pledgePreserveType;


    @Schema(description = "质押保全权力类型")
    private String pledgePreserveRightType;


    @Schema(description = "生效日期")
    private LocalDate effectiveDate;


    @Schema(description = "变更日期")
    private LocalDate changeDate;


    @Schema(description = "出质人")
    private String pledgor;


    @Schema(description = "质权人")
    private String pledgee;


    @Schema(description = "解除日期")
    private LocalDate rescissionDate;

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPatentId)
                && StrUtil.isEmpty(pledgeNo)
                && StrUtil.isEmpty(pledgePreserveType)
                && StrUtil.isEmpty(pledgePreserveRightType)
                && Objects.isNull(effectiveDate)
                && Objects.isNull(changeDate)
                && StrUtil.isEmpty(pledgor)
                && StrUtil.isEmpty(pledgee)
                && Objects.isNull(rescissionDate)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentPledgeExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyIprPatentId, exWarehouseVO.getCompanyIprPatentId())) {
            this.companyIprPatentId = null;
        }
        if (Objects.equals(pledgeNo, exWarehouseVO.getPledgeNo())) {
            this.pledgeNo = null;
        }
        if (Objects.equals(pledgePreserveType, exWarehouseVO.getPledgePreserveType())) {
            this.pledgePreserveType = null;
        }
        if (Objects.equals(pledgePreserveRightType, exWarehouseVO.getPledgePreserveRightType())) {
            this.pledgePreserveRightType = null;
        }
        if (Objects.equals(effectiveDate, exWarehouseVO.getEffectiveDate())) {
            this.effectiveDate = null;
        }
        if (Objects.equals(changeDate, exWarehouseVO.getChangeDate())) {
            this.changeDate = null;
        }
        if (Objects.equals(pledgor, exWarehouseVO.getPledgor())) {
            this.pledgor = null;
        }
        if (Objects.equals(pledgee, exWarehouseVO.getPledgee())) {
            this.pledgee = null;
        }
        if (Objects.equals(rescissionDate, exWarehouseVO.getRescissionDate())) {
            this.rescissionDate = null;
        }

    }
    public static DataCompanyIprPatentPledgeWarehouseCommand createByDataCompanyIprPatentPledgeExWarehouseVO(DataCompanyIprPatentPledgeExWarehouseVO exWarehouseVO) {
        DataCompanyIprPatentPledgeWarehouseCommand command = new DataCompanyIprPatentPledgeWarehouseCommand();
        command.companyIprPatentId = exWarehouseVO.getCompanyIprPatentId();
        command.pledgeNo = exWarehouseVO.getPledgeNo();
        command.pledgePreserveType = exWarehouseVO.getPledgePreserveType();
        command.pledgePreserveRightType = exWarehouseVO.getPledgePreserveRightType();
        command.effectiveDate = exWarehouseVO.getEffectiveDate();
        command.changeDate = exWarehouseVO.getChangeDate();
        command.pledgor = exWarehouseVO.getPledgor();
        command.pledgee = exWarehouseVO.getPledgee();
        command.rescissionDate = exWarehouseVO.getRescissionDate();


        return command;
    }
}
