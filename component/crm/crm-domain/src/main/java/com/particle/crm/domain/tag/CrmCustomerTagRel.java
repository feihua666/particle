package com.particle.crm.domain.tag;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 客户标签关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Data
@Entity
public class CrmCustomerTagRel extends AggreateRoot {

    private CrmCustomerTagRelId id;

    /**
    * 客户id
    */
    private Long crmCustomerId;

    /**
    * 标签id
    */
    private Long crmCustomerTagId;



    /**
     * 创建客户标签关系领域模型对象
     * @return 客户标签关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrmCustomerTagRel create(){
        return DomainFactory.create(CrmCustomerTagRel.class);
    }
}
