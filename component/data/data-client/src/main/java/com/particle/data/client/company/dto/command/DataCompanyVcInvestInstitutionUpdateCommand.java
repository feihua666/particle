package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcInvestInstitutionWarehouseCommand;

/**
 * <p>
 * 企业投资机构 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Data
@Schema
public class DataCompanyVcInvestInstitutionUpdateCommand extends AbstractBaseUpdateCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "是否对应公司")
    private Boolean isRelatedCompany;


    @Schema(description = "机构名称")
    private String name;


    @Schema(description = "机构英文名称")
    private String enName;


    @Schema(description = "机构logo地址")
    private String logoUrl;


    @Schema(description = "机构网址")
    private String websiteUrl;


    @Schema(description = "成立年份")
    private Integer establishYear;


    @Schema(description = "成立日期")
    private LocalDate establishDate;


    @Schema(description = "所在省份")
    private Long provinceAreaId;


    @Schema(description = "所在城市")
    private Long cityAreaId;


    @Schema(description = "所在区县")
    private Long countyAreaId;


    @Schema(description = "机构地址")
    private String address;


    @Schema(description = "手机号码")
    private String mobile;


    @Schema(description = "电话号码")
    private String telephone;


    @Schema(description = "邮箱")
    private String email;


    @Schema(description = "微博地址")
    private String weiboUrl;


    @Schema(description = "微信号")
    private String wechatNo;


    @Schema(description = "机构介绍")
    private String description;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;
    public static DataCompanyVcInvestInstitutionUpdateCommand createByWarehouseCommand(Long id, Integer version,DataCompanyVcInvestInstitutionWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyVcInvestInstitutionUpdateCommand command = new DataCompanyVcInvestInstitutionUpdateCommand();
        command.setId(id);
        command.setVersion(version);
        command.companyId = dataCompanyBasicWarehouseCommand.getCompanyId();
        command.isRelatedCompany = dataCompanyBasicWarehouseCommand.getIsRelatedCompany();
        command.name = dataCompanyBasicWarehouseCommand.getName();
        command.enName = dataCompanyBasicWarehouseCommand.getEnName();
        command.logoUrl = dataCompanyBasicWarehouseCommand.getLogoUrl();
        command.websiteUrl = dataCompanyBasicWarehouseCommand.getWebsiteUrl();
        command.establishYear = dataCompanyBasicWarehouseCommand.getEstablishYear();
        command.establishDate = dataCompanyBasicWarehouseCommand.getEstablishDate();
        command.provinceAreaId = dataCompanyBasicWarehouseCommand.getProvinceAreaId();
        command.cityAreaId = dataCompanyBasicWarehouseCommand.getCityAreaId();
        command.countyAreaId = dataCompanyBasicWarehouseCommand.getCountyAreaId();
        command.address = dataCompanyBasicWarehouseCommand.getAddress();
        command.mobile = dataCompanyBasicWarehouseCommand.getMobile();
        command.telephone = dataCompanyBasicWarehouseCommand.getTelephone();
        command.email = dataCompanyBasicWarehouseCommand.getEmail();
        command.weiboUrl = dataCompanyBasicWarehouseCommand.getWeiboUrl();
        command.wechatNo = dataCompanyBasicWarehouseCommand.getWechatNo();
        command.description = dataCompanyBasicWarehouseCommand.getDescription();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();

        return command;
    }
}
