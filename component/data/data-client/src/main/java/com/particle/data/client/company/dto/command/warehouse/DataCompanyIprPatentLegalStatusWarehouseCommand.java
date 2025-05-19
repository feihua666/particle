package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentLegalStatusExWarehouseVO;
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
 * 企业知识产权专利法律状态入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentLegalStatusWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利表id 不能为空")
    @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;

    @Schema(description = "法律状态")
    private Long legalStatusDictId;

    @Schema(description = "法律状态代码")
    private String legalStatusCode;

    @Schema(description = "原始法律状态名称")
    private String legalStatusName;

    @Schema(description = "英文法律状态名称")
    private String legalStatusNameEn;

    @Schema(description = "中文法律状态名称")
    private String legalStatusNameCn;

    @Schema(description = "原始法律状态详情")
    private String legalStatusDetail;

    @Schema(description = "英文法律状态详情")
    private String legalStatusDetailEn;

    @Schema(description = "中文法律状态详情")
    private String legalStatusDetailCn;

    @Schema(description = "法律状态日期")
    private LocalDate legalStatusDate;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprPatentLegalStatusDataMd5(legalStatusName,legalStatusDetail,legalStatusDate);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPatentId)
                && Objects.isNull(legalStatusDictId)
                && StrUtil.isEmpty(legalStatusCode)
                && StrUtil.isEmpty(legalStatusName)
                && StrUtil.isEmpty(legalStatusNameEn)
                && StrUtil.isEmpty(legalStatusNameCn)
                && StrUtil.isEmpty(legalStatusDetail)
                && StrUtil.isEmpty(legalStatusDetailEn)
                && StrUtil.isEmpty(legalStatusDetailCn)
                && Objects.isNull(legalStatusDate);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentLegalStatusExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprPatentId, exWarehouseVO.getCompanyIprPatentId())) {
            this.companyIprPatentId = null;
        }
        if (Objects.equals(legalStatusDictId, exWarehouseVO.getLegalStatusDictId())) {
            this.legalStatusDictId = null;
        }
        if (Objects.equals(legalStatusCode, exWarehouseVO.getLegalStatusCode())) {
            this.legalStatusCode = null;
        }
        if (Objects.equals(legalStatusName, exWarehouseVO.getLegalStatusName())) {
            this.legalStatusName = null;
        }
        if (Objects.equals(legalStatusNameEn, exWarehouseVO.getLegalStatusNameEn())) {
            this.legalStatusNameEn = null;
        }
        if (Objects.equals(legalStatusNameCn, exWarehouseVO.getLegalStatusNameCn())) {
            this.legalStatusNameCn = null;
        }
        if (Objects.equals(legalStatusDetail, exWarehouseVO.getLegalStatusDetail())) {
            this.legalStatusDetail = null;
        }
        if (Objects.equals(legalStatusDetailEn, exWarehouseVO.getLegalStatusDetailEn())) {
            this.legalStatusDetailEn = null;
        }
        if (Objects.equals(legalStatusDetailCn, exWarehouseVO.getLegalStatusDetailCn())) {
            this.legalStatusDetailCn = null;
        }
        if (Objects.equals(legalStatusDate, exWarehouseVO.getLegalStatusDate())) {
            this.legalStatusDate = null;
        }
    }
    public static DataCompanyIprPatentLegalStatusWarehouseCommand createByDataCompanyIprPatentLegalStatusExWarehouseVO(DataCompanyIprPatentLegalStatusExWarehouseVO exWarehouseVO) {
        DataCompanyIprPatentLegalStatusWarehouseCommand command = new DataCompanyIprPatentLegalStatusWarehouseCommand();
        command.companyIprPatentId = exWarehouseVO.getCompanyIprPatentId();
        command.legalStatusDictId = exWarehouseVO.getLegalStatusDictId();
        command.legalStatusCode = exWarehouseVO.getLegalStatusCode();
        command.legalStatusName = exWarehouseVO.getLegalStatusName();
        command.legalStatusNameEn = exWarehouseVO.getLegalStatusNameEn();
        command.legalStatusNameCn = exWarehouseVO.getLegalStatusNameCn();
        command.legalStatusDetail = exWarehouseVO.getLegalStatusDetail();
        command.legalStatusDetailEn = exWarehouseVO.getLegalStatusDetailEn();
        command.legalStatusDetailCn = exWarehouseVO.getLegalStatusDetailCn();
        command.legalStatusDate = exWarehouseVO.getLegalStatusDate();

        return command;
    }
}
