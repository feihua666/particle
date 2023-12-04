package com.particle.dataquery.client.dataapi.dto.command.representation;


import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 数据查询数据接口 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Data
@Schema
public class DataQueryDataApiQueryListCommand extends AbstractBaseQueryCommand {

	@Schema(description = "出参翻译配置json")
	private String outParamTransConfigJson;

	@Schema(description = "出参扩展配置json")
	private String outParamExtConfigJson;

	@Schema(description = "入参扩展配置json")
	private String inParamExtConfigJson;

	@Schema(description = "是否使用远程服务")
	private Boolean isUseRemote;

    @Like
    @Schema(description = "接口地址,左前缀匹配")
    private String url;

    @Like
    @Schema(description = "接口名称,左前缀匹配")
    private String name;

    @Schema(description = "数据查询数据源接口id")
    private Long dataQueryDatasourceApiId;

    @Schema(description = "适配类型")
    private Long adaptTypeDictId;

    @Schema(description = "入参类型")
    private Long inParamTypeDictId;

    @Schema(description = "出参类型")
    private Long outParamTypeDictId;

    @Schema(description = "输出类型")
    private Long responseTypeDictId;

}