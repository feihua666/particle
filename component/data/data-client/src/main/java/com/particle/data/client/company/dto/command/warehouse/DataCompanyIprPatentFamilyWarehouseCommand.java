package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentFamilyExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业知识产权专利同族信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentFamilyWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利表id 不能为空")
    @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;

    @Schema(description = "原始申请号")
    private String applyNo;

    @Schema(description = "标准申请号，如：CN101995000006852")
    private String standardApplyNo;


    @Schema(description = "申请日期")
    private LocalDate applyDate;


    @Schema(description = "原始公布号")
    private String publicNo;

    @Schema(description = "标准公布号")
    private String standardPublicNo;

    @Schema(description = "公布日")
    private LocalDate publicDate;


    @Schema(description = "原始标题")
    private String title;

    @Schema(description = "中文标题")
    private String titleCn;

    @Schema(description = "英文标题")
    private String titleEn;


    @Schema(description = "国别")
    private String countryCode;


    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPatentId)
                && StrUtil.isEmpty(applyNo)
                && StrUtil.isEmpty(standardApplyNo)
                && Objects.isNull(applyDate)
                && StrUtil.isEmpty(publicNo)
                && StrUtil.isEmpty(standardPublicNo)
                && Objects.isNull(publicDate)
                && StrUtil.isEmpty(title)
                && StrUtil.isEmpty(titleCn)
                && StrUtil.isEmpty(titleEn)
                && StrUtil.isEmpty(countryCode)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentFamilyExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprPatentId, exWarehouseVO.getCompanyIprPatentId())) {
            this.companyIprPatentId = null;
        }
        if (Objects.equals(applyNo, exWarehouseVO.getApplyNo())) {
            this.applyNo = null;
        }
        if (Objects.equals(standardApplyNo, exWarehouseVO.getStandardApplyNo())) {
            this.standardApplyNo = null;
        }
        if (Objects.equals(applyDate, exWarehouseVO.getApplyDate())) {
            this.applyDate = null;
        }
        if (Objects.equals(publicNo, exWarehouseVO.getPublicNo())) {
            this.publicNo = null;
        }
        if (Objects.equals(standardPublicNo, exWarehouseVO.getStandardPublicNo())) {
            this.standardPublicNo = null;
        }
        if (Objects.equals(publicDate, exWarehouseVO.getPublicDate())) {
            this.publicDate = null;
        }
        if (Objects.equals(title, exWarehouseVO.getTitle())) {
            this.title = null;
        }
        if (Objects.equals(titleCn, exWarehouseVO.getTitleCn())) {
            this.titleCn = null;
        }
        if (Objects.equals(titleEn, exWarehouseVO.getTitleEn())) {
            this.titleEn = null;
        }
        if (Objects.equals(countryCode, exWarehouseVO.getCountryCode())) {
            this.countryCode = null;
        }

    }
    public static DataCompanyIprPatentFamilyWarehouseCommand createByDataCompanyIprPatentFamilyExWarehouseVO(DataCompanyIprPatentFamilyExWarehouseVO exWarehouseVO) {
        DataCompanyIprPatentFamilyWarehouseCommand command = new DataCompanyIprPatentFamilyWarehouseCommand();
        command.companyIprPatentId = exWarehouseVO.getCompanyIprPatentId();
        command.applyNo = exWarehouseVO.getApplyNo();
        command.standardApplyNo = exWarehouseVO.getStandardApplyNo();
        command.applyDate = exWarehouseVO.getApplyDate();
        command.publicNo = exWarehouseVO.getPublicNo();
        command.standardPublicNo = exWarehouseVO.getStandardPublicNo();
        command.publicDate = exWarehouseVO.getPublicDate();
        command.title = exWarehouseVO.getTitle();
        command.titleCn = exWarehouseVO.getTitleCn();
        command.titleEn = exWarehouseVO.getTitleEn();

        command.countryCode = exWarehouseVO.getCountryCode();


        return command;
    }
}
