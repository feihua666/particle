package com.particle.data.domain.company.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 企业类型 字典项
 * </p>
 *
 * @author yw
 * @since 2025-05-20 14:56:29
 */
public enum CompanyType implements IDictItem {

    /**
     * 有限责任公司
     */
    limited_liability_company
    ,
    /**
     * 股份有限公司
     */
    joint_stock_company
    ,
    /**
     * 个人独资企业
     */
    sole_proprietorship
    ,
    /**
     * 普通合伙
     */
    general_partnership
    ,
    /**
     * 有限合伙
     */
    limited_partnership
    ,
    /**
     * 全民所有制
     */
    state_owned
    ,
    /**
     * 集体所有制
     */
    collective_owned
    ,
    /**
     * 联营企业
     */
    joint_venture
    ,
    /**
     * 股份合作企业
     */
    shareholding_cooperative
    ,
    /**
     * 个体工商户
     */
    individual_business
    ,
    /**
     * 农民专业合作社（联合社）
     */
    farmers_professional_cooperative
    ,
    /**
     * 机关单位
     */
    government_agency
    ,
    /**
     * 事业单位
     */
    public_institution
    ,
    /**
     * 社会组织
     */
    social_organization
    ,
    /**
     * 律师事务所
     */
    law_firm
    ,
    /**
     * 学校
     */
    school
    ,
    /**
     * 医疗机构
     */
    medical_institution
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.company_type.groupCode();
    }

    /**
     * 企业类型 字典组
     */
    public enum Group implements IDictGroup {
        company_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

