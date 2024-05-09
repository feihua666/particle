package com.particle.crm.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 客户公司 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Data
@Entity
public class CrmCompany extends AggreateRoot {

    private CrmCompanyId id;

    /**
    * 公司编码
    */
    private String code;

    /**
    * 公司名称
    */
    private String name;

    /**
    * 公司简称名称
    */
    private String nameSimple;

    /**
    * 备注
    */
    private String remark;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 父级
    */
    private Long parentId;



    /**
     * 创建客户公司领域模型对象
     * @return 客户公司领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrmCompany create(){
        return DomainFactory.create(CrmCompany.class);
    }
}
