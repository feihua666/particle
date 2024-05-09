package com.particle.crm.domain.customer;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 客户联系方式 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Data
@Entity
public class CrmCustomerContact extends AggreateRoot {

    private CrmCustomerContactId id;

    /**
    * 客户id
    */
    private Long crmCustomerId;

    /**
    * 联系方式类型,字典id
    */
    private Long contactTypeDictId;

    /**
    * 联系方式
    */
    private String contact;

    /**
    * 备注
    */
    private String remark;



    /**
     * 创建客户联系方式领域模型对象
     * @return 客户联系方式领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrmCustomerContact create(){
        return DomainFactory.create(CrmCustomerContact.class);
    }
}
