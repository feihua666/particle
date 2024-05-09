package com.particle.crm.domain.relation;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 客户与客户关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Data
@Entity
public class CrmCustomerRelation extends AggreateRoot {

    private CrmCustomerRelationId id;

    /**
    * 客户id
    */
    private Long crmCustomerId;

    /**
    * 另一个客户id
    */
    private Long anotherCrmCustomerId;

    /**
    * 关系id，描述为客户是另一个客户的关系，如：张三是李四的父亲
    */
    private Long crmCustomerRelationDefineId;

    /**
    * 关系详情描述
    */
    private String relationDetail;



    /**
     * 创建客户与客户关系领域模型对象
     * @return 客户与客户关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrmCustomerRelation create(){
        return DomainFactory.create(CrmCustomerRelation.class);
    }
}
