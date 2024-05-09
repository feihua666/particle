package com.particle.crm.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 客户公司部门 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Data
@Entity
public class CrmDept extends AggreateRoot {

    private CrmDeptId id;

    /**
    * 部门编码
    */
    private String code;

    /**
    * 部门名称
    */
    private String name;

    /**
    * 客户公司id
    */
    private Long crmCompanyId;

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
     * 创建客户公司部门领域模型对象
     * @return 客户公司部门领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrmDept create(){
        return DomainFactory.create(CrmDept.class);
    }
}
