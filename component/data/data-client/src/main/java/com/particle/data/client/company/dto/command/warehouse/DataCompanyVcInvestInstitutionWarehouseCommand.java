package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>
 * 企业投资机构入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyVcInvestInstitutionWarehouseCommand extends AbstractBaseCommand {



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

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyVcInvestInstitutionDataMd5(name,enName);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyId)
                && Objects.isNull(isRelatedCompany)
                && StrUtil.isEmpty(name)
                && StrUtil.isEmpty(enName)
                && StrUtil.isEmpty(logoUrl)
                && StrUtil.isEmpty(websiteUrl)
                && Objects.isNull(establishYear)
                && Objects.isNull(establishDate)
                && Objects.isNull(provinceAreaId)
                && Objects.isNull(cityAreaId)
                && Objects.isNull(countyAreaId)
                && StrUtil.isEmpty(address)
                && StrUtil.isEmpty(mobile)
                && StrUtil.isEmpty(telephone)
                && StrUtil.isEmpty(email)
                && StrUtil.isEmpty(weiboUrl)
                && StrUtil.isEmpty(wechatNo)
                && StrUtil.isEmpty(description);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyVcInvestInstitutionExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyId, exWarehouseVO.getCompanyId())) {
            this.companyId = null;
        }
        if (Objects.equals(isRelatedCompany, exWarehouseVO.getIsRelatedCompany())) {
            this.isRelatedCompany = null;
        }
        if (Objects.equals(name, exWarehouseVO.getName())) {
            this.name = null;
        }
        if (Objects.equals(enName, exWarehouseVO.getEnName())) {
            this.enName = null;
        }
        if (Objects.equals(logoUrl, exWarehouseVO.getLogoUrl())) {
            this.logoUrl = null;
        }
        if (Objects.equals(websiteUrl, exWarehouseVO.getWebsiteUrl())) {
            this.websiteUrl = null;
        }
        if (Objects.equals(establishYear, exWarehouseVO.getEstablishYear())) {
            this.establishYear = null;
        }
        if (Objects.equals(establishDate, exWarehouseVO.getEstablishDate())) {
            this.establishDate = null;
        }
        if (Objects.equals(provinceAreaId, exWarehouseVO.getProvinceAreaId())) {
            this.provinceAreaId = null;
        }
        if (Objects.equals(cityAreaId, exWarehouseVO.getCityAreaId())) {
            this.cityAreaId = null;
        }
        if (Objects.equals(countyAreaId, exWarehouseVO.getCountyAreaId())) {
            this.countyAreaId = null;
        }
        if (Objects.equals(address, exWarehouseVO.getAddress())) {
            this.address = null;
        }
        if (Objects.equals(mobile, exWarehouseVO.getMobile())) {
            this.mobile = null;
        }
        if (Objects.equals(telephone, exWarehouseVO.getTelephone())) {
            this.telephone = null;
        }
        if (Objects.equals(email, exWarehouseVO.getEmail())) {
            this.email = null;
        }
        if (Objects.equals(weiboUrl, exWarehouseVO.getWeiboUrl())) {
            this.weiboUrl = null;
        }
        if (Objects.equals(wechatNo, exWarehouseVO.getWechatNo())) {
            this.wechatNo = null;
        }
        if (Objects.equals(description, exWarehouseVO.getDescription())) {
            this.description = null;
        }
    }

    public static DataCompanyVcInvestInstitutionWarehouseCommand createByDataCompanyVcInvestInstitutionExWarehouseVO(DataCompanyVcInvestInstitutionExWarehouseVO exWarehouseVO) {
        DataCompanyVcInvestInstitutionWarehouseCommand command = new DataCompanyVcInvestInstitutionWarehouseCommand();

        command.companyId = exWarehouseVO.getCompanyId();
        command.isRelatedCompany = exWarehouseVO.getIsRelatedCompany();
        command.name = exWarehouseVO.getName();
        command.enName = exWarehouseVO.getEnName();
        command.logoUrl = exWarehouseVO.getLogoUrl();
        command.websiteUrl = exWarehouseVO.getWebsiteUrl();
        command.establishYear = exWarehouseVO.getEstablishYear();
        command.establishDate = exWarehouseVO.getEstablishDate();
        command.provinceAreaId = exWarehouseVO.getProvinceAreaId();
        command.cityAreaId = exWarehouseVO.getCityAreaId();
        command.countyAreaId = exWarehouseVO.getCountyAreaId();
        command.address = exWarehouseVO.getAddress();
        command.mobile = exWarehouseVO.getMobile();
        command.telephone = exWarehouseVO.getTelephone();
        command.email = exWarehouseVO.getEmail();
        command.weiboUrl = exWarehouseVO.getWeiboUrl();
        command.wechatNo = exWarehouseVO.getWechatNo();
        command.description = exWarehouseVO.getDescription();

        return command;
    }
}
