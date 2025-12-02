package com.particle.data.client.dynamictable.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 动态数据表格上传记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Data
@Schema
public class DynamicTableUploadRecordVO extends AbstractBaseIdVO {

    @Schema(description = "动态数据表格id")
    private Long dynamicTableId;

    @Schema(description = "动态数据表格名称")
    @TransBy(tableName = TransTableNameConstants.component_data_dynamic_table, byFieldName = "dynamicTableId", mapValueField = "name")
    private String dynamicTableName;

    @Schema(description = "上传文件名称")
    private String uploadFileName;

    @Schema(description = "上传文件地址")
    private String uploadFileUrl;

    @Schema(description = "上传字段数量")
    private Integer uploadTableFieldNum;

    @Schema(description = "上传数据数量")
    private Integer uploadTableDataNum;

	@Schema(description = "是否发布，1=是，0=否")
	private Boolean isPublic;



}
