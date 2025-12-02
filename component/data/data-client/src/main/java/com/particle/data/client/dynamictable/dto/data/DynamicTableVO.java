package com.particle.data.client.dynamictable.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 动态数据表格 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Data
@Schema
public class DynamicTableVO extends AbstractBaseIdVO {

    @Schema(description = "表名称")
    private String name;
    
    @Schema(description = "表注释")
    private String comment;
    
    @Schema(description = "是否已建表")
    private Boolean isCreatedTable;

	@Schema(description = "字段数量")
	private Integer dynamicTableFieldNum;

	@Schema(description = "数据数量")
	private Integer dynamicTableDataNum;

	@Schema(description = "备注信息")
	private String remark;
    


}