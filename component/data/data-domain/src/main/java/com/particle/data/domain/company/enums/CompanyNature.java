package com.particle.data.domain.company.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 企业性质 字典项
 * </p>
 *
 * @author yw
 * @since 2025-05-20 14:59:04
 */
public enum CompanyNature implements IDictItem {

    /**
     * 大陆企业
     */
    mainland_company
    ,
    /**
     * 社会组织
     */
    social_organization
    ,
    /**
     * 中国香港公司
     */
    hong_kong_company
    ,
    /**
     * 事业单位
     */
    public_institution
    ,
    /**
     * 中国台湾公司
     */
    taiwan_company
    ,
    /**
     * 基金会
     */
    foundation
    ,
    /**
     * 医院
     */
    hospital
    ,
    /**
     * 海外公司
     */
    overseas_company
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
     * 机关单位
     */
    government_agency
    ,
    /**
     * 其他未识别性质
     */
    other_unidentified_nature
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.company_nature.groupCode();
    }

    /**
     * 企业性质 字典组
     */
    public enum Group implements IDictGroup {
        company_nature;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

