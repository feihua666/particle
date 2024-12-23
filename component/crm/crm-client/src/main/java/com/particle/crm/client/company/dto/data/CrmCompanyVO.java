package com.particle.crm.client.company.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 客户公司 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Data
@Schema
public class CrmCompanyVO extends AbstractBaseIdTreeVO {

    @Schema(description = "公司编码")
    private String code;

    @Schema(description = "公司名称")
    private String name;

    @Schema(description = "公司简称名称")
    private String nameSimple;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "排序")
    private Integer seq;


    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_crm_company, byFieldName = "parentId", mapValueField = "name")
    private String parentName;
}
