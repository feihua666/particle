package com.particle.data.client.dynamicdata.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 动态数据指标分类 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Data
@Schema
public class DynamicDataIndicatorCategoryVO extends AbstractBaseIdVO {

    @Schema(description = "动态数据分类id")
    private Long dynamicDataCategoryId;

    @TransBy(tableName = TransTableNameConstants.component_data_dynamic_data_category, byFieldName = "dynamicDataCategoryId", mapValueField = "name")
    @Schema(description = "动态数据分类名称")
    private String dynamicDataCategoryName;

    @Schema(description = "动态数据指标分类名称")
    private String name;

    @Schema(description = "是否禁用")
    private Boolean isDisabled;

    @Schema(description = "动态数据指标分类类型")
    private Long typeDictId;

	@Schema(description = "指标数量")
	private Integer dynamicDataIndicatorNum;

	@Schema(description = "数据数量")
	private Integer dynamicDataIndicatorCategoryDataNum;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "动态数据指标分类类型对应字典名称")
    private String typeDictName;

    @Schema(description = "备注信息")
    private String remark;



}