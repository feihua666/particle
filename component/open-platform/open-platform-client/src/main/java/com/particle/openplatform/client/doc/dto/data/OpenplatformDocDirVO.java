package com.particle.openplatform.client.doc.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口目录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Data
@Schema
public class OpenplatformDocDirVO extends AbstractBaseIdTreeVO {

    @Schema(description = "名称")
    private String name;
    
    @Schema(description = "简称")
    private String nameSimple;
    
    @Schema(description = "开放接口文档目录名称id")
    private Long openplatformDocDirNameId;


    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_dir_name, byFieldName = "openplatformDocDirNameId", mapValueField = "name")
    @Schema(description = "开放接口文档目录名称的名称")
    private String openplatformDocDirNameName;

    @Schema(description = "备注")
    private String remark;
    
    @Schema(description = "排序")
    private Integer seq;

    @Schema(description = "父级名称")
    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_dir, byFieldName = "parentId", mapValueField = "name")
    private String parentName;

}
