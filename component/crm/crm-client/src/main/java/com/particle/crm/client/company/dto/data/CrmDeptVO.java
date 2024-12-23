package com.particle.crm.client.company.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 客户公司部门 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Data
@Schema
public class CrmDeptVO extends AbstractBaseIdTreeVO {

    @Schema(description = "部门编码")
    private String code;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "客户公司id")
    private Long crmCompanyId;

    @TransBy(tableName = TransTableNameConstants.component_crm_company, byFieldName = "crmCompanyId", mapValueField = "name")
    @Schema(description = "客户公司名称")
    private String crmCompanyName;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "排序")
    private Integer seq;


    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_crm_dept, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}
