package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySeriousIllegalExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Objects;
import cn.hutool.core.util.NumberUtil;

/**
 * <p>
 * 企业严重违法入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanySeriousIllegalWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "类别")
    private String type;


    @NotEmpty(message = "列入原因 不能为空")
        @Schema(description = "列入原因",requiredMode = Schema.RequiredMode.REQUIRED)
    private String putReason;


    @NotNull(message = "列入日期 不能为空")
        @Schema(description = "列入日期",requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate putDate;


    @Schema(description = "作出列入决定机关公司id")
    private Long putInstituteCompanyId;


    @Schema(description = "作出列入决定机关名称")
    private String putInstituteName;


    @Schema(description = "移除原因")
    private String removeReason;


    @Schema(description = "移除日期")
    private LocalDate removeDate;


    @Schema(description = "作出移除决定机关公司id")
    private Long removeInstituteCompanyId;


    @Schema(description = "作出移除决定机关名称")
    private String removeInstituteName;

    public String obtainDataMd5() {
        return SomeMd5Tool.dataCompanySeriousIllegalDataMd5(type, putReason, putDate);
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && StrUtil.isEmpty(type)
                && StrUtil.isEmpty(putReason)
                && Objects.isNull(putDate)
                && Objects.isNull(putInstituteCompanyId)
                && StrUtil.isEmpty(putInstituteName)
                && StrUtil.isEmpty(removeReason)
                && Objects.isNull(removeDate)
                && Objects.isNull(removeInstituteCompanyId)
                && StrUtil.isEmpty(removeInstituteName);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanySeriousIllegalExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(type, exWarehouseVO.getType())) {
            this.type = null;
        }
        if (Objects.equals(putReason, exWarehouseVO.getPutReason())) {
            this.putReason = null;
        }
        if (Objects.equals(putDate, exWarehouseVO.getPutDate())) {
            this.putDate = null;
        }
        if (Objects.equals(putInstituteCompanyId, exWarehouseVO.getPutInstituteCompanyId())) {
            this.putInstituteCompanyId = null;
        }
        if (Objects.equals(putInstituteName, exWarehouseVO.getPutInstituteName())) {
            this.putInstituteName = null;
        }
        if (Objects.equals(removeReason, exWarehouseVO.getRemoveReason())) {
            this.removeReason = null;
        }
        if (Objects.equals(removeDate, exWarehouseVO.getRemoveDate())) {
            this.removeDate = null;
        }
        if (Objects.equals(removeInstituteCompanyId, exWarehouseVO.getRemoveInstituteCompanyId())) {
            this.removeInstituteCompanyId = null;
        }
        if (Objects.equals(removeInstituteName, exWarehouseVO.getRemoveInstituteName())) {
            this.removeInstituteName = null;
        }

    }

    public static DataCompanySeriousIllegalWarehouseCommand createByDataCompanySeriousIllegalExWarehouseVO(DataCompanySeriousIllegalExWarehouseVO dataCompanyBasicWarehouseCommand){
        DataCompanySeriousIllegalWarehouseCommand command = new DataCompanySeriousIllegalWarehouseCommand();
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.type = dataCompanyBasicWarehouseCommand.getType();
        command.putReason = dataCompanyBasicWarehouseCommand.getPutReason();
        command.putDate = dataCompanyBasicWarehouseCommand.getPutDate();
        command.putInstituteCompanyId = dataCompanyBasicWarehouseCommand.getPutInstituteCompanyId();
        command.putInstituteName = dataCompanyBasicWarehouseCommand.getPutInstituteName();
        command.removeReason = dataCompanyBasicWarehouseCommand.getRemoveReason();
        command.removeDate = dataCompanyBasicWarehouseCommand.getRemoveDate();
        command.removeInstituteCompanyId = dataCompanyBasicWarehouseCommand.getRemoveInstituteCompanyId();
        command.removeInstituteName = dataCompanyBasicWarehouseCommand.getRemoveInstituteName();


        return command;
    }
}