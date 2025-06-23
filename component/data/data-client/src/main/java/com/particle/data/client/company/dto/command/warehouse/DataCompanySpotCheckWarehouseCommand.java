package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业抽查检查入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanySpotCheckWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "检查实施机关")
    private String checkInstitute;


    @Schema(description = "检查实施机关公司id")
    private Long checkInstituteCompanyId;


    @Schema(description = "检查类型")
    private String checkTypeName;


    @Schema(description = "检查日期")
    private LocalDate checkDate;


    @Schema(description = "检查结果")
    private String checkResult;


    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanySpotCheckDataMd5(checkInstitute, checkTypeName, checkDate, checkResult);
        }
        return dataMd5;
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && StrUtil.isEmpty(checkInstitute)
                && Objects.isNull(checkInstituteCompanyId)
                && Objects.isNull(checkTypeName)
                && Objects.isNull(checkDate)
                && StrUtil.isEmpty(checkResult)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanySpotCheckExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(checkInstitute, exWarehouseVO.getCheckInstitute())) {
            this.checkInstitute = null;
        }
        if (Objects.equals(checkInstituteCompanyId, exWarehouseVO.getCheckInstituteCompanyId())) {
            this.checkInstituteCompanyId = null;
        }
        if (Objects.equals(checkTypeName, exWarehouseVO.getCheckTypeName())) {
            this.checkTypeName = null;
        }
        if (Objects.equals(checkDate, exWarehouseVO.getCheckDate())) {
            this.checkDate = null;
        }
        if (Objects.equals(checkResult, exWarehouseVO.getCheckResult())) {
            this.checkResult = null;
        }

    }

    public static DataCompanySpotCheckWarehouseCommand createByDataCompanySpotCheckExWarehouseVO(DataCompanySpotCheckExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanySpotCheckWarehouseCommand command = new DataCompanySpotCheckWarehouseCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.checkInstitute = dataCompanyBasicWarehouseCommand.getCheckInstitute();
        command.checkInstituteCompanyId = dataCompanyBasicWarehouseCommand.getCheckInstituteCompanyId();
        command.checkTypeName = dataCompanyBasicWarehouseCommand.getCheckTypeName();
        command.checkDate = dataCompanyBasicWarehouseCommand.getCheckDate();
        command.checkResult = dataCompanyBasicWarehouseCommand.getCheckResult();


        return command;
    }
}
