package com.particle.crm.domain.tag;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 客户标签 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Data
@Entity
public class CrmCustomerTag extends AggreateRoot {

    private CrmCustomerTagId id;

    /**
    * 标签编码
    */
    private String code;

    /**
    * 标签名称
    */
    private String name;

    /**
    * 备注
    */
    private String remark;



    /**
     * 创建客户标签领域模型对象
     * @return 客户标签领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrmCustomerTag create(){
        return DomainFactory.create(CrmCustomerTag.class);
    }
}
