package com.particle.crm.domain.relation;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 客户关系定义 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Data
@Entity
public class CrmCustomerRelationDefine extends AggreateRoot {

    private CrmCustomerRelationDefineId id;

    /**
    * 关系定义编码
    */
    private String code;

    /**
    * 关系定义名称
    */
    private String name;

	/**
	 * 是否为双向关系,不是双向就是单身
	 */
	private Boolean isBidirectional;

	/**
	 * 双向关系id，如果为单向关系，则必填，存储对应的双向关系id
	 */
	private Long bidirectionalId;

    /**
    * 备注
    */
    private String remark;



    /**
     * 创建客户关系定义领域模型对象
     * @return 客户关系定义领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static CrmCustomerRelationDefine create(){
        return DomainFactory.create(CrmCustomerRelationDefine.class);
    }
}