package com.particle.dataconstraint.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 数据范围 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Data
@Schema
public class DataScopeQueryListCommand extends AbstractBaseQueryCommand {



    @Like
        @Schema(description = "数据范围编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "数据范围名称,左前缀匹配")
    private String name;


    @Schema(description = "数据对象id")
    private Long dataObjectId;

	@Schema(description = "约束条件内容类型，字典id")
	private Long constraintContentTypeDictId;


    @Schema(description = "约束条件内容")
    private String constraintContent;


    @Schema(description = "是否自定义")
    private Boolean isCustom;


    @Schema(description = "是否用于删除")
    private Boolean isForDelete;


    @Schema(description = "是否用于修改")
    private Boolean isForUpdate;


    @Schema(description = "是否用于查询")
    private Boolean isForQuery;


    @Schema(description = "是否用于其它")
    private Boolean isForOther;










}