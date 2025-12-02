package com.particle.data.client.dynamicdata.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 动态数据指标分类上传记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Data
@Schema
public class DynamicDataIndicatorCategoryUploadRecordVO extends AbstractBaseIdVO {

    @Schema(description = "动态数据指标分类id")
    private Long dynamicDataIndicatorCategoryId;

    @TransBy(tableName = TransTableNameConstants.component_data_dynamic_data_indicator_category, byFieldName = "dynamicDataIndicatorCategoryId", mapValueField = "name")
    @Schema(description = "动态数据指标分类名称")
    private String dynamicDataIndicatorCategoryName;

    @Schema(description = "上传文件名称")
    private String uploadFileName;

    @Schema(description = "上传文件地址")
    private String uploadFileUrl;

    @Schema(description = "上传指标数量")
    private Integer uploadIndicatorNum;

    @Schema(description = "上传数据数量")
    private Integer uploadIndicatorDataNum;

	@Schema(description = "是否发布，1=是，0=否")
	private Boolean isPublic;



}
