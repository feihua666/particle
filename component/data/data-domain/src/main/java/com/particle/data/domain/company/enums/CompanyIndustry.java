package com.particle.data.domain.company.enums;


import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

/**
 * <p>
 * 国民经济行业分类 字典项
 * </p>
 *
 * @author yw
 * @since 2025-05-20 15:31:49
 */
public enum CompanyIndustry implements IDictItem {

    /**
     * 农、林、牧、渔业
     */
    A
    ,
    /**
     * 采矿业
     */
    B
    ,
    /**
     * 制造业
     */
    C
    ,
    /**
     * 电力、热力、燃气及水生产和供应业
     */
    D
    ,
    /**
     * 建筑业
     */
    E
    ,
    /**
     * 批发和零售业
     */
    F
    ,
    /**
     * 交通运输、仓储和邮政业
     */
    G
    ,
    /**
     * 住宿和餐饮业
     */
    H
    ,
    /**
     * 信息传输、软件和信息技术服务业
     */
    I
    ,
    /**
     * 金融业
     */
    J
    ,
    /**
     * 房地产业
     */
    K
    ,
    /**
     * 租赁和商务服务业
     */
    L
    ,
    /**
     * 科学研究和技术服务业
     */
    M
    ,
    /**
     * 水利、环境和公共设施管理业
     */
    N
    ,
    /**
     * 居民服务、修理和其他服务业
     */
    O
    ,
    /**
     * 教育
     */
    P
    ,
    /**
     * 卫生和社会工作
     */
    Q
    ,
    /**
     * 文化、体育和娱乐业
     */
    R
    ,
    /**
     * 公共管理、社会保障和社会组织
     */
    S
    ,
    /**
     * 国际组织
     */
    T
    ;

    @Override
    public String itemValue() {
        return this.name();
    }

    @Override
    public String groupCode() {
        return Group.company_industry.groupCode();
    }

    /**
     * 国民经济行业分类 字典组
     */
    public enum Group implements IDictGroup {
        company_industry;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
}

