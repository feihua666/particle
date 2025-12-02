package com.particle.data.client.dynamictable.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 动态数据表格 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Data
@Schema
public class DynamicTablePageQueryCommand extends AbstractBasePageQueryCommand {

	@Schema(description = "字段数量")
	private Integer dynamicTableFieldNum;

	@Schema(description = "数据数量")
	private Integer dynamicTableDataNum;

	@Schema(description = "备注信息")
	private String remark;


    @Like(left = true,right = true)
    @Schema(description = "表名称")
    private String name;

    @Like(left = true,right = true)
    @Schema(description = "表注释")
    private String comment;
}