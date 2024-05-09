package com.particle.crm.client.relation.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 客户关系定义 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Data
@Schema
public class CrmCustomerRelationDefineVO extends AbstractBaseIdVO {

    @Schema(description = "关系定义编码")
    private String code;
    
    @Schema(description = "关系定义名称")
    private String name;

	@Schema(description = "是否为双向关系,不是双向就是单身")
	private Boolean isBidirectional;

	@Schema(description = "双向关系id，如果为单向关系，则必填，存储对应的双向关系id")
	private Long bidirectionalId;

    @TransBy(tableName = TransTableNameConstants.component_crm_customer_relation_define, byFieldName = "bidirectionalId", mapValueField = "name")
    @Schema(description = "双向关系名称")
    private String bidirectionalName;
    
    @Schema(description = "备注")
    private String remark;
    


}