package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyBasicWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyBasicWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseVO;
import com.particle.data.common.tool.CompanyNameCheckTool;
import com.particle.data.common.tool.CompanyNatureMatchTool;
import com.particle.data.common.tool.DataAreaItemInfo;
import com.particle.data.common.tool.DataDictItemInfo;
import com.particle.data.domain.company.enums.CompanyIndustry;
import com.particle.data.domain.company.enums.CompanyNature;
import com.particle.data.domain.company.enums.CompanyStatus;
import com.particle.data.domain.company.enums.CompanyType;
import com.particle.data.domain.gateway.DataAreaGateway;
import com.particle.data.domain.gateway.DataDictGateway;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业基本信息入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyBasicWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyBasicWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;


    /**
     * 企业基本信息入库
     * @param dataCompanyBasicExWarehouseVOSingleResponse
     */
    public void warehouse(SingleResponse<DataCompanyBasicExWarehouseVO> dataCompanyBasicExWarehouseVOSingleResponse) {
        DataCompanyBasicExWarehouseVO dataCompanyBasicExWarehouseVO = dataCompanyBasicExWarehouseVOSingleResponse.getData();
        if (dataCompanyBasicExWarehouseVO != null) {
            DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand = DataCompanyBasicWarehouseCommand.createByDataCompanyBasicExWarehouseVO(dataCompanyBasicExWarehouseVO);
            fillIds(dataCompanyBasicWarehouseCommand, dataCompanyBasicExWarehouseVO);
            dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
        }
    }

    /**
     * 填充id
     * @param dataCompanyBasicWarehouseCommand
     * @param dataCompanyBasicExWarehouseVO
     */
    private void fillIds(DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand, DataCompanyBasicExWarehouseVO dataCompanyBasicExWarehouseVO) {
        // 企业id
        if (dataCompanyBasicWarehouseCommand.getCompanyId() == null) {
            DataCompanyExWarehouseVO companyUnique = dataCompanyBasicExWarehouseVO.getCompanyUnique();
            if (companyUnique != null) {
                if (companyUnique.getId() != null) {
                    dataCompanyBasicWarehouseCommand.setCompanyId(companyUnique.getId());
                }else{
                    DataCompanyExWarehouseVO dataCompanyExWarehouseVO = warehouseCompany(DataCompanyWarehouseCommand.createByDataCompanyExWarehouseVO(companyUnique));
                    if (dataCompanyExWarehouseVO != null) {
                        dataCompanyBasicWarehouseCommand.setCompanyId(dataCompanyExWarehouseVO.getId());
                    }
                }
            }
        }
        // 登记状态
        if (dataCompanyBasicWarehouseCommand.getStatusDictId() == null) {
            Long statusDictId = mappingDictItemGetDictId(dataCompanyBasicExWarehouseVO.getStatusDictName(), CompanyStatus.Group.company_status.groupCode());
            dataCompanyBasicWarehouseCommand.setStatusDictId(statusDictId);
        }
        // 企业类型
        DataDictItemInfo typeDataDictItemInfo = null;
        if (dataCompanyBasicWarehouseCommand.getTypeDictId() == null) {
            typeDataDictItemInfo = mappingDictItem(dataCompanyBasicExWarehouseVO.getTypeDictName(), CompanyType.Group.company_type.groupCode());
            dataCompanyBasicWarehouseCommand.setTypeDictId(typeDataDictItemInfo == null ? null : typeDataDictItemInfo.getId());
        }
        // 企业性质
        if (dataCompanyBasicWarehouseCommand.getNatureDictId() == null) {
            String natureDictName = dataCompanyBasicExWarehouseVO.getNatureDictName();
            if (StrUtil.isBlank(natureDictName)) {
                DataCompanyExWarehouseVO companyUnique = dataCompanyBasicExWarehouseVO.getCompanyUnique();
                if (companyUnique != null) {
                    CompanyNatureMatchTool.EnterpriseInfo enterpriseInfo = CompanyNatureMatchTool.EnterpriseInfo.create(companyUnique.getName(),
                            companyUnique.getUscc(),
                            typeDataDictItemInfo != null ? typeDataDictItemInfo.getName() : dataCompanyBasicExWarehouseVO.getTypeDictName(),
                            companyUnique.getOrgCode(),
                            dataCompanyBasicExWarehouseVO.getRegAddress());
                    String matchNature = CompanyNatureMatchTool.match(enterpriseInfo);
                    natureDictName = matchNature;


                }
            }
            if (StrUtil.isBlank(natureDictName)) {
                Long natureDictId = mappingDictItemGetDictId(dataCompanyBasicExWarehouseVO.getNatureDictName(), CompanyNature.Group.company_nature.groupCode());
                dataCompanyBasicWarehouseCommand.setNatureDictId(natureDictId);
            }
        }
        // 法人
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyBasicExWarehouseVO.getLegalPersonName(),
                dataCompanyBasicWarehouseCommand.getLegalPersonCompanyId(),
                dataCompanyBasicWarehouseCommand.getLegalPersonCompanyPersonId(),
                dataCompanyBasicWarehouseCommand.getIsLegalPersonNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyBasicWarehouseCommand.setLegalPersonCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyBasicWarehouseCommand.setLegalPersonCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyBasicWarehouseCommand.setIsLegalPersonNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
        // 注册机关
        if (dataCompanyBasicWarehouseCommand.getRegInstituteCompanyId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyBasicExWarehouseVO.getRegInstituteName())) {
                Long regInstituteCompanyId = warehouseCompanyGetCompanyId(dataCompanyBasicExWarehouseVO.getRegInstituteName());
                dataCompanyBasicWarehouseCommand.setRegInstituteCompanyId(regInstituteCompanyId);
            }
        }

        // 区域信息
        fillAreaIds(dataCompanyBasicWarehouseCommand, dataCompanyBasicExWarehouseVO);
        // 行业信息
        fillIndustryIds(dataCompanyBasicWarehouseCommand, dataCompanyBasicExWarehouseVO);
    }

    /**
     * 填充区域id
     * @param dataCompanyBasicWarehouseCommand
     * @param dataCompanyBasicExWarehouseVO
     */
    private void fillAreaIds(DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand, DataCompanyBasicExWarehouseVO dataCompanyBasicExWarehouseVO) {
        // 省
        if (dataCompanyBasicWarehouseCommand.getProvinceAreaId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyBasicExWarehouseVO.getProvinceAreaName())) {
                DataAreaItemInfo provinceDataAreaItemInfo = dataAreaGateway.getProvinceByName(dataCompanyBasicExWarehouseVO.getProvinceAreaName());
                if (provinceDataAreaItemInfo != null) {
                    dataCompanyBasicWarehouseCommand.setProvinceAreaId(provinceDataAreaItemInfo.getId());
                }
            }
        }
        // 市
        if (dataCompanyBasicWarehouseCommand.getCityAreaId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyBasicExWarehouseVO.getCityAreaName())) {
                if (dataCompanyBasicWarehouseCommand.getProvinceAreaId() != null) {
                    DataAreaItemInfo cityDataAreaItemInfo = dataAreaGateway.getCityByNameAndProvinceId(dataCompanyBasicExWarehouseVO.getCityAreaName(), dataCompanyBasicWarehouseCommand.getProvinceAreaId());
                    if (cityDataAreaItemInfo != null) {
                        dataCompanyBasicWarehouseCommand.setCityAreaId(cityDataAreaItemInfo.getId());
                    }
                }
            }
        }
        // 区县
        if (dataCompanyBasicWarehouseCommand.getCountyAreaId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyBasicExWarehouseVO.getCountyAreaName())) {
                if (dataCompanyBasicWarehouseCommand.getCityAreaId() != null) {
                    DataAreaItemInfo countyDataAreaItemInfo = dataAreaGateway.getCountyByNameAndCityId(dataCompanyBasicExWarehouseVO.getCountyAreaName(), dataCompanyBasicWarehouseCommand.getCityAreaId());
                    if (countyDataAreaItemInfo != null) {
                        dataCompanyBasicWarehouseCommand.setCountyAreaId(countyDataAreaItemInfo.getId());
                    }
                }
            }
        }
    }

    /**
     * 填充行业id
     * @param dataCompanyBasicWarehouseCommand
     * @param dataCompanyBasicExWarehouseVO
     */
    private void fillIndustryIds(DataCompanyBasicWarehouseCommand dataCompanyBasicWarehouseCommand, DataCompanyBasicExWarehouseVO dataCompanyBasicExWarehouseVO) {
        // 门类
        DataDictItemInfo mainDataDictItemInfo = null;
        if (dataCompanyBasicWarehouseCommand.getIndustryMainDictId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyBasicExWarehouseVO.getIndustryMainDictName())) {
                mainDataDictItemInfo = dataDictGateway.matchWithMappingValue(dataCompanyBasicExWarehouseVO.getIndustryMainDictName(), CompanyIndustry.Group.company_industry.groupCode(), true, true);
                if (mainDataDictItemInfo != null) {
                    dataCompanyBasicWarehouseCommand.setIndustryMainDictId(mainDataDictItemInfo.getId());
                }
            }
        }
        // 大类
        DataDictItemInfo bigDataDictItemInfo = null;
        if (dataCompanyBasicWarehouseCommand.getIndustryBigDictId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyBasicExWarehouseVO.getIndustryBigDictName())) {
                if (dataCompanyBasicWarehouseCommand.getIndustryMainDictId() != null) {
                    bigDataDictItemInfo = dataDictGateway.matchWithMappingValue(dataCompanyBasicExWarehouseVO.getIndustryBigDictName(), mainDataDictItemInfo.getCode(), true, true);
                    if (mainDataDictItemInfo != null) {
                        dataCompanyBasicWarehouseCommand.setIndustryBigDictId(bigDataDictItemInfo.getId());
                    }
                }
            }
        }
        // 中类
        DataDictItemInfo middleDataDictItemInfo = null;
        if (dataCompanyBasicWarehouseCommand.getIndustryMiddleDictId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyBasicExWarehouseVO.getIndustryMiddleDictName())) {
                if (dataCompanyBasicWarehouseCommand.getIndustryBigDictId() != null) {
                    middleDataDictItemInfo = dataDictGateway.matchWithMappingValue(dataCompanyBasicExWarehouseVO.getIndustryMiddleDictName(), bigDataDictItemInfo.getCode(), true, true);
                    if (bigDataDictItemInfo != null) {
                        dataCompanyBasicWarehouseCommand.setIndustryMiddleDictId(middleDataDictItemInfo.getId());
                    }
                }
            }
        }
        // 小类
        DataDictItemInfo smallDataDictItemInfo = null;
        if (dataCompanyBasicWarehouseCommand.getIndustrySmallDictId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyBasicExWarehouseVO.getIndustrySmallDictName())) {
                if (dataCompanyBasicWarehouseCommand.getIndustryMiddleDictId() != null) {
                    smallDataDictItemInfo = dataDictGateway.matchWithMappingValue(dataCompanyBasicExWarehouseVO.getIndustrySmallDictName(), middleDataDictItemInfo.getCode(), true, true);
                    if (middleDataDictItemInfo != null) {
                        dataCompanyBasicWarehouseCommand.setIndustrySmallDictId(smallDataDictItemInfo.getId());
                    }
                }
            }
        }
    }

    @Autowired
    public void setDataCompanyBasicWarehouseCommandExecutor(DataCompanyBasicWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }
}
