package com.particle.openplatform.client.doc.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放接口文档接口与目录关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Data
@Schema
public class OpenplatformDocApiDirRelVO extends AbstractBaseIdVO {

    @Schema(description = "开放接口文档接口id")
    private Long openplatformDocApiId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api, byFieldName = "openplatformDocApiId", mapValueField = "code")
    @Schema(description = "开放接口文档接口编码")
    private String openplatformDocApiCode;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_api, byFieldName = "openplatformDocApiId", mapValueField = "name")
    @Schema(description = "开放接口文档接口名称")
    private String openplatformDocApiName;

    @Schema(description = "开放接口文档目录id")
    private Long openplatformDocDirId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_doc_dir, byFieldName = "openplatformDocDirId", mapValueField = "name")
    @Schema(description = "开放接口文档目录名称")
    private String openplatformDocDirName;

}
