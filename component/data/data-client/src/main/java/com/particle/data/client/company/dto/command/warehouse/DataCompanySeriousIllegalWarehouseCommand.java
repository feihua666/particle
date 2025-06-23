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



    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;

    @Schema(description = "企业名称")
    private String companyName;

    @Schema(description = "列入决定书文号")
    private String putNo;

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

    @Schema(description = "移出决定书文号")
    private String removeNo;

    @Schema(description = "移除原因")
    private String removeReason;


    @Schema(description = "移除日期")
    private LocalDate removeDate;


    @Schema(description = "作出移除决定机关公司id")
    private Long removeInstituteCompanyId;


    @Schema(description = "作出移除决定机关名称")
    private String removeInstituteName;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanySeriousIllegalDataMd5(putNo,putReason,putDate,putInstituteName);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && StrUtil.isEmpty(companyName)
                && StrUtil.isEmpty(putNo)
                && StrUtil.isEmpty(putReason)
                && Objects.isNull(putDate)
                && Objects.isNull(putInstituteCompanyId)
                && StrUtil.isEmpty(putInstituteName)
                && StrUtil.isEmpty(removeNo)
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
        if (Objects.equals(companyName, exWarehouseVO.getCompanyName())) {
            this.companyName = null;
        }
        if (Objects.equals(putNo, exWarehouseVO.getCompanyName())) {
            this.putNo = null;
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
        if (Objects.equals(removeNo, exWarehouseVO.getRemoveNo())) {
            this.removeNo = null;
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
        command.companyName = dataCompanyBasicWarehouseCommand.getCompanyName();
        command.putNo = dataCompanyBasicWarehouseCommand.getPutNo();
        command.putReason = dataCompanyBasicWarehouseCommand.getPutReason();
        command.putDate = dataCompanyBasicWarehouseCommand.getPutDate();
        command.putInstituteCompanyId = dataCompanyBasicWarehouseCommand.getPutInstituteCompanyId();
        command.putInstituteName = dataCompanyBasicWarehouseCommand.getPutInstituteName();
        command.removeNo = dataCompanyBasicWarehouseCommand.getRemoveNo();
        command.removeReason = dataCompanyBasicWarehouseCommand.getRemoveReason();
        command.removeDate = dataCompanyBasicWarehouseCommand.getRemoveDate();
        command.removeInstituteCompanyId = dataCompanyBasicWarehouseCommand.getRemoveInstituteCompanyId();
        command.removeInstituteName = dataCompanyBasicWarehouseCommand.getRemoveInstituteName();


        return command;
    }
}
