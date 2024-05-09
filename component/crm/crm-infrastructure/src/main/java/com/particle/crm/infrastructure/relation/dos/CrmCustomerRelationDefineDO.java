package com.particle.crm.infrastructure.relation.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 客户关系定义表
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Data
@TableName("component_crm_customer_relation_define")
public class CrmCustomerRelationDefineDO extends BaseDO {

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


}