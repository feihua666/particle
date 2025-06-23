package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;
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
 * 企业主要人员入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyPrimeStaffWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "姓名")
    private String staffName;


    @Schema(description = "个人表ID")
    private Long staffCompanyPersonId;


    @Schema(description = "职位名称")
    private String positionNames;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && StrUtil.isEmpty(staffName)
                && Objects.isNull(staffCompanyPersonId)
                && StrUtil.isEmpty(positionNames);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyPrimeStaffExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(staffName, exWarehouseVO.getStaffName())) {
            this.staffName = null;
        }
        if (Objects.equals(staffCompanyPersonId, exWarehouseVO.getStaffCompanyPersonId())) {
            this.staffCompanyPersonId = null;
        }
        if (Objects.equals(positionNames, exWarehouseVO.getPositionNames())) {
            this.positionNames = null;
        }

    }

    public static DataCompanyPrimeStaffWarehouseCommand createByDataCompanyPrimeStaffExWarehouseVO(DataCompanyPrimeStaffExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanyPrimeStaffWarehouseCommand command = new DataCompanyPrimeStaffWarehouseCommand();
                command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.staffName = dataCompanyBasicWarehouseCommand.getStaffName();
        command.staffCompanyPersonId = dataCompanyBasicWarehouseCommand.getStaffCompanyPersonId();
        command.positionNames = dataCompanyBasicWarehouseCommand.getPositionNames();

        return command;
    }
}
