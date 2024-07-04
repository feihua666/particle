package com.particle.dataconstraint.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 数据对象 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Data
@Schema
public class DataObjectQueryListCommand extends AbstractBaseQueryCommand {



    @Like
        @Schema(description = "数据对象编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "数据对象名称,左前缀匹配")
    private String name;


    @Schema(description = "数据范围自定义时用来绑定自定义数据的url")
    private String customDataUrl;


    @Schema(description = "自定义数据是否懒加载")
    private Boolean isCustomDataLazy;


    @Schema(description = "自定义数据交互方式")
    private Long customDataTypeDictId;


    @Schema(description = "数据交互方式内容")
    private String customDataConfigJson;

	@Schema(description = "是否禁用")
	private Boolean isDisabled;

	@Schema(description = "禁用原因")
	private String disabledReason;










}