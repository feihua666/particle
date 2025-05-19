package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业荣誉资质入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyHonorQualificationWarehouseCommand extends AbstractBaseCommand {

    @NotNull(message = "企业表ID 不能为空")
    @Schema(description = "企业表ID",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "等级")
    private String level;


    @Schema(description = "发布日期")
    private LocalDate publishDate;


    @Schema(description = "发布日期")
    private LocalDate startDate;


    @Schema(description = "发布日期")
    private LocalDate endDate;


    @Schema(description = "证书编号")
    private String certificateNo;


    @Schema(description = "发布单位")
    private String publishOffice;


    @Schema(description = "发布标题")
    private String publishTitle;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyHonorQualificationDataMd5(name,level,certificateNo,publishOffice,publishTitle);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && StrUtil.isEmpty(name)
                && StrUtil.isEmpty(level)
                && Objects.isNull(publishDate)
                && Objects.isNull(startDate)
                && Objects.isNull(endDate)
                && StrUtil.isEmpty(certificateNo)
                && StrUtil.isEmpty(publishOffice)
                && StrUtil.isEmpty(publishTitle);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyHonorQualificationExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(name, exWarehouseVO.getName())) {
            this.name = null;
        }
        if (Objects.equals(level, exWarehouseVO.getLevel())) {
            this.level = null;
        }
        if (Objects.equals(publishDate, exWarehouseVO.getPublishDate())) {
            this.publishDate = null;
        }
        if (Objects.equals(startDate, exWarehouseVO.getStartDate())) {
            this.startDate = null;
        }
        if (Objects.equals(endDate, exWarehouseVO.getEndDate())) {
            this.endDate = null;
        }
        if (Objects.equals(certificateNo, exWarehouseVO.getCertificateNo())) {
            this.certificateNo = null;
        }
        if (Objects.equals(publishOffice, exWarehouseVO.getPublishOffice())) {
            this.publishOffice = null;
        }
        if (Objects.equals(publishTitle, exWarehouseVO.getPublishTitle())) {
            this.publishTitle = null;
        }
    }

    public static DataCompanyHonorQualificationWarehouseCommand createByDataCompanyHonorQualificationExWarehouseVO(DataCompanyHonorQualificationExWarehouseVO exWarehouseVO){
        DataCompanyHonorQualificationWarehouseCommand command = new DataCompanyHonorQualificationWarehouseCommand();
        command.companyId = exWarehouseVO.getCompanyId();
        command.name = exWarehouseVO.getName();
        command.level = exWarehouseVO.getLevel();
        command.publishDate = exWarehouseVO.getPublishDate();
        command.startDate = exWarehouseVO.getStartDate();
        command.endDate = exWarehouseVO.getEndDate();
        command.certificateNo = exWarehouseVO.getCertificateNo();
        command.publishOffice = exWarehouseVO.getPublishOffice();
        command.publishTitle = exWarehouseVO.getPublishTitle();


        return command;
    }
}
