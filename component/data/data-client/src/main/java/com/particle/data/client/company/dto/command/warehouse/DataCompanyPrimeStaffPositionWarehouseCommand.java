package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业主要人员职位入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyPrimeStaffPositionWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业主要人员表ID 不能为空")
        @Schema(description = "企业主要人员表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyPrimeStaffId;


    @Schema(description = "职位名称")
    private String positionName;


    @Schema(description = "职位")
    private Long positionDictId;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyPrimeStaffId)
                && StrUtil.isEmpty(positionName)
                && Objects.isNull(positionDictId)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyPrimeStaffPositionExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyPrimeStaffId, exWarehouseVO.getCompanyPrimeStaffId())) {
            this.companyPrimeStaffId = null;
        }
        if (Objects.equals(positionName, exWarehouseVO.getPositionName())) {
            this.positionName = null;
        }
        if (Objects.equals(positionDictId, exWarehouseVO.getPositionDictId())) {
            this.positionDictId = null;
        }

    }
public static DataCompanyPrimeStaffPositionWarehouseCommand createByDataCompanyPrimeStaffPositionExWarehouseVO(DataCompanyPrimeStaffPositionExWarehouseVO exWarehouseVO) {
    DataCompanyPrimeStaffPositionWarehouseCommand command = new DataCompanyPrimeStaffPositionWarehouseCommand();
    command.companyPrimeStaffId = exWarehouseVO.getCompanyPrimeStaffId();
    command.positionName = exWarehouseVO.getPositionName();
    command.positionDictId = exWarehouseVO.getPositionDictId();

    return command;
}
}
